package lk.ijse.AAD.service.impl;

import lk.ijse.AAD.dto.OrderDTO;
import lk.ijse.AAD.dto.OrderSaveDTO;
import lk.ijse.AAD.entity.Customer;
import lk.ijse.AAD.entity.Order;
import lk.ijse.AAD.repository.CustomerRepository;
import lk.ijse.AAD.repository.OrderRepository;
import lk.ijse.AAD.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    CustomerRepository customerRepository;
    OrderRepository orderRepository;

    public OrderServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        try {
            log.info("Saving order: {}", orderDTO);
            Optional<Customer> optionalCustomer = customerRepository.findById(orderDTO.getCustomerId());
            if (!optionalCustomer.isPresent()) {
                log.error("Customer with ID {} not found", orderDTO.getCustomerId());
                throw new RuntimeException("Customer not found");
            }
            Customer customer = optionalCustomer.get();
            Order order = new Order();
            order.setOrderDate(orderDTO.getOrderDate());
            order.setCustomer(customer);
            order.setPrice(orderDTO.getPrice());

            Order savedOrder = orderRepository.save(order);
            OrderDTO savedOrderDTO = new OrderDTO();
            savedOrderDTO.setId(savedOrder.getId());
            savedOrderDTO.setOrderDate(savedOrder.getOrderDate());
            savedOrderDTO.setCustomerId(savedOrder.getCustomer().getId());
            savedOrderDTO.setPrice(savedOrder.getPrice());

            return savedOrderDTO;

        } catch (Exception e) {
            log.error("Error occurred while saving order: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDTO saveOrder(OrderSaveDTO orderSaveDTO) {
        try {
            log.info("Saving order: {}", orderSaveDTO);

            //get the Customer for the given customerId
            Optional<Customer> optionalCustomer = customerRepository.findById(orderSaveDTO.getCustomerId());

            //check if customer exists, if not log an error and throw an exception
            if (!optionalCustomer.isPresent()) {

                log.error("Customer with ID {} not found", orderSaveDTO.getCustomerId());
                throw new RuntimeException("Customer not found");

            }

            Customer customer = optionalCustomer.get();

            Order order = new Order();
            order.setOrderDate(orderSaveDTO.getOrderDate());
            order.setPrice(orderSaveDTO.getPrice());
            //set the customer to the order
            order.setCustomer(customer);

            // save order
            Order savedOrder = orderRepository.save(order);

            // setup return dto (OrderDTO).it will return saved order with id
            OrderDTO savedOrderDTO = new OrderDTO();
            savedOrderDTO.setId(savedOrder.getId());
            savedOrderDTO.setOrderDate(savedOrder.getOrderDate());
            savedOrderDTO.setPrice(savedOrder.getPrice());

            savedOrderDTO.setCustomerId(savedOrder.getCustomer().getId());


            return savedOrderDTO;

        } catch (Exception e) {
            log.error("Error occurred while saving order: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        try {
            log.info("Fetching all orders");
            List<Order> orders = orderRepository.findAll();

            return orders.stream().map(order -> {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setId(order.getId());
                orderDTO.setOrderDate(order.getOrderDate());
                orderDTO.setPrice(order.getPrice());
                orderDTO.setCustomerId(order.getCustomer().getId());
                return orderDTO;
            }).toList();

        } catch (Exception e) {
            log.error("Error occurred while fetching orders: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }


}

