package lk.ijse.AAD.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderSaveDTO {
    private String orderDate;
    private double price;
    private long customerId;
}
