package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Borrow {
    private Integer orderId;
    private Integer memberId;
    private Integer bookId;
    private String date;
    private String returnDate;
    private String availability;

}
