package com.commerce.ecommerce_api.dto.request;
import com.commerce.ecommerce_api.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private String age;
    private AddressDto addressDto;

}
