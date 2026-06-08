package lk.ijse.AAD.service;

import lk.ijse.AAD.dto.CustomerDTO;
import lk.ijse.AAD.dto.CustomerSaveDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerSaveDTO customerSaveDTO);
    List<CustomerDTO> getCustomers();
    List<CustomerDTO> getAllCustomersOrders();
}
