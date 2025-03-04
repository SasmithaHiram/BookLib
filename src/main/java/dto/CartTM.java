package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CartTM {
    private String borrowId;
    private String memberId;
    private String bookId;
    private String borrowDate;
    private String returnDate;

}
