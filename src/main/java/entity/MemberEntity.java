package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MemberEntity {
    private String id;
    private String name;
    private String contactInfo;
    private String MembershipDate;

}
