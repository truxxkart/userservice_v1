package com.truxxkart.userservice_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truxxkart.userservice_v1.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {
}
