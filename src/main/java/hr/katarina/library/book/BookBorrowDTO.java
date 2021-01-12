package hr.katarina.library.book;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class BookBorrowDTO {
    @NotNull
    Long userId;
}
