package entity;

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

public class BorrowEntity {
    private String borrowId;
    private String memberId;
    private String bookId;
    private String borrowDate;
    private String dewDate;
    private String returnDate;
    private BorrowStatus status;

}
