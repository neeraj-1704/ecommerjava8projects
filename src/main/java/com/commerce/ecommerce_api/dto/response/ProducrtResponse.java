package com.commerce.ecommerce_api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProducrtResponse {
    private Long id;
    private String name;
    private String description;
    private String price;
    private String stockQuntity;
    private String category;
    private String image;
}
