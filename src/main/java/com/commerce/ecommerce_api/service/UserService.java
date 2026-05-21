    package com.commerce.ecommerce_api.service;


    import com.commerce.ecommerce_api.Mapper.UserMapper;
    import com.commerce.ecommerce_api.dto.request.UserRequest;

    import com.commerce.ecommerce_api.dto.response.UserResponse;
    import com.commerce.ecommerce_api.excpetions.UserAlreadyExistsException;

    import com.commerce.ecommerce_api.model.User;
    import com.commerce.ecommerce_api.repository.UserRepository;
    import lombok.AllArgsConstructor;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    @AllArgsConstructor
    public class UserService {


        private final UserRepository userRepository;
        private final UserMapper userMapper;

        // create user
        // ==========================================
        // 1. CREATE
        // ==========================================
        public UserResponse createUser(UserRequest userRequest) {
            // 1. If the user already exists, throw exception
            // (Assuming you have a method boolean existsByEmail(String email) in your repository)
            if (userRepository.existsByEmail(userRequest.getEmail())) {
                throw new UserAlreadyExistsException(
                        "A user with email " + userRequest.getEmail() + " already exists."
                );
            }
            // 2. Convert the UserRequest DTO into the User object using the mapper class
            User userEntity = userMapper.toEntity(userRequest);
            // 3. Save the entity to the database
            User savedUser = userRepository.save(userEntity);
            // 4. Convert the saved User entity back into a UserResponse DTO and return it
            return userMapper.toResponse(savedUser);

        }
        // ==========================================
        // 2. UPDATE
        // ==========================================
        public UserResponse updateUser(Long id, UserRequest userRequest) {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found")); // Replace with Custom Exception

            // 2. Use the Mapper to apply changes from the request directly onto the existing user
            userMapper.updateEntityFromRequest(userRequest, existingUser);
            // Update other fields as necessary...
            User updatedUser = userRepository.save(existingUser);
            return userMapper.toResponse(updatedUser);
        }

        // get all
        public List<UserResponse> getAllUsers() {
            return userRepository.findAll().stream()
                    .map(userMapper::toResponse)
                    .collect(Collectors.toList()); // or .collect(Collectors.toList()) for older Java versions
        }

        // get by id
        public UserResponse getUserById(Long id) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found")); // Replace with Custom Exception
            return userMapper.toResponse(user);
        }

        // delete user
        public void deleteUser(Long id) {
            if (!userRepository.existsById(id)) {
                throw new RuntimeException("User not found"); // Replace with Custom Exception
            }
            userRepository.deleteById(id);
        }
    }
