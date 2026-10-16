package org.example.librarymanagement.book;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagement.common.BusinessException;
import org.example.librarymanagement.common.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream()
                .map(BookResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public BookResponse findById(Long id) {
        return BookResponse.from(bookRepository.findById(id).orElse(null));
    }

    @Transactional
    public BookResponse create (CreateBookRequest request) {
        if (bookRepository.existsByIsbn(request.isbn())) {
            throw new BusinessException("Book with ISBN " + request.isbn() + " already exists!");
        }
        Book book = Book.builder()
                .title(request.title())
                .author(request.author())
                .isbn(request.isbn())
                .totalCopies(request.totalCopies())
                .availableCopies(request.totalCopies())
                .build();

        return BookResponse.from(bookRepository.save(book));
    }

    @Transactional
    public BookResponse updateBook(Long id, UpdateBookRequest request) {
        Book book = getBookById(id);
        book.updateBook(request.title(), request.author(), request.totalCopies());
        return BookResponse.from(book);
    }

    @Transactional
    public Book getBookById(Long id){
      return   bookRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Book with id " + id + " not found!"));
    }
}
