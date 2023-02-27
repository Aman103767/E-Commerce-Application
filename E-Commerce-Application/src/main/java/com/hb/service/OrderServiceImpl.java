package com.hb.service;

import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hb.exceptions.AdminException;
import com.hb.exceptions.CustomerException;
import com.hb.exceptions.OrderException;
import com.hb.models.Address;
import com.hb.models.AddressDto;
import com.hb.models.Cart;
import com.hb.models.CurrentUserSession;
import com.hb.models.Customer;
import com.hb.models.Orders;
import com.hb.models.Product;
import com.hb.models.ProductDtoSec;
import com.hb.repository.CartDao;
import com.hb.repository.CustomerDao;
import com.hb.repository.OrderDao;
import com.hb.repository.ProductDao;
import com.hb.repository.SessionDao;
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
	public Orders OrderProducts(Integer customerId,AddressDto address) throws OrderException, CustomerException {
		// TODO Auto-generated method stub
		
        
      
	
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

	@Override
	public String cancelOrder(Integer orderId) throws OrderException, CustomerException {
		// TODO Auto-generated method stub
		
	        	Orders order = odao.findById(orderId).get(); 
	        	if(order ==  null) {
	        	   throw new OrderException("order not found with id :"+orderId);
	        	}
	        	if(order.getOrderStatus().equals("Order Comfirmed")) {
	        		
	        	
	        
	        	
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
	       
	        
	
	
	}

	@Override
	public Orders getOrderById(Integer orderId)
			throws OrderException, CustomerException {
		
	        
	   
	        	Optional<Orders> order = odao.findById(orderId); 
	        	if(order.isPresent()) {
	        		Orders o = order.get();
	        		return o;
	        	}else {
	        		throw new OrderException("Order not found with id :"+orderId);
	        	}
	        	
	        	
	        
	
	}

	@Override
	public List<Orders> getAllOrders() throws OrderException, CustomerException {
		// TODO Auto-generated method stub
		
	        	List<Orders> orders = odao.findAll();
	        	if(orders.size()== 0) {
	        		throw new OrderException("No order found");
	        	}
	        	return orders;
	        }
	       
		
	@Override
	public List<Orders> sortByfieldOrders(String field) throws OrderException, CustomerException {
		// TODO Auto-generated method stub
		
	        	List<Orders> orders = odao.findAll(Sort.by(Sort.Direction.DESC,field));
	       
	        	if(orders.size()== 0) {
	        		throw new OrderException("No order found");
	        	}
	        	return orders;
	        }


	
	
	
	

}
