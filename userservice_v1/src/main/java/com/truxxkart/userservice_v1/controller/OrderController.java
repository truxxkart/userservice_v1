package com.truxxkart.userservice_v1.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.truxxkart.userservice_v1.entity.Orders;
import com.truxxkart.userservice_v1.serviceImpl.OrderServiceImpl;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;
    
    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Orders>> findOrdersOfUser(@PathVariable Long userId) {
    	List<Orders> ordersList= orderService.findOrderOfUser(userId);
    	 return ResponseEntity.status(HttpStatus.OK).body(ordersList);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Orders> createOrder(@RequestParam Long userId ,@RequestParam Long addressId , @RequestParam String paymentMode , @RequestParam String paymentId) {
        Orders orders = orderService.createOrderFromCart(userId, addressId, paymentMode, paymentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orders);
    }
    
    @GetMapping("/creation-date")
    public ResponseEntity<List<Orders>> findOrdersByCreationDate(@RequestParam LocalDate date ){
    	List<Orders> ordersList= orderService.findOrdersByCreationDate(date);
    	 return ResponseEntity.status(HttpStatus.OK).body(ordersList);
    }
    @GetMapping("/updation-date")
    public ResponseEntity<List<Orders>> findOrdersByUpdationDate(@RequestParam LocalDate date ){
    	List<Orders> ordersList= orderService.findOrdersByUpdationDate(date);
    	 return ResponseEntity.status(HttpStatus.OK).body(ordersList);
    }
    @GetMapping("/order-id/{orderId}")
    public ResponseEntity<Orders> getOrdersByOrderId(@PathVariable String orderId) {
        Orders orders = orderService.getOrdersByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orders);
    
    }
    @GetMapping("/payment-id/{paymentId}")
    public ResponseEntity<Orders> getOrdersByPaymentId(@PathVariable String paymentId) {
        Orders orders = orderService.getOrdersByPaymentId(paymentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orders);
    
    }
    @GetMapping("/payment-mode")
    public ResponseEntity<List<Orders>> findOrdersByCreationDate(@RequestParam String paymentMode ){
    	List<Orders> ordersList= orderService.findOrdersByPaymentMode(paymentMode);
    	 return ResponseEntity.status(HttpStatus.OK).body(ordersList);
    }
    
    @DeleteMapping("/remove/{orderId}")
    public ResponseEntity<String> deletOrdersByOrdersId(@PathVariable String orderId) {
        String delMsg = orderService.deleteOrdersByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(delMsg);
    
    }
    
}
