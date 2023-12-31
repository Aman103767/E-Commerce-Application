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
		Customer customer;

		// Check if the username is a valid email address
		if (username.contains("@")) {
			customer = customerDao.findByEmail(username);
		} else {
			// Assuming it's a mobile number if not an email
			customer = customerDao.findByMobileNumber(username);
		}

		if (customer != null) {
			return new CustomerSecurity(customer);
		}

		throw new UsernameNotFoundException("Customer does not exist..!");
		
	}

}
