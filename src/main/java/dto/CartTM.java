package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import util.BorrowStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CartTM {
    private String bookId;
    private String borrowDate;
    private String dewDate;
}
