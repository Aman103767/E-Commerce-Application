package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.service.CartService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class CartController {
    @Autowired
	CartService cservice;
    
    @GetMapping("/cart")
    public ResponseEntity<String> addToCart(@RequestParam Integer customerId,@RequestParam Integer productId ){
    	String mess = cservice.addProductToCart(customerId, productId);
    	return new ResponseEntity<String>(mess, HttpStatus.OK);
    }
	
}
