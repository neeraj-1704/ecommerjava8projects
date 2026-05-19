package com.commerce.ecommerce_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {

    private Long id;
    // Flattened Product details
    private Long productId;
    private String productName;
    private Integer quantity;
    // Financials
    private BigDecimal unitPrice;
    private BigDecimal subTotal; // Usually calculated in the service layer: quantity * unitPrice

    // Audit fields
    private LocalDateTime addedAt;
}
