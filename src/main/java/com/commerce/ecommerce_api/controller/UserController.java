package com.commerce.ecommerce_api.controller;


import com.commerce.ecommerce_api.dto.request.UserRequest;
import com.commerce.ecommerce_api.dto.response.UserResponse;
import com.commerce.ecommerce_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {


    private final UserService userService;

    // ==========================================
    // 1. CREATE USER
    // ==========================================
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        UserResponse createdUser = userService.createUser(userRequest);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // ==========================================
    // 2. GET ALL USERS
    // ==========================================
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // ==========================================
    // 3. GET USER BY ID
    // ==========================================
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // ==========================================
    // 4. UPDATE USER
    // ==========================================
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest userRequest) {

        UserResponse updatedUser = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(updatedUser);
    }

    // ==========================================
    // 5. DELETE USER
    // ==========================================
    @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        // HttpStatus.NO_CONTENT (204) is the standard REST response for a successful deletion
        // Return a 200 OK status along with your custom message
        return ResponseEntity.ok("User with ID " + id + " was successfully deleted.");
    }
}
