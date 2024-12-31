package com.truxxkart.userservice_v1.entity;


import lombok.Data;

@Data
public class CartItemDto {

    private Long productId; // Unique identifier for the product
    private Long productVariantId;
    private Long productSizeId;
    private int quantity;     // Quantity of the product
    private Double price;     // Price per unit of the product
    private double discount;
    private double totalPrice;

}