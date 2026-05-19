package com.commerce.ecommerce_api.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ErrorDetails {
    private String timestamp;
    private String message;
    private String details;
}
