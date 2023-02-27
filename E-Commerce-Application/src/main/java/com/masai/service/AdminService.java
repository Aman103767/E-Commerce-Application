package com.masai.service;

import java.util.List; 

import com.masai.exceptions.AdminException;

import com.masai.model.Admin;


public interface AdminService {

	 public Admin createAdmin(Admin Admin)throws AdminException;
		
	 public  Admin updateAdmin(Admin Admin,String key)throws AdminException;
	 
	 public String deleteAdmin(Integer adminId,String key) throws AdminException;
	 
	

	
}
