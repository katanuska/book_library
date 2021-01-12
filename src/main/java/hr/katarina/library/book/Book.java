package hr.katarina.library.book;

import hr.katarina.library.book.title.Title;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book  extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "title_id", nullable = false)
    private Title title;

    @Column(name = "number")
    private String number;

}
