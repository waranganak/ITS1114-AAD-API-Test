package lk.ijse.AAD.service.impl;

import lk.ijse.AAD.dto.CustomerDTO;
import lk.ijse.AAD.dto.CustomerSaveDTO;
import lk.ijse.AAD.dto.OrderDTO;
import lk.ijse.AAD.entity.Customer;
import lk.ijse.AAD.repository.CustomerRepository;
import lk.ijse.AAD.repository.OrderRepository;
import lk.ijse.AAD.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;
    OrderRepository orderRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerSaveDTO customerSaveDTO) {
        try {
            log.info("Saving customer: {}", customerSaveDTO);
            Customer customer = new Customer();
            customer.setName(customerSaveDTO.getName());
            customer.setPhone(customerSaveDTO.getPhone());
            customer.setAddress(customerSaveDTO.getAddress());

            Customer savedCustomer = customerRepository.save(customer);

            CustomerDTO savedCustomerDTO = new CustomerDTO();
            savedCustomerDTO.setId(savedCustomer.getId());
            savedCustomerDTO.setName(savedCustomer.getName());
            savedCustomerDTO.setPhone(savedCustomer.getPhone());
            savedCustomerDTO.setAddress(savedCustomer.getAddress());

            return savedCustomerDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<CustomerDTO> getCustomers() {
        try {
            log.info("Fetching all customers");
            List<Customer> customers = customerRepository.findAll();

            return customers.stream().map(customer -> {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setId(customer.getId());
                customerDTO.setName(customer.getName());
                customerDTO.setPhone(customer.getPhone());
                customerDTO.setAddress(customer.getAddress());
                return customerDTO;
            }).toList();

        } catch (Exception e) {
            log.error("Error occurred while fetching customers: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersOrders() {
        try {
            log.info("Fetching all customers orders");
            List<Customer> customers = customerRepository.findAll();

            return customers.stream().map(customer -> {
                CustomerDTO customerDTO = new CustomerDTO();

                customerDTO.setId(customer.getId());
                customerDTO.setName(customer.getName());
                customerDTO.setPhone(customer.getPhone());
                customerDTO.setAddress(customer.getAddress());

                // set orders to the customerDTO
                customerDTO.setOrders(customer.getOrders().stream().map(order -> {
                    OrderDTO orderDTO = new OrderDTO();

                    orderDTO.setId(order.getId());
                    orderDTO.setOrderDate(order.getOrderDate());
                    orderDTO.setPrice(order.getPrice());
                    return orderDTO;
                }).toList());
                return customerDTO;
            }).toList();
        } catch (Exception e) {
            log.error("Error occurred while fetching customers orders: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
