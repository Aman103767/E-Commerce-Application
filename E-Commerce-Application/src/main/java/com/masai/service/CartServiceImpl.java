package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.model.Cart;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Product;
import com.masai.repository.CartDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.ProductDao;
import com.masai.repository.SessionDao;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private ProductDao pdao;
   @Autowired
	private CartDao cdao;
   @Autowired
   private CustomerDao custdao;
   @Autowired
   private SessionDao sessionDao;
	
	@Override
	public String addProductToCart(Integer customerId,Integer productId ,String key) throws  CustomerException, CartException {
		// TODO Auto-generated method stub
		 CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
		  if(loggedInUser == null) {
	        	throw new CustomerException("Please provide a valid key to get all products");
	        }
	        
	        if(customerId == loggedInUser.getUserId()) {
	        	
//	          Optional<Product> pro = pdao.findById(productId);
//	          if(pro.isPresent() == false) {
	   
    Optional<Customer> c = custdao.findById(customerId);
	Optional<Product> p = pdao.findById(productId);
	if(p.isPresent()&& c.isPresent()) {
		Customer customer = c.get();
		Product product = p.get();
		Cart c1 = customer.getCart();
		if(c1 != null) {
		//Cart cart = new Cart();
//		if(cart.getCartId() != null) {
//			cart.setCartId(cart.getCartId());
//		}
		List<Product> list = c1.getCartproducts();
		for(int i=0;i<list.size();i++) {
			if(productId == list.get(i).getProductId()) {
			     return "Product is already added to the cart";
			}
		}
		c1.getCartproducts().add(product);
		}
		else {
		//	return c1.toString()
			c1 = new Cart();
			c1.getCartproducts().add(product);
		    c1.setCustomer(customer);
		    customer.setCart(c1);
		    
		    
		}

		cdao.save(c1);
		return "Product added to the cart";
	}
		
	      throw new CartException("Customerid or productid not found");
//	          }else {
//	        	  throw new CartException("product already added to the cart");
//	          }
	        }
	        else {
	    		throw new CustomerException("wrong Details please login first!");
	    	}
	}

	@Override
	public List<Product> getAllProduct(Integer cartId,String key,Integer CustomerId ) throws CustomerException, CartException {
		
		  
        CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
        
        if(loggedInUser == null) {
        	throw new CustomerException("Please provide a valid key to get all products");
        }
        
        if(CustomerId == loggedInUser.getUserId()) {
       
		// TODO Auto-generated method stub
	Optional<Cart> c = 	cdao.findById(cartId);
		if(c.isPresent()) {
			Cart cart =c.get();
			return cart.getCartproducts();
		}
		throw new CartException("Cartid not found with "+cartId);
	}
	else {
		throw new CustomerException("wrong Details please login first!");
	}
	}
	

}
