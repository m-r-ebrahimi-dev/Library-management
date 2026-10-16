package org.example.librarymanagement.bootstrap;

import lombok.RequiredArgsConstructor;
import org.example.librarymanagement.book.Book;
import org.example.librarymanagement.book.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FakeData implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        Book book1 = Book.builder()
                .title("Book 1")
                .author("Author 1")
                .isbn("111111111")
                .totalCopies(10)
                .availableCopies(10).build();

        Book book2 = Book.builder()
                .title("title2")
                .author("author2")
                .isbn("111111112")
                .totalCopies(10)
                .availableCopies(10).build();

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
