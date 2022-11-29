package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Product;
import com.masai.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
   
	@GetMapping("/{cartId}")
	public ResponseEntity<List<Product>> Order(@PathVariable("cartId") Integer cartId){
		
		List<Product> list = orderService.OrderProducts(cartId);
		
		return new  ResponseEntity<List<Product>>(list,HttpStatus.OK);
		
		
	}
}
