package com.truxxkart.userservice_v1.service;

import java.time.LocalDate;
import java.util.List;

import com.truxxkart.userservice_v1.entity.Cart;

public interface CartService {
	
	// IMPLEMENT CODE LOGIC  FOR PROMO
	 public Cart getOrCreateCart(Long userId);
	 public Cart addItemToCart(Long userId, Long productId,Long productVariantId,Long productSizeId, Double price);
	 public Cart updateCartItems(String cartId, String cartItemId, String field,Long toBeUpdated,Double price);
	 public Cart removeItemFromCart(String cartId, String itemId);
	 public Cart removeAllItemFromCart(String cartId);
	 public Cart incrementItemFromCart(String cartId, String itemId);
	 public Cart decrementItemFromCart(String cartId, String itemId);
	 public Cart getCartById(String cartId);
	 public List<Cart> fetchCartsByUpdationDate(LocalDate date);

}
