package com.truxxkart.userservice_v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truxxkart.userservice_v1.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Optional<Cart> findByUserId(Long userId);
    Optional<Cart> findByCartId(String cartId);
}
