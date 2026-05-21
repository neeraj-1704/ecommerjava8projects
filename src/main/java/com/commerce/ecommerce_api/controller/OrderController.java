package com.commerce.ecommerce_api.controller;

import com.commerce.ecommerce_api.dto.request.OrderRequest;   // Added missing import
import com.commerce.ecommerce_api.dto.response.OrderResponse; // Added missing import
import com.commerce.ecommerce_api.model.Orderstatus;         // Fixed casing (Capital S)
import com.commerce.ecommerce_api.service.OrderService;       // Added missing import
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;                   // Added missing import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;             // Added missing annotation imports
import java.util.List;                                        // Added missing import

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody OrderRequest request) {

        OrderResponse response = orderService.createOrder(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody Orderstatus status) { // Changed @RequestParam to @RequestBody

        return ResponseEntity.ok(
                orderService.updateOrderStatus(id, status)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(
            @PathVariable Long id) {

        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }
}