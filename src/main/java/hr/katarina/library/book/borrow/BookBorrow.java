package hr.katarina.library.book.borrow;

import hr.katarina.library.book.Book;
import hr.katarina.library.users.User;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book_borrows")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrow extends AbstractPersistable<Long> {
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "borrow_date")
    private Date borrowDate;

    @Column(name = "expected_return_date")
    private Date expectedReturnDate;

    @Column(name = "return_date")
    private Date returnDate;
}
