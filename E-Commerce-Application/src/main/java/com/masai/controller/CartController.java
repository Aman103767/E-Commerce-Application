package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Product;
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
    @GetMapping("/getAllProductAddedInCart")
    public ResponseEntity<List<Product>> getAllProductAddedToCart(@RequestParam Integer cartId){
    	List<Product> products = cservice.getAllProduct(cartId);
    	return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    	
    }
	
}
