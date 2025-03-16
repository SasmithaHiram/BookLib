package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Fine {
    private String borrowId;
    private String borrowDate;
    private String dewDate;
    private String returnDate;
    private Integer fineAmount;
    private Double payAmount;

}
