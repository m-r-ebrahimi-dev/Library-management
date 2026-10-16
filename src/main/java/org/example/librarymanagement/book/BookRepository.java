package org.example.librarymanagement.book;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BookRepository extends JpaRepository<Book,Long> {
    boolean existsByIsbn(String isbn);
}
