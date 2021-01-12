package hr.katarina.library.book;

import hr.katarina.library.book.borrow.BookBorrow;
import hr.katarina.library.book.borrow.BookBorrowService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private final BookBorrowService bookBorrowService;

    @GetMapping("{id}/borrows")
    public List<BookBorrow> getBookBorrows(@PathVariable Long id) {
        return bookBorrowService.getBookBorrows(id);
    }

    @PostMapping("{id}/borrows")
    public BookBorrow create(@PathVariable Long id, @RequestBody BookBorrowDTO bookBorrowDTO) {
        return bookBorrowService.create(id, bookBorrowDTO.getUserId());
    }

    @PostMapping("{id}/borrows/return")
    public BookBorrow returnBook(@PathVariable Long id) {
        return bookBorrowService.returnBook(id);
    }

}
