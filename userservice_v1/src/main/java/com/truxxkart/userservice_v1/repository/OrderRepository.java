package com.truxxkart.userservice_v1.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truxxkart.userservice_v1.entity.Orders;
@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {
	Optional<List<Orders>> findByUserId(Long userId);
	Optional<List<Orders>> findByPaymentMode(String paymentMode);
	Optional<Orders> findByPaymentId(String paymentId);
}
