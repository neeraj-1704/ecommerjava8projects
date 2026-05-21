package com.commerce.ecommerce_api.controller;

import com.commerce.ecommerce_api.dto.request.CartItemRequest;
import com.commerce.ecommerce_api.dto.response.CartItemResponse;
import com.commerce.ecommerce_api.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // ==========================================
    // 1. CREATE: Add Item to Cart
    // ==========================================
    @PostMapping("/items")
    public ResponseEntity<CartItemResponse> addToCart(@Valid @RequestBody CartItemRequest request) {
        CartItemResponse response = cartService.addToCart(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // HTTP 201 Created
    }

    // ==========================================
    // 2. READ: Get a Specific Cart Item by ID
    // ==========================================
    @GetMapping("/items/{id}")
    public ResponseEntity<CartItemResponse> getCartItem(@PathVariable Long id) {
        CartItemResponse response = cartService.getCartItem(id);
        return ResponseEntity.ok(response); // HTTP 200 OK
    }

    // ==========================================
    // 3. UPDATE: Change Quantity of an Item in Cart
    // ==========================================
    @PutMapping("/items/{id}")
    public ResponseEntity<CartItemResponse> updateCartItemQuantity(
            @PathVariable Long id,
            @Valid @RequestBody CartItemRequest request) {

        // Passing both the ID from the path and the updated body details to the service
        CartItemResponse response = cartService.updateCartItemQuantity(id, request);
        return ResponseEntity.ok(response); // HTTP 200 OK
    }

    // ==========================================
    // 4. DELETE: Remove a Specific Item from Cart
    // ==========================================
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long id) {
        cartService.removeCartItem(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    // ==========================================
    // 5. DELETE: Clear Entire Cart / Reset
    // ==========================================
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart() {
        cartService.clearEntireCart();
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}