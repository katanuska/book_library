package hr.katarina.library.book.borrow;

import hr.katarina.library.book.Book;
import hr.katarina.library.book.BookRepository;
import hr.katarina.library.book.exceptions.BookAllreadyBorrowed;
import hr.katarina.library.book.exceptions.BookNotBorrowedException;
import hr.katarina.library.book.exceptions.BookNotFoundException;
import hr.katarina.library.users.exceptions.UserNotFoundException;
import hr.katarina.library.users.User;
import hr.katarina.library.users.UserRepository;
import hr.katarina.library.util.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BookBorrowService {

    private final BookBorrowRepository bookBorrowRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public List<BookBorrow> getBookBorrows(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found."));
        return bookBorrowRepository.findByBook(book);
    }

    public BookBorrow create(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found."));
        if (bookBorrowRepository.findActiveBorrow(book).isPresent())
            throw new BookAllreadyBorrowed("Book is already borrowed.");

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found."));
        Date borrowDate = new Date();
        Date expectedReturnDate = DateUtil.addDays(borrowDate, 30);

        BookBorrow bookBorrow = new BookBorrow(book, user, borrowDate, expectedReturnDate, null);

        return bookBorrowRepository.save(bookBorrow);
    }

    public BookBorrow returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found."));
        BookBorrow bookBorrow = bookBorrowRepository.findActiveBorrow(book)
                .orElseThrow(() -> new BookNotBorrowedException("Book is not borrowed"));

        bookBorrow.setReturnDate(new Date());
        return bookBorrowRepository.save(bookBorrow);
    }

}
