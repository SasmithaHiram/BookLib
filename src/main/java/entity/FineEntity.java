package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FineEntity {
    private String borrowId;
    private String borrowDate;
    private String dewDate;
    private String returnDate;
    private Integer fineAmount;
    private Double payAmount;

}
