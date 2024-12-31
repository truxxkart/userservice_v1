package com.truxxkart.userservice_v1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truxxkart.userservice_v1.entity.OrderItem;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

	// Find all items for a specific product across orders
    List<OrderItem> findByProductId(Long productId);

    // Find all items for a specific order
    Optional<List<OrderItem>> findByOrderId(String orderId);
    Optional<List<OrderItem>> findByStatus(String status);
}
