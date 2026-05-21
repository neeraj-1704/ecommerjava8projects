package com.commerce.ecommerce_api.service;


import com.commerce.ecommerce_api.dto.request.OrderRequest;
import com.commerce.ecommerce_api.dto.response.OrderResponse;
import com.commerce.ecommerce_api.excpetions.ResourceNotFoundException;
import com.commerce.ecommerce_api.model.Order;
import com.commerce.ecommerce_api.model.Orderstatus;
import com.commerce.ecommerce_api.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        // 1. Map the DTO (Request) to the Entity
        Order order = new Order();
        // order.setCustomerId(request.getCustomerId());
        // order.setTotalAmount(request.getTotalAmount());
        order.setStatus(Orderstatus.PENDING); // Set default status

        // 2. Save to database
        Order savedOrder = orderRepository.save(order);

        // 3. Map the saved Entity back to a DTO (Response)
        return mapToResponse(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));

        return mapToResponse(order);
    }

    @Override
    @Transactional
    public OrderResponse updateOrderStatus(Long id, Orderstatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));

        // Update the status
        order.setStatus(status);

        // Save and return
        Order updatedOrder = orderRepository.save(order);
        return mapToResponse(updatedOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));

        orderRepository.delete(order);
    }

    // --- Helper Methods ---

    /**
     * Manual mapper to convert an Order Entity into an OrderResponse DTO.
     * Note: You can also use libraries like MapStruct or ModelMapper to automate this.
     */
    private OrderResponse mapToResponse(Order order) {
        OrderResponse response = new OrderResponse();
        // response.setId(order.getId());
        // response.setStatus(order.getStatus());
        // response.setTotalAmount(order.getTotalAmount());
        // Map other fields as necessary...
        return response;
    }

}
