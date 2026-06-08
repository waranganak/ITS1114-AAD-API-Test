package lk.ijse.AAD.dto;

import jakarta.persistence.OneToMany;
import lk.ijse.AAD.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private long id;
    private String name;
    private String phone;
    private String address;


    private List<OrderDTO> orders;
}
