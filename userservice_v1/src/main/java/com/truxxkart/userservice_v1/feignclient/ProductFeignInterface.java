package com.truxxkart.userservice_v1.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.truxxkart.userservice_v1.entity.Product;


@FeignClient(name = "sellerservice" , url = "http://localhost:8000")
public interface ProductFeignInterface {
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts();
}
