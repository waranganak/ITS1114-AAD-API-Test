package lk.ijse.AAD.dto;

import lk.ijse.AAD.enumaration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    private long id;
    private String firstName;
    private String lastName;
    private Date dob;
    private UserStatus status;
}
