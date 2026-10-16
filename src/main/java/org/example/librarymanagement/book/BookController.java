package org.example.librarymanagement.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable String id) {
        return ResponseEntity.ok().body(bookService.findById(Long.parseLong(id)));
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Validated @RequestBody CreateBookRequest request){
        return ResponseEntity.ok().body(bookService.create(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable String id, @Validated @RequestBody UpdateBookRequest request) {
        return ResponseEntity.ok().body(bookService.updateBook(Long.parseLong(id),request));
    }
}
