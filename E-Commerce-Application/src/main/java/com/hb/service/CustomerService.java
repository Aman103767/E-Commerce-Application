package com.hb.service;

import java.util.List;

import com.hb.exceptions.CustomerException;
import com.hb.exceptions.ProductException;
import com.hb.models.Address;
import com.hb.models.AddressDto;
import com.hb.models.Customer;
import com.hb.models.CustomerDTO;
import com.hb.models.Reviews;
import com.hb.security.JwtAuthResponse;

public interface CustomerService {
	
 public Customer createCustomer(CustomerDTO customer) throws CustomerException;
 
 public String addAddress(AddressDto address, String token) throws CustomerException;
 
 public List<Address>  getAllAddress(Integer customerId) throws CustomerException;  
 
 public Address getAddressById(Integer addressId) throws CustomerException;
 
 public String deleteAddressById(Integer addressId,Integer CustomerId) throws CustomerException;
 
 public String setDefaultAddress(Integer addressId, Integer customerId) throws CustomerException;

 public Customer updateCustomer(CustomerDTO customer,Integer customerId) throws CustomerException;

 public Customer viewCustomer(Integer customerId) throws CustomerException;

 public List<Customer> viewCustomerAll() throws CustomerException;
 
 public String deleteCustomer(Integer customerId) throws CustomerException;
 
 public Reviews addReviewToProductAdmin(Integer customerId,Integer productId, Integer orderId, Reviews review) throws CustomerException, ProductException;
 
 public Reviews getReview(Integer productId, Integer customerId, Integer orderId) throws CustomerException, ProductException;
}
