package com.hb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hb.models.Customer;
import com.hb.models.CustomerSecurity;
import com.hb.repository.CustomerDao;
@Service
public class CustomerDetailsService implements UserDetailsService {
    
	@Autowired
	private CustomerDao customerDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerDao.findByMobileNumber(username);
		System.out.println(username);
		if(customer!= null) {
			return new CustomerSecurity(customer);
		}
		throw new UsernameNotFoundException("Customer does not exist..!");
		
	}

}
