package org.example.librarymanagement.loan;

import jakarta.persistence.*;
import lombok.*;
import org.example.librarymanagement.book.Book;
import org.example.librarymanagement.common.BusinessException;
import org.example.librarymanagement.user.AppUser;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;


    private Instant borrowedAt;
    private Instant returnedAt;

    private static Loan borrow(AppUser member, Book book) {
        book.borrowBook();
        return Loan.builder()
                .member(member)
                .book(book)
                .status(LoanStatus.BORROWED)
                .borrowedAt(Instant.now())
                .build();
    }

    public void markReturned() {
        if (this.status == LoanStatus.RETURNED)
            throw new BusinessException("Loan returned");
        status = LoanStatus.RETURNED;
        returnedAt = Instant.now();
        book.returnBook();
    }
}
