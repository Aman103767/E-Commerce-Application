package com.hb.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hb.models.Admin;
import com.hb.models.Customer;
import com.hb.models.Orders;


@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>   {

	public Admin findByMobileNumber(String mobileNo);
	
	public Admin findByAdminId(Integer AdminId);
	
	@Query("Select o from Orders o where Month(o.orderDate) = Month(?1)")
	public List<Orders> findAllOrderInlastMonth(LocalDate date);
	
}
