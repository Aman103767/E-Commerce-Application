package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.OrderException;
import com.masai.model.Customer;
import com.masai.model.CustomerDTO;
import com.masai.model.Product;
import com.masai.service.CartService;
import com.masai.service.CustomerService;
import com.masai.service.OrderService;

@RestController
@RequestMapping("/Customer")
public class CustomerController {
	
    @Autowired
	CustomerService cService;
    
    @Autowired
	CartService cservice;
    
	@Autowired
	OrderService orderService;
   
    
	
	@PostMapping("/create")
	public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO cDTO){
		
		Customer cust = cService.createCustomer(cDTO);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
		
		
	}
	 
    @GetMapping("/cart")
	public ResponseEntity<String> addToCart(@RequestParam Integer customerId,@RequestParam Integer productId ,@RequestParam String key) throws CartException, CustomerException{
	    	String mess = cservice.addProductToCart(customerId, productId,key);
	    	return new ResponseEntity<String>(mess, HttpStatus.OK);
	    }
	@GetMapping("/getAllProductAddedInCart")
	public ResponseEntity<List<Product>> getAllProductAddedToCart(@RequestParam Integer cartId,@RequestParam String key,@RequestParam Integer CustomerId) throws CartException, CustomerException{
	   List<Product> products = cservice.getAllProduct(cartId,key,CustomerId);
	   return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	    	
	    }
	

	@GetMapping("/{cartId}")
	public ResponseEntity<List<Product>> Order(@PathVariable("cartId") Integer cartId,@RequestParam String key,@RequestParam Integer CustomerId) throws OrderException, CustomerException{
		
		List<Product> list = orderService.OrderProducts(cartId,key,CustomerId);
		
		return new  ResponseEntity<List<Product>>(list,HttpStatus.OK);
		
		
	}
	
	
}
