package org.example.librarymanagement.book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.example.librarymanagement.common.BusinessException;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private int totalCopies;
    private int availableCopies;


    public void updateBook(String title, String author, int newTotalCopies) {
        int borrowedCopies = totalCopies - availableCopies;
        if (totalCopies < borrowedCopies) {
            throw new BusinessException("total copies less than borrowed copies");
        }
        this.title = title;
        this.author = author;
        this.totalCopies = newTotalCopies;
        this.availableCopies = totalCopies - borrowedCopies;
    }

    public void borrowBook() {
        if (availableCopies == 0)
            throw new BusinessException("there is no available copies.");
        availableCopies--;
    }

    public void returnBook() {
        availableCopies++;
    }
}
