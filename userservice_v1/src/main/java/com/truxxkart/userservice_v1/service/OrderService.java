package com.truxxkart.userservice_v1.service;

import java.time.LocalDate;
import java.util.List;

import com.truxxkart.userservice_v1.entity.Orders;

public interface OrderService {
	
	//IMPEMENT LOGIC AFTER PROMO CHG IN CARTITEM
	public Orders createOrderFromCart(Long userId ,Long addressId ,String paymentMode,String paymentId);
	public List<Orders> findOrderOfUser(Long userId);
	public List<Orders> findOrdersByCreationDate(LocalDate date);
	public List<Orders> findOrdersByUpdationDate(LocalDate date);
	public Orders getOrdersByOrderId(String orderId);
	public Orders getOrdersByPaymentId(String paymentId);
	public List<Orders> findOrdersByPaymentMode(String paymentMode);
	public String deleteOrdersByOrderId(String orderId);
	
			
}
