package org.example.librarymanagement.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateBookRequest(
        @NotBlank String title,
        @NotBlank String author,
        @NotNull @PositiveOrZero Integer totalCopies
) {
}
