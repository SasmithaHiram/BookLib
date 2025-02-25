package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Book {
    private String id;
    private String iSBN;
    private String title;
    private String author;
    private String genre;
    private String availability;

}
