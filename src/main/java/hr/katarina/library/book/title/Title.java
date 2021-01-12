package hr.katarina.library.book.title;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "titles")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Title extends AbstractPersistable<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;
}
