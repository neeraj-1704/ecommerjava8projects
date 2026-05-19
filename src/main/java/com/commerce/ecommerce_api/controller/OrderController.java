//package com.commerce.ecommerce_api.controller;
//
//
//import com.commerce.ecommerce_api.model.Order;
//import com.commerce.ecommerce_api.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @PostMapping
//    public ResponseEntity<Order> checkout(@RequestParam Long productId, @RequestParam int quantity) {
//        // If an exception occurs inside the service, execution stops immediately,
//        // bypasses this return statement, and flies straight to the Global Handler.
//        Order savedOrder = orderService.placeOrder(productId, quantity);
//        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
//    }
//
//}
