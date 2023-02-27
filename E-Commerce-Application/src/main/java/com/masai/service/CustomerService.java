package com.masai.service;

import java.util.List;

import com.masai.exceptions.CustomerException;
import com.masai.model.Customer;
import com.masai.model.CustomerDTO;

public interface CustomerService {
	
 public Customer createCustomer(CustomerDTO customer) throws CustomerException;

 public Customer updateCustomer(CustomerDTO customer, String key) throws CustomerException;

 public Customer viewCustomer(Integer customerId, String key, Integer AdminId) throws CustomerException;

 public List<Customer> viewCustomerAll(Integer AdminId, String key) throws CustomerException;
}
