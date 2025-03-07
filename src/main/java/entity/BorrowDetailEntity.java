package entity;

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
    private String memberId;
    private String bookId;

}
