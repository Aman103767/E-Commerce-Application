package com.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.models.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer>{
	
	public Customer findByMobileNumber(String mobileNumber);

}
