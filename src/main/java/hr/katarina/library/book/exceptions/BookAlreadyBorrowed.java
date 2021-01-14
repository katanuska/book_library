package hr.katarina.library.book.exceptions;

public class BookAlreadyBorrowed extends RuntimeException{
    public BookAlreadyBorrowed(String message) {
        super(message);
    }
}
