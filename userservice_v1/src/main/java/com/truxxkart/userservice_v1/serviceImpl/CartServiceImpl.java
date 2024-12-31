package com.truxxkart.userservice_v1.serviceImpl;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxxkart.userservice_v1.entity.Cart;
import com.truxxkart.userservice_v1.entity.CartItem;
import com.truxxkart.userservice_v1.repository.CartItemRepository;
import com.truxxkart.userservice_v1.repository.CartRepository;
import com.truxxkart.userservice_v1.service.CartService;
@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public Cart getOrCreateCart(Long userId) {
		 Optional<Cart> cart = java.util.Optional.empty();
	        if (userId != null) {
	            cart = cartRepository.findByUserId(userId);
	        } 

	        return cart.orElseGet(() -> {
	            Cart newCart = new Cart();
	            newCart.setUserId(userId);
	            return cartRepository.save(newCart);
	        });
	}

	@Override
	public Cart addItemToCart(Long userId, Long productId,Long productVariantId,Long productSizeId, Double price) {
		Cart cart=getOrCreateCart(userId);
		List<CartItem> cartItems=cart.getCartItems();
		if(cartItems.size()>0) {
			for(CartItem ci:cartItems) {
				if(ci.getProductId()==productId) {
					return cart;
				}
			}
		}
		
		CartItem cartItem = new CartItem();
	        cartItem.setCart(cart);
	        cartItem.setProductId(productId);
	        cartItem.setProductVariantId(productVariantId);
	        cartItem.setProductSizeId(productSizeId);
	        cartItem.setQuantity(1);
	        cartItem.setPrice(price);
	        cartItem.setTotalPrice(1*price);

	        cart.setTotalCartPrice(cart.getTotalCartPrice()+cartItem.getTotalPrice());
	        cart.getCartItems().add(cartItem);
	        cart.setUserId(userId);

	        cartItemRepository.save(cartItem);
	        return cartRepository.save(cart);
	}

 // IMPLEMENT CODE LOGIC FOR PROMO AND DISCOUNT AND UPDATE THE CARTITEMS AND PRICE ACCORDINGLY

	@Override
	public Cart removeItemFromCart(String cartId, String itemId) {
		Optional<Cart> optCart =cartRepository.findByCartId(cartId);
		Cart cart=null;
		if(optCart.isPresent()) {
			cart=optCart.get();
			 CartItem cartItem = cartItemRepository.findById(itemId)
		                .orElseThrow(() -> new RuntimeException("Cart item not found"));
			    double latestCartTotalPrice=cart.getTotalCartPrice()-cartItem.getTotalPrice();
		        cart.setTotalCartPrice(latestCartTotalPrice);
		        cart.getCartItems().remove(cartItem);
		        cartItemRepository.delete(cartItem);
		        return cartRepository.save(cart);
		}
		return null;
	}

	@Override
	public Cart getCartById(String cartId) {
		Optional<Cart> optCart =cartRepository.findByCartId(cartId);
		if(optCart.isPresent()) {
			return optCart.get();
		}
		return null;
	}

	@Override
	public Cart incrementItemFromCart(String cartId, String itemId) {
		
		Optional<Cart> optCart =cartRepository.findByCartId(cartId);
		Cart cart=null;
		if(optCart.isPresent()) {
			cart=optCart.get();
		CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        int newQuantity=cartItem.getQuantity()+1;
        cartItem.setQuantity(newQuantity);
        cartItem.setTotalPrice(newQuantity * cartItem.getPrice());
            double latestCartTotalPrice=cart.getTotalCartPrice()+  cartItem.getPrice();
        cart.setTotalCartPrice(latestCartTotalPrice);
        cartItemRepository.save(cartItem);
        return cartRepository.save(cart);
		}
		return null;
	}

	@Override
	public Cart decrementItemFromCart(String cartId, String itemId) {
		Optional<Cart> optCart =cartRepository.findByCartId(cartId);
		Cart cart=null;
		if(optCart.isPresent()) {
			cart=optCart.get();
		CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        int newQuantity=cartItem.getQuantity()-1;
        if(newQuantity==0) {
        	return removeItemFromCart(cartId, itemId);
        }
        cartItem.setQuantity(newQuantity);
        cartItem.setTotalPrice(newQuantity * cartItem.getPrice());
        double latestCartTotalPrice=cart.getTotalCartPrice() - ( cartItem.getPrice());
        cart.setTotalCartPrice(latestCartTotalPrice);
        cartItemRepository.save(cartItem);
        return cartRepository.save(cart);
		}
		return null;
	}

	@Override
	public Cart updateCartItems(String cartId, String cartItemId, String field, Long toBeUpdated,Double price) {
		Optional<Cart> optCart =cartRepository.findByCartId(cartId);
		Cart cart=null;
		if(optCart.isPresent()) {
			cart=optCart.get();
		CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
      Double latestCartPrice=0.0;
		if(field.equalsIgnoreCase("VARIANT")) {
			cartItem.setProductVariantId(toBeUpdated);
			latestCartPrice=cartItem.getPrice();
			cartItem.setPrice(price);
			cartItem.setQuantity(1);
			cartItem.setTotalPrice(price);
			cart.setTotalCartPrice(cart.getTotalCartPrice()-latestCartPrice+price);
		}else if(field.equalsIgnoreCase("SIZE")) {
			cartItem.setProductSizeId(toBeUpdated);
			latestCartPrice=cartItem.getPrice();
			cartItem.setPrice(price);
			cartItem.setQuantity(1);
			cartItem.setTotalPrice(price);
			cart.setTotalCartPrice(cart.getTotalCartPrice()-latestCartPrice+price);
		}
		
        cartItemRepository.save(cartItem);
        return cartRepository.save(cart);
		}
		return null;
	}

	@Override
	public Cart removeAllItemFromCart(String cartId) {
		Optional<Cart> optCart =cartRepository.findByCartId(cartId);
		Cart cart=null;
		if(optCart.isPresent()) {
			cart=optCart.get();
			List<CartItem> cartItem=cart.getCartItems();
			for(CartItem ci :cartItem) {
				removeItemFromCart(cartId, ci.getCartItemId());
			}
			cart.setTotalCartPrice(0);
			
			return cartRepository.save(cart);
		}
		return null;
	}

	@Override
	public List<Cart> fetchCartsByUpdationDate(LocalDate date) {
		return cartRepository.findAll().stream().filter(cart->cart.getUpdatedAt().toLocalDate().equals(date)).collect(Collectors.toList());
	
	}

	

}
