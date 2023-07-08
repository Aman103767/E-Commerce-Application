package com.hb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.exceptions.CustomerException;
import com.hb.exceptions.OrderException;
import com.hb.models.AddressDto;
import com.hb.models.Orders;
import com.hb.service.OrderService;

@RestController
@RequestMapping(value = "/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@PostMapping("/orderProduct/{customerId}")
	public ResponseEntity<Orders> Order(@PathVariable Integer customerId,@RequestBody AddressDto addDto) throws OrderException, CustomerException{
		
		Orders order = orderService.OrderProducts(customerId,addDto);
		
		return new  ResponseEntity<Orders>(order,HttpStatus.OK);
	}
	@GetMapping("/getOrderById/{orderId}")
	public ResponseEntity<Orders> getOrderById(@PathVariable Integer orderId) throws OrderException, CustomerException {
		Orders order = orderService.getOrderById(orderId);
		return new ResponseEntity<Orders>(order,HttpStatus.OK);
	}
}