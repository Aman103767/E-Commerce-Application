package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Customer;
import com.masai.model.CustomerDTO;
import com.masai.service.CustomerService;

@RestController
public class CustomerController {
	
    @Autowired
	CustomerService cService;
	
	@PostMapping("/create")
	public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO cDTO){
		
		Customer cust = cService.createCustomer(cDTO);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
		
		
	}
	
}
