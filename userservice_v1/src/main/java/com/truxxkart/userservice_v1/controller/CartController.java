package com.truxxkart.userservice_v1.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.truxxkart.userservice_v1.entity.Cart;
import com.truxxkart.userservice_v1.entity.CartItemDto;
import com.truxxkart.userservice_v1.serviceImpl.CartServiceImpl;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @GetMapping
    public Cart getCart(@RequestParam(required = false) Long userId) {
        return cartService.getOrCreateCart(userId);
    }
    
    @GetMapping("/date")
    public List<Cart> fetchCartsByUpdationDate(@RequestParam(required = false) LocalDate date) {
        return cartService.fetchCartsByUpdationDate(date);
    }

    @PostMapping("/{userId}/item")
    public Cart addItem(@PathVariable Long userId, @RequestBody CartItemDto cartItemDto) {
//        Cart cart = cartService.getCartById(cartId);
        return cartService.addItemToCart(userId, cartItemDto.getProductId(),cartItemDto.getProductVariantId(),cartItemDto.getProductSizeId() , cartItemDto.getPrice());
    }

    @PutMapping("/update/{cartId}/item/{itemId}")
    public Cart updateCartItems(@PathVariable String cartId, @PathVariable String itemId ,
    		@RequestParam String field,@RequestParam Long toBeUpdated,@RequestParam Double price
    		) {
//        Cart cart = cartService.getCartById(cartId);
        return cartService.updateCartItems(cartId, itemId, field, toBeUpdated, price);
    }
    
    @PutMapping("/increment/{cartId}/item/{itemId}")
    public Cart incrementItemQuantity(@PathVariable String cartId, @PathVariable String itemId) {
//        Cart cart = cartService.getCartById(cartId);
        return cartService.incrementItemFromCart(cartId, itemId);
    }
    @PutMapping("/decrement/{cartId}/item/{itemId}")
    public Cart decrementItemQuantity(@PathVariable String cartId, @PathVariable String itemId) {
//        Cart cart = cartService.getCartById(cartId);
        return cartService.decrementItemFromCart(cartId, itemId);
    }

    @DeleteMapping("/{cartId}/item/{itemId}")
    public Cart removeItem(@PathVariable String cartId, @PathVariable String itemId) {
//        Cart cart = cartService.getCartById(cartId);
        return cartService.removeItemFromCart(cartId, itemId);
    }
    @DeleteMapping("/remove-all-items/{cartId}")
    public Cart removeAllItemFromCart(@PathVariable String cartId) {
//        Cart cart = cartService.getCartById(cartId);
        return cartService.removeAllItemFromCart(cartId);
    }
}
