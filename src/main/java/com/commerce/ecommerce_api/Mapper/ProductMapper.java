package com.commerce.ecommerce_api.Mapper;


import com.commerce.ecommerce_api.dto.request.ProductRequest;
import com.commerce.ecommerce_api.dto.response.ProductResponse;
import com.commerce.ecommerce_api.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapToEntityProduct(ProductRequest request) {

        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stockQuntity(request.getStockQuntity())
                .category(request.getCategory())
                .image(request.getImage())
                .build();
    }

    public ProductResponse mapProductToResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuntity(product.getStockQuntity())
                .category(product.getCategory())
                .image(product.getImage())
                .build();
    }
}