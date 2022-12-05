package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Address;
import com.masai.model.Customer;
import com.masai.model.CustomerDTO;
import com.masai.repository.CustomerDao;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao cdao;

	@Override
	public Customer createCustomer(CustomerDTO customer) {
		// TODO Auto-generated method stub
		Customer cust = new Customer();
		cust.setUsername(customer.getName());
		cust.setEmail(customer.getEmail());
		cust.setMobileNumber(customer.getMobileNumber());
		cust.setPassword(customer.getPassword());
		cdao.save(cust);
		return cust;
		
		
	}

}
