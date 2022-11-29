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
		Address add = new Address();
		add.setBuildingNo(customer.getBuildingNo());
		add.setCity(customer.getCity());
		add.setPincode(customer.getPincode());
		add.setState(customer.getPincode());
		add.setCountry(customer.getCountry());
		cust.setAddress(add);
		cdao.save(cust);
		return cust;
		
		
	}

}
