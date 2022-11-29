package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.OrderException;
import com.masai.model.Cart;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.Product;
import com.masai.repository.CartDao;
import com.masai.repository.OrderDao;
import com.masai.repository.ProductDao;
import com.masai.repository.SessionDao;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	CartDao cdao;
	
	@Autowired
	ProductDao pdao;
	
	
	@Autowired
    OrderDao odao;
	
	@Autowired
	SessionDao sessionDao;

	@Override
	public List<Product> OrderProducts(Integer cartId,String key,Integer customerId) throws OrderException, CustomerException {
		// TODO Auto-generated method stub
		
		   
        CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
        
        if(loggedInUser == null) {
        	throw new CustomerException("Please provide a valid key to create a Product");
        }
        
        if(customerId == loggedInUser.getUserId()) {
       
	
		Optional<Cart> OptionalCart = cdao.findById(cartId);
		
		if(OptionalCart.isPresent()) {
			Cart cart = OptionalCart.get();
		Orders order = new Orders();
		Customer cust = cart.getCustomer();
		order.setAddress(cust.getAddress());
		order.setOrderDate(LocalDate.now());
		 order.setCustomer(cust);
		List<Product> products= new ArrayList<>();
		for(Product p : cart.getCartproducts()) {
			products.add(p);
			
		}
		order.setProducts(products);
		odao.save(order);
		return cart.getCartproducts();
		}
		throw new OrderException("cart not found with id "+ cartId);
		
	}
        else {
        	throw new CustomerException("Wrong details please login first");
        	
	}
	}
	

}
