package lk.ijse.AAD.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerSaveDTO {
    private String name;
    private String phone;
    private String address;
}
