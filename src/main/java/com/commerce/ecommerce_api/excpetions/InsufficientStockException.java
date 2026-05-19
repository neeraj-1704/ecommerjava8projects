package com.commerce.ecommerce_api.excpetions;

// exception/InsufficientStockException.java
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}