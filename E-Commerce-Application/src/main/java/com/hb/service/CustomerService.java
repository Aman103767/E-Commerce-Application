package com.hb.service;

import java.util.List;

import com.hb.exceptions.CustomerException;
import com.hb.models.Customer;
import com.hb.models.CustomerDTO;

public interface CustomerService {
	
 public Customer createCustomer(CustomerDTO customer) throws CustomerException;

 public Customer updateCustomer(CustomerDTO customer) throws CustomerException;

 public Customer viewCustomer(Integer customerId) throws CustomerException;

 public List<Customer> viewCustomerAll() throws CustomerException;
}
