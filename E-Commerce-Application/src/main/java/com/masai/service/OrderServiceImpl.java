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
	public Orders OrderProducts(String key,Integer customerId,AddressDto address) throws OrderException, CustomerException {
		// TODO Auto-generated method stub
		
		   
        CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
        
        if(loggedInUser == null) {
        	throw new CustomerException("Please provide a valid key to create a Product");
        }
        
        if(customerId == loggedInUser.getUserId()) {
       
	
		Optional<Customer> OptionalCust = custdao.findById(customerId);
		
		if(OptionalCust.isPresent()) {
			Customer customer = OptionalCust.get();
			Cart cart = customer.getCart();
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
		order.setOrderStatus("Order Comfirmed");
		odao.save(order);
		for(ProductDtoSec p : cart.getCartproducts()) {
			Product product = pdao.findById(p.getProductId()).get();
			product.setQunatity(product.getQunatity()-p.getQuantity());
			pdao.save(product);
		}
		cart.getCartproducts().clear();
		cdao.save(cart);
		
		return order;
		}
		throw new OrderException("Customer not found with id "+OptionalCust.get().getCustomerId());
		
	}
        else {
        	throw new CustomerException("Wrong details please login first");
        	
	}
	}

	@Override
	public String cancelOrder(Integer orderId, String key,Integer customerId) throws OrderException, CustomerException {
		// TODO Auto-generated method stub
		
		 CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
	        
	        if(loggedInUser == null) {
	        	throw new CustomerException("Please provide a valid key to create a Product");
	        }
	        
	        if(customerId == loggedInUser.getUserId()) {
	        	Orders order = odao.findById(orderId).get(); 
	        	if(order ==  null) {
	        	   throw new OrderException("order not found with id :"+orderId);
	        	}
	        	if(order.getOrderStatus().equals("Order Comfirmed")) {
	        		
	        	
	        	Customer cust = custdao.findById(customerId).get();
	        	
	        	order.setOrderDate(LocalDate.now());
	        	order.setOrderStatus("Order Cancelled");
	        	 
	        	List<ProductDtoSec> products = order.getProducts();
	        	
	        	for(ProductDtoSec p : products) {
	        		Product pro = pdao.findById(p.getProductId()).get();
	        		pro.setQunatity(pro.getQunatity()+p.getQuantity());
	        		pdao.save(pro);
	        	}
	        	odao.save(order);
	        
	        	return "Order Cancelled Successfully";	
	        	}else {
	        		if(order.getOrderStatus().equals("Order Cancelled")) {
	        		  return "Order Already Cancelled";
	        		}else {
	        			return "Something Went wrong Please Try Again";
	        		}
	        		
	        	}
	       
	        }else {
	        	throw new CustomerException("Wrong details please login first");
	        }
		
	
	}

	@Override
	public Orders getOrderById(Integer orderId, String key, Integer customerId)
			throws OrderException, CustomerException {
		// TODO Auto-generated method stub
		 CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
	        
	        if(loggedInUser == null) {
	        	throw new CustomerException("Please provide a valid key to create a Product");
	        }
	        
	        if(customerId == loggedInUser.getUserId()) {
	        	Optional<Orders> order = odao.findById(orderId); 
	        	if(order.isPresent()) {
	        		Orders o = order.get();
	        		return o;
	        	}else {
	        		throw new OrderException("Order not found with id :"+orderId);
	        	}
	        	
	        	
	        }else {
	        	throw new CustomerException("Wrong details please login first");
	        }
	        	
	
	}

	@Override
	public List<Orders> getAllOrders(String key, Integer adminId) throws OrderException, CustomerException {
		// TODO Auto-generated method stub
		 CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
	        
	        if(loggedInUser == null) {
	        	throw new CustomerException("Please provide a valid key to create a Product");
	        }
	        if(adminId == loggedInUser.getUserId()) {
	        	List<Orders> orders = odao.findAll();
	        	if(orders.size()== 0) {
	        		throw new OrderException("No order found");
	        	}
	        	return orders;
	        }
	        else {
	        	throw new CustomerException("Wrong details please login first");
	        }
		
	}


	
	
	
	

}
