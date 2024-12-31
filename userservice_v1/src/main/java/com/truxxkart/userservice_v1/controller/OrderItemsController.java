package com.truxxkart.userservice_v1.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.truxxkart.userservice_v1.entity.OrderItem;
import com.truxxkart.userservice_v1.service.OrderItemsService;

@RestController
@RequestMapping("/order-items")
public class OrderItemsController {
	
	@Autowired
	private OrderItemsService orderItemsService;
	
	@GetMapping("/id")
	public ResponseEntity<OrderItem> getOrderItemById(@RequestParam String orderItemId){
             OrderItem orderItem =orderItemsService.getOrderItemById(orderItemId);
              if(orderItem!=null) {
            	  return ResponseEntity.status(HttpStatus.OK).body(orderItem);
              }
		return null;
	}
	
	@GetMapping("/status")
	public ResponseEntity<List<OrderItem>> getOrderItemsByStatus(@RequestParam String status){
              List<OrderItem> orderItemList =orderItemsService.getOrderItemsByStatus(status);
              if(orderItemList!=null) {
            	  return ResponseEntity.status(HttpStatus.OK).body(orderItemList);
              }
		return null;
	}
	
	@GetMapping("/creation-date")
	public ResponseEntity<List<OrderItem>> getOrderItemsByCreationDate(@RequestParam LocalDate creationDate){
              List<OrderItem> orderItemList =orderItemsService.getOrderItemsByCreationDate(creationDate);
              if(orderItemList!=null) {
            	  return ResponseEntity.status(HttpStatus.OK).body(orderItemList);
              }
		return null;
	}
	
	@GetMapping("/updation-date")
	public ResponseEntity<List<OrderItem>> getOrderItemsByUpdationDate(@RequestParam LocalDate updationDate){
              List<OrderItem> orderItemList =orderItemsService.getOrderItemsByUpdationDate(updationDate);
              if(orderItemList!=null) {
            	  return ResponseEntity.status(HttpStatus.OK).body(orderItemList);
              }
		return null;
	}
	
	@GetMapping("/activation-verification")
	public ResponseEntity<List<OrderItem>> getOrderItemsByVerificationOrActivation(@RequestParam String field,@RequestParam Boolean findBy){
              List<OrderItem> orderItemList =orderItemsService.getOrderItemsByVerificationOrActivation(field, findBy);
              if(orderItemList!=null) {
            	  return ResponseEntity.status(HttpStatus.OK).body(orderItemList);
              }
		return null;
	}

	@GetMapping("/order-id")
	public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@RequestParam String orderId){
              List<OrderItem> orderItemList =orderItemsService.getOrderItemsByOrderId(orderId);
              if(orderItemList!=null) {
            	  return ResponseEntity.status(HttpStatus.OK).body(orderItemList);
              }
		return null;
	}
	
	@PutMapping("/update/status")
	public ResponseEntity<OrderItem> updateOrderItemStatus(@RequestParam String orderItemId,@RequestParam String status){
        OrderItem orderItem =orderItemsService.updateOrderItemStatus(orderItemId, status);
         if(orderItem!=null) {
       	  return ResponseEntity.status(HttpStatus.OK).body(orderItem);
         }
	return null;
}

	@PutMapping("/update/activation-verification")
	public ResponseEntity<OrderItem> updateOrderItemVerificationOrActivation(@RequestParam String orderItemId,@RequestParam String field,@RequestParam Boolean toBeUpdated){
        OrderItem orderItem =orderItemsService.updateOrderItemVerificationOrActivation(orderItemId, field, toBeUpdated);
         if(orderItem!=null) {
       	  return ResponseEntity.status(HttpStatus.OK).body(orderItem);
         }
	return null;
}
	@GetMapping("/date/status")
	public ResponseEntity<List<OrderItem>> getOrderItemsByStatusAndDate(@RequestParam String field , @RequestParam LocalDate creationDate,@RequestParam String status){
              List<OrderItem> orderItemList =orderItemsService.getOrderItemsByStatusAndDate(status, field, creationDate);
              if(orderItemList!=null) {
            	  return ResponseEntity.status(HttpStatus.OK).body(orderItemList);
              }
		return null;
	}
}
