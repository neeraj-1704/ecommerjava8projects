    package com.commerce.ecommerce_api.Mapper;


    import com.commerce.ecommerce_api.dto.AddressDto;
    import com.commerce.ecommerce_api.dto.request.UserRequest;
    import com.commerce.ecommerce_api.dto.response.UserResponse;
    import com.commerce.ecommerce_api.model.Address;
    import com.commerce.ecommerce_api.model.User;
    import org.springframework.stereotype.Component;


    @Component
    public class UserMapper {
        // Convert Request DTO -> Database Entity
        // Convert Request DTO -> Database Entity
        public User toEntity(UserRequest request) {
            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());

            // Hash the password here in a real application using PasswordEncoder!
            user.setPassword(request.getPassword());
            user.setAge(request.getAge());

            // Check if the AddressDto exists in the request
            if (request.getAddressDto() != null) {
                Address address = new Address();
                // Map all fields from AddressDto to Address entity
                address.setHouse_no(request.getAddressDto().getHouse_no());
                address.setStreet(request.getAddressDto().getStreet());
                address.setAddressLine1(request.getAddressDto().getAddressLine1());
                address.setAddressLine2(request.getAddressDto().getAddressLine2());
                address.setPincode(request.getAddressDto().getPincode());
                address.setCity(request.getAddressDto().getCity());
                address.setState(request.getAddressDto().getState());
                address.setCountry(request.getAddressDto().getCountry());
                // IMPORTANT: Link the Address back to the User (Required for CascadeType.ALL to work)
                address.setUser(user);
                // Add the single address to the user's address list
                user.getAddresses().add(address);
            }

            return user;
        }

        // Add this method inside your UserMapper class

        public void updateEntityFromRequest(UserRequest request, User existingUser) {
            // 1. Update basic fields only if they are provided
            if (request.getName() != null) {
                existingUser.setName(request.getName());
            }
            if (request.getEmail() != null) {
                existingUser.setEmail(request.getEmail());
            }
            if (request.getAge() != null) {
                existingUser.setAge(request.getAge());
            }
            if (request.getPassword() != null && !request.getPassword().isEmpty()) {
                existingUser.setPassword(request.getPassword()); // Remember to hash this!
            }

            // 2. Update Address Logic
            if (request.getAddressDto() != null) {
                // Clear existing addresses (orphanRemoval = true in User.java will delete the old one from DB)
                existingUser.getAddresses().clear();

                Address newAddress = new Address();

                // Map all fields from AddressDto to new Address entity
                newAddress.setHouse_no(request.getAddressDto().getHouse_no());
                newAddress.setStreet(request.getAddressDto().getStreet());
                newAddress.setAddressLine1(request.getAddressDto().getAddressLine1());
                newAddress.setAddressLine2(request.getAddressDto().getAddressLine2());
                newAddress.setPincode(request.getAddressDto().getPincode());
                newAddress.setCity(request.getAddressDto().getCity());
                newAddress.setState(request.getAddressDto().getState());
                newAddress.setCountry(request.getAddressDto().getCountry());

                // IMPORTANT: Link the Address back to the existing User
                newAddress.setUser(existingUser);

                // Add the single new address to the user's address list
                existingUser.getAddresses().add(newAddress);
            }
        }

        // Convert Database Entity -> Response DTO
        public UserResponse toResponse(User user) {
            if (user == null) {
                return null;
            }
            UserResponse response = new UserResponse();
            response.setId(String.valueOf(user.getId()));
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setAge(user.getAge());
            response.setUserRole(user.getRole());

            // Extract the FIRST address from the list to put into the response DTO
            if (user.getAddresses() != null && !user.getAddresses().isEmpty()) {
                Address address = user.getAddresses().get(0); // Safely grab the first address
                AddressDto addressDto = new AddressDto();
                // Map all fields from Address entity to AddressDto
                addressDto.setHouse_no(address.getHouse_no());
                addressDto.setStreet(address.getStreet());
                addressDto.setAddressLine1(address.getAddressLine1());
                addressDto.setAddressLine2(address.getAddressLine2());
                addressDto.setPincode(address.getPincode());
                addressDto.setCity(address.getCity());
                addressDto.setState(address.getState());
                addressDto.setCountry(address.getCountry());
                // Link the populated DTO to the response object
                response.setAddressDto(addressDto);
            }

            return response;
        }

    }


