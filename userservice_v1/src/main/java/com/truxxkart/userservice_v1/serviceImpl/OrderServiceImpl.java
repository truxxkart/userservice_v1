package com.truxxkart.userservice_v1.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.userservice_v1.entity.Cart;
import com.truxxkart.userservice_v1.entity.CartItem;
import com.truxxkart.userservice_v1.entity.Orders;
import com.truxxkart.userservice_v1.entity.OrderItem;
import com.truxxkart.userservice_v1.repository.CartRepository;
import com.truxxkart.userservice_v1.repository.OrderItemRepository;
import com.truxxkart.userservice_v1.repository.OrderRepository;
import com.truxxkart.userservice_v1.service.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
	   @Autowired
	    private CartRepository cartRepository;

	    @Autowired
	    private OrderRepository orderRepository;

	    @Autowired
	    private OrderItemRepository orderItemRepository;
	@Override
	@Transactional
	public Orders createOrderFromCart(Long userId,Long addressId ,String paymentMode,String paymentId) {
		Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot place an order.");
        }

        Orders order = new Orders();
        order.setUserId(userId);
        order.setAddressId(addressId);
//        order.setOrderDate(LocalDateTime.now());
//        order.setStatus("PENDING");
        order.setPaymentId(paymentId);
        order.setPaymentMode(paymentMode);
        order.setTotalOrderAmount(cart.getTotalCartPrice());
        order = orderRepository.save(order);

        // Add items from the cart to the order
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setProductVariantId(cartItem.getProductVariantId());
            orderItem.setProductSizeId(cartItem.getProductSizeId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setStatus("PENDING");
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItemRepository.save(orderItem);
        }

        // Clear the cart
        cart.getCartItems().clear();
        cart.setTotalCartPrice(0.0);
        cartRepository.save(cart);

        return order;
	}
	@Override
	public List<Orders> findOrderOfUser(Long userId) {
		Optional<List<Orders>> optOrders =orderRepository.findByUserId(userId);
		if(optOrders.isPresent()) {
			return optOrders.get();
		}
		return null;
	}
	@Override
	public List<Orders> findOrdersByCreationDate(LocalDate date) {
	return	orderRepository.findAll().stream().filter(order->order.getCreatedAt().toLocalDate().equals(date)).collect(Collectors.toList());
		
	}
	@Override
	public Orders getOrdersByOrderId(String orderId) {
		Optional<Orders> optOrders=orderRepository.findById(orderId);
		if(optOrders.isPresent()) {
			return optOrders.get();
		}
		return null;
	}
	@Override
	public List<Orders> findOrdersByPaymentMode(String paymentMode) {
		Optional<List<Orders>> optOrders=orderRepository.findByPaymentMode(paymentMode);
		if(optOrders.isPresent()) {
			return optOrders.get();
		}
		return null;	}
	@Override
	public Orders getOrdersByPaymentId(String paymentId) {
		Optional<Orders> optOrders=orderRepository.findByPaymentId(paymentId);
		if(optOrders.isPresent()) {
			return optOrders.get();
		}
		return null;
	}
	@Override
	public List<Orders> findOrdersByUpdationDate(LocalDate date) {
		return	orderRepository.findAll().stream().filter(order->order.getUpdatedAt().toLocalDate().equals(date)).collect(Collectors.toList());
		
	}
	@Override
	public String deleteOrdersByOrderId(String orderId) {
		orderRepository.deleteById(orderId);
		return "Order is deleted Successfully";
	}
	

}
