package hr.katarina.library.book.borrow;

import hr.katarina.library.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookBorrowRepository extends JpaRepository<BookBorrow, Long> {
    boolean existsByBook(Book book);

    List<BookBorrow> findByBook(Book book);

    @Query(nativeQuery = true, value = "SELECT bb.* FROM book_borrows bb JOIN users u on bb.user_id = u.id WHERE bb.return_date IS NULL LIMIT 1")
    Optional<BookBorrow> findActiveBorrow(@Param("book") Book book);
}
