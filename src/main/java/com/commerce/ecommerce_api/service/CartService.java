package com.commerce.ecommerce_api.service;

import com.commerce.ecommerce_api.dto.request.CartItemRequest;
import com.commerce.ecommerce_api.dto.response.CartItemResponse;
import com.commerce.ecommerce_api.excpetions.*; // This now safely covers your BadRequestException!
import com.commerce.ecommerce_api.model.CartItem;
import com.commerce.ecommerce_api.model.Product;
import com.commerce.ecommerce_api.repository.ProductRepository;
import com.commerce.ecommerce_api.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemResponse addToCart(CartItemRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found")
                );

        // Stock validation - Now successfully uses your custom runtime exception!
        if (product.getStockQuntity() < request.getQuantity()) {
            throw new BadRequestException("Insufficient stock");
        }

        // Create entity (Make sure to capture the current price of the product here!)
        CartItem cartItem = CartItem.builder()
                .product(product)
                .quantity(request.getQuantity())
                .price(new BigDecimal(product.getPrice())) // Crucial: sets the current snapshot price in the CartItem
                .build();

        CartItem savedCartItem = cartRepository.save(cartItem);

        // Convert to response DTO
        return mapToResponse(savedCartItem);
    }

    public CartItemResponse getCartItem(Long id) {

        CartItem cartItem = cartRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart item not found")
                );

        return mapToResponse(cartItem);
    }

    public void removeCartItem(Long id) {

        CartItem cartItem = cartRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart item not found")
                );

        cartRepository.delete(cartItem);
    }

    // Add this method inside CartService.java for handling Updates
    public CartItemResponse updateCartItemQuantity(Long id, CartItemRequest request) {
        CartItem cartItem = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        // Optional: Re-validate stock limitations if quantity increases
        if (cartItem.getProduct().getStockQuntity() < request.getQuantity()) {
            throw new BadRequestException("Insufficient stock available");
        }

        cartItem.setQuantity(request.getQuantity());
        CartItem updatedItem = cartRepository.save(cartItem);

        return mapToResponse(updatedItem);
    }

    // Add this method inside CartService.java for clearing the cart
    public void clearEntireCart() {
        // If multi-tenant, pass user context details to selectively delete items by user id
        cartRepository.deleteAll();
    }
    private CartItemResponse mapToResponse(CartItem cartItem) {
        // 1. Calculate subtotal using the CartItem's own price field
        BigDecimal subTotal = cartItem.getPrice()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));

        // 2. Map fields directly to the Response DTO
        return CartItemResponse.builder()
                .id(cartItem.getId())
                .productId(cartItem.getProduct().getId())
                .productName(cartItem.getProduct().getName())
                .quantity(cartItem.getQuantity())
                .unitPrice(cartItem.getPrice())
                .subTotal(subTotal)
                .addedAt(cartItem.getCreatedAt())
                .build();
    }
}