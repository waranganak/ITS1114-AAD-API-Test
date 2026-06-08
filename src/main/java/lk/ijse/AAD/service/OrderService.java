package lk.ijse.AAD.service;


import lk.ijse.AAD.dto.OrderDTO;
import lk.ijse.AAD.dto.OrderSaveDTO;

import java.util.List;

public interface OrderService {
    OrderDTO saveOrder(OrderDTO orderDTO);
    OrderDTO saveOrder(OrderSaveDTO orderSaveDTO);
    List<OrderDTO> getAllOrders();
}
