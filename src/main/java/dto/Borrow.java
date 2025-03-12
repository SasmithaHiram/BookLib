package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import util.BorrowStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Borrow {
    private String borrowId;
    private String memberId;
    private String borrowDate;
    private String dewDate;
    private BorrowStatus status;
    private List<BorrowDetails> borrowedBooks;

}
