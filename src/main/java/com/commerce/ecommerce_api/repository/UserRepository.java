package com.commerce.ecommerce_api.repository;

import com.commerce.ecommerce_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring automatically implements this based on the method name
    boolean existsByEmail(String email);
}
