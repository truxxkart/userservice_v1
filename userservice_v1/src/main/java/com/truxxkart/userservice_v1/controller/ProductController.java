package com.truxxkart.userservice_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truxxkart.userservice_v1.entity.Product;
import com.truxxkart.userservice_v1.feignclient.ProductFeignInterface;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductFeignInterface pfi;
	
	@GetMapping()
	public ResponseEntity<List<Product>> getAllProducts(){
		  ResponseEntity<List<Product>> proList =pfi.getAllProducts();
		return ResponseEntity.status(HttpStatus.OK).body(proList.getBody());
	}

}
