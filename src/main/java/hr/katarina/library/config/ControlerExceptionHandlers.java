package hr.katarina.library.config;

import hr.katarina.library.book.exceptions.BookAllreadyBorrowed;
import hr.katarina.library.book.exceptions.BookNotBorrowedException;
import hr.katarina.library.book.exceptions.BookNotFoundException;
import hr.katarina.library.users.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ControlerExceptionHandlers {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseBody
    public String handleMissingPathVariableExceptions(MissingPathVariableException ex) {
        return "Missing path variable.";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BookNotFoundException.class, UserNotFoundException.class, BookAllreadyBorrowed.class, BookNotBorrowedException.class})
    @ResponseBody
    public String handleBusinessExceptions(RuntimeException ex) {
        return ex.getMessage();
    }
}
