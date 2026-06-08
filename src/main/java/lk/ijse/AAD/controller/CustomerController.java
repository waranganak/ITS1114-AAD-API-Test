package lk.ijse.AAD.controller;

import lk.ijse.AAD.dto.CustomerDTO;
import lk.ijse.AAD.dto.CustomerSaveDTO;
import lk.ijse.AAD.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO saveCustomer(@RequestBody CustomerSaveDTO customerSaveDTO) {
        return customerService.saveCustomer(customerSaveDTO);
    }

    //get all customers
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getCustomers();
    }

    //get all customers with their orders
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/customers-orders/all")
    public List<CustomerDTO> getAllCustomersOrderDetails() {
        return customerService.getAllCustomersOrders();
    }


}
