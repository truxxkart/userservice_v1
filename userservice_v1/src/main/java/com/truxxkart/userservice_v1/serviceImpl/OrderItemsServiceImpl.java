package com.truxxkart.userservice_v1.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.userservice_v1.entity.OrderItem;
import com.truxxkart.userservice_v1.repository.OrderItemRepository;
import com.truxxkart.userservice_v1.service.OrderItemsService;
@Service
public class OrderItemsServiceImpl implements OrderItemsService {
	@Autowired
	private OrderItemRepository orderItemsRepo;

	@Override
	public OrderItem getOrderItemById(String orderItemId) {
		Optional<OrderItem> optOrderItem =orderItemsRepo.findById(orderItemId);
		if(optOrderItem.isPresent()) {
			return optOrderItem.get();
		}
		return null;
	}

	@Override
	public List<OrderItem> getOrderItemsByStatus(String status) {
		Optional<List<OrderItem>> optOrderItem =orderItemsRepo.findByStatus(status);
		if(optOrderItem.isPresent()) {
			return optOrderItem.get();
		}
		return null;
	}

	@Override
	public List<OrderItem> getOrderItemsByCreationDate(LocalDate creationDate) {
		return orderItemsRepo.findAll().stream().filter(oi->oi.getCreatedAt().toLocalDate().equals(creationDate)).collect(Collectors.toList());
		
	}

	@Override
	public List<OrderItem> getOrderItemsByUpdationDate(LocalDate updationDate) {
		return orderItemsRepo.findAll().stream().filter(oi->oi.getUpdatedAt().toLocalDate().equals(updationDate)).collect(Collectors.toList());
		
	}

	@Override
	public List<OrderItem> getOrderItemsByVerificationOrActivation(String field, Boolean findBy) {
		if(field.equalsIgnoreCase("VERIFICATION")) {
			return orderItemsRepo.findAll().stream().filter(oi->oi.getIsVerified()==findBy).collect(Collectors.toList());	
		} else if(field.equalsIgnoreCase("ACTIVATION")) {
			return orderItemsRepo.findAll().stream().filter(oi->oi.getIsActive()==findBy).collect(Collectors.toList());	
		}
		return null;
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(String orderId) {
		return orderItemsRepo.findAll().stream().filter(oi->oi.getOrder().getId().equalsIgnoreCase(orderId)).collect(Collectors.toList());	
	}

	@Override
	public OrderItem updateOrderItemStatus(String orderItemId, String status) {
		Optional<OrderItem> optOrderItem =orderItemsRepo.findById(orderItemId);
		if(optOrderItem.isPresent()) {
			OrderItem orderItem = optOrderItem.get();
			orderItem.setStatus(status);
			return orderItemsRepo.save(orderItem);
		}
		return null;
	}

	@Override
	public OrderItem updateOrderItemVerificationOrActivation(String orderItemId, String field, Boolean toBeUpdated) {
		Optional<OrderItem> optOrderItem =orderItemsRepo.findById(orderItemId);
		if(optOrderItem.isPresent()) {
			OrderItem orderItem = optOrderItem.get();
			if(field.equalsIgnoreCase("VERIFICATION")) {
				orderItem.setIsVerified(toBeUpdated);
			}else if(field.equalsIgnoreCase("ACTIVATION")) {
				orderItem.setIsActive(toBeUpdated);
			}
			return orderItemsRepo.save(orderItem);
		}
		return null;
	}

	@Override
	public List<OrderItem> getOrderItemsByStatusAndDate(String status, String field, LocalDate date) {
		List<OrderItem> orderItems=null;
		if(field.equalsIgnoreCase("CREATION")) {
			orderItems=getOrderItemsByCreationDate(date);
		} else if(field.equalsIgnoreCase("UPDATION")) {
			orderItems=getOrderItemsByUpdationDate(date);
		}
	return	orderItems.stream().filter(oi->oi.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
		
	}

}
