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
import com.masai.model.Address;
import com.masai.model.AddressDto;
import com.masai.model.Cart;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.Product;
import com.masai.model.ProductDtoSec;
import com.masai.repository.CartDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.OrderDao;
import com.masai.repository.ProductDao;
import com.masai.repository.SessionDao;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private CartDao cdao;
	
	@Autowired
	private ProductDao pdao;
	@Autowired
	private CustomerDao custdao;
	
	@Autowired
    private OrderDao odao;
	
	@Autowired
	private SessionDao sessionDao;

	@SuppressWarnings("unused")
	@Override
	public List<ProductDtoSec> OrderProducts(Integer cartId,String key,Integer customerId,AddressDto address) throws OrderException, CustomerException {
		// TODO Auto-generated method stub
		
		   
        CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
        
        if(loggedInUser == null) {
        	throw new CustomerException("Please provide a valid key to create a Product");
        }
        
        if(customerId == loggedInUser.getUserId()) {
       
	
		Optional<Customer> OptionalCust = custdao.findById(customerId);
		
		if(OptionalCust.isPresent()) {
			Cart cart = OptionalCust.get().getCart();
			if(cart.getCartproducts().size()==0) {
				throw new OrderException("Please add the product in the cart first!");
			}
			if(cart == null) {
				throw new OrderException("Please add the product in the cart first!");
			}
		Orders order = new Orders();
		Customer cust = cart.getCustomer();
		
	     Address add = new Address();
	     add.setBuildingNo(address.getBuildingNo());
	     add.setCity(address.getCity());
	     add.setCountry(address.getCountry());
	     add.setPincode(address.getPincode());
	     add.setState(address.getState());
	     cust.setAddress(add);
	     custdao.save(cust);
	     
		order.setAddress(cust.getAddress());
		order.setOrderDate(LocalDate.now());
		 order.setCustomer(cust);
		List<ProductDtoSec> products= new ArrayList<>();
		for(ProductDtoSec p : cart.getCartproducts()) {
			products.add(p);
			
		}
		order.setProducts(products);
		
		odao.save(order);
		for(ProductDtoSec p : cart.getCartproducts()) {
			Product product = pdao.findById(p.getProductId()).get();
			product.setQunatity(product.getQunatity()-p.getQuantity());
			pdao.save(product);
		}
		cart.getCartproducts().clear();
		cdao.save(cart);
		
		return order.getProducts();
		}
		throw new OrderException("cart not found with id "+ cartId);
		
	}
        else {
        	throw new CustomerException("Wrong details please login first");
        	
	}
	}
	

}
