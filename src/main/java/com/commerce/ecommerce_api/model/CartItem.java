package com.commerce.ecommerce_api.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cartItem")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Added comma
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false) // Added comma
    private Product product;

    // ✅ Fix
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @CreationTimestamp
    private LocalDateTime createdAt; // Fixed spelling

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
