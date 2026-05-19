package com.commerce.ecommerce_api.dto.response;

import com.commerce.ecommerce_api.model.Orderstatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private Long userId;
    private BigDecimal totalAmount;
    private Orderstatus status;
    private LocalDateTime createdAt;

    // We send back a list of formatted item responses
    private List<OrderItemResponse> items;
}