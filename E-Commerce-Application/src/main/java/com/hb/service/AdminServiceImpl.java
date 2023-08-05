package com.hb.service;

import java.time.LocalDate; 
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.hb.exceptions.AdminException;
import com.hb.models.Admin;

import com.hb.models.Orders;
import com.hb.repository.AdminDao;


@Service 
public class AdminServiceImpl implements AdminService {
	

	@Autowired
	private AdminDao aDao;
	
	
	
	
	public Admin createAdmin(Admin admin)throws AdminException {
		
		
		Admin existingAdmin= aDao.findByMobileNumber(admin.getMobileNumber());
		
	
		
		
		if(existingAdmin != null ) 
			throw new AdminException("Admin Already Registered with Mobile number");
			
		
		
		
			return aDao.save(admin);
			
			
		}

	@Override
	public Admin updateAdmin(Admin Admin) throws AdminException{
	
			return aDao.save(Admin);
		
		
		
	
	}

	@Override
	public String deleteAdmin(Integer adminId) throws AdminException {
		Admin adminDetails = aDao.findByAdminId(adminId);
		
		if(adminDetails != null) {
			
			aDao.delete(adminDetails);
			return "Admin Deleted Successfull with Id : "+adminId ;
			
		}else {
			throw new AdminException("Admin not found with adminId :"+ adminId);
		}
	}

	@Override
	public List<Orders> getAllOrderLastMonth() {
		// TODO Auto-generated method stub

		List<Orders> ordersLastMonth = aDao.findAllOrderInlastMonth(LocalDate.now());

		return ordersLastMonth;
	}
	


	
	
	

}
