package com.hb.service;

import java.util.List;

import com.hb.exceptions.AdminException;
import com.hb.models.Admin;
import com.hb.models.Orders;


public interface AdminService {

	 public Admin createAdmin(Admin Admin)throws AdminException;
		
	 public  Admin updateAdmin(Admin Admin)throws AdminException;
	 
	 public String deleteAdmin(Integer adminId) throws AdminException;
	 
	 public List<Orders> getAllOrderLastMonth() ;
	
	 

	
}
