package com.commerce.ecommerce_api.dto.response;
import com.commerce.ecommerce_api.dto.AddressDto;
import com.commerce.ecommerce_api.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String age;
    private Role userRole;
    private AddressDto addressDto;
}
