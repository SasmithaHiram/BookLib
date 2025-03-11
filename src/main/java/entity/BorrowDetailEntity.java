package entity;

import dto.BorrowDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BorrowDetailEntity {
    private String borrowId;
    private String bookId;
    private String borrowDate;
    private String returnDate;

}
