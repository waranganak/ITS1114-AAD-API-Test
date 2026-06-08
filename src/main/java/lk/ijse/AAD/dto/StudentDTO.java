package lk.ijse.AAD.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private long id;
    private String fName;
    private String lName;
    private String dob;
    private String address;

    public StudentDTO(long id, String fName, String lName, String dob, String address) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.dob = dob;
        this.address = address;
    }
    private long SchoolId;
}
