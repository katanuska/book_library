package hr.katarina.library.book.exceptions;

public class BookAllreadyBorrowed extends RuntimeException{
    public BookAllreadyBorrowed(String message) {
        super(message);
    }
}
