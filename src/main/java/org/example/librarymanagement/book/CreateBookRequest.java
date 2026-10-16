package org.example.librarymanagement.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateBookRequest(
        @NotBlank String title,
        @NotBlank String author,
        @NotBlank String isbn,
        @NotNull @PositiveOrZero Integer totalCopies) {
}
