package com.hb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.exceptions.CustomerException;
import com.hb.models.Address;
import com.hb.models.AuthAddress;
import com.hb.service.CustomerService;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressController {
	
	@Autowired
	private CustomerService custService;
	
	
	@PostMapping("/add")
	public ResponseEntity<String> setAddress(@RequestBody AuthAddress addressAndToken) throws CustomerException{
		System.out.println(addressAndToken);
		String mess = custService.addAddress(addressAndToken.getAddress(), addressAndToken.getToken());
		return new ResponseEntity<String>(mess,HttpStatus.OK);
	}
    @GetMapping("/getAllAddress/{customerId}")
    public ResponseEntity<List<Address>> getAllAddress(@PathVariable Integer customerId) throws CustomerException{
    	List<Address> addresses = custService.getAllAddress(customerId);
    	return new ResponseEntity<List<Address>>(addresses,HttpStatus.OK);    	
    }
    @GetMapping("/getById/{addressId}")
    public ResponseEntity<Address> getByIdAddress(@PathVariable Integer addressId) throws CustomerException{
    	Address address = custService.getAddressById(addressId);
    	return new ResponseEntity<Address>(address,HttpStatus.OK);
    }
    @DeleteMapping("/deleteById/{customerId}/{addressId}")
    public ResponseEntity<String> deleteByIdAddress(@PathVariable Integer addressId,@PathVariable Integer customerId) throws CustomerException{
    	String mess = custService.deleteAddressById(addressId, customerId);
    	return new ResponseEntity<String>(mess,HttpStatus.OK);
    }
    @GetMapping("/setDefault/{customerId}/{addressId}")
    public ResponseEntity<String> setDefaultAddress(@PathVariable Integer addressId,@PathVariable Integer customerId) throws CustomerException{
    	String mess = custService.setDefaultAddress(addressId,customerId);
    	return new ResponseEntity<String>(mess,HttpStatus.OK);
    }
    
    
}
