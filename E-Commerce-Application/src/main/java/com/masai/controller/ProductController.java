package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Product;
import com.masai.model.ProductDTO;
import com.masai.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService pService;
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody ProductDTO product ) {
		Product p = pService.createProduct(product);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}

}
