package com.truxxkart.userservice_v1.service;

import java.time.LocalDate;
import java.util.List;

import com.truxxkart.userservice_v1.entity.OrderItem;


public interface OrderItemsService {
//	public List<OrderItem> getOrderByDateAndStatus(String status,LocalDate date);
    public OrderItem getOrderItemById(String orderItemId);
    public List<OrderItem> getOrderItemsByStatus(String status);
    public List<OrderItem> getOrderItemsByStatusAndDate(String status,String field ,LocalDate date);
    
    public List<OrderItem> getOrderItemsByCreationDate(LocalDate creationDate);
    public List<OrderItem> getOrderItemsByUpdationDate(LocalDate updationDate);
    public List<OrderItem> getOrderItemsByVerificationOrActivation(String field,Boolean findBy);
    public List<OrderItem> getOrderItemsByOrderId(String orderId);
    
    public OrderItem updateOrderItemStatus(String orderItemId,String status);
    public OrderItem updateOrderItemVerificationOrActivation(String orderItemId,String field,Boolean toBeUpdated);
}
