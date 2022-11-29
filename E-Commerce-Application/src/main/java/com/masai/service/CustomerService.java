package com.masai.service;

import com.masai.exceptions.CustomerException;
import com.masai.model.Customer;
import com.masai.model.CustomerDTO;

public interface CustomerService {
 public Customer createCustomer(CustomerDTO customer) ;
}
