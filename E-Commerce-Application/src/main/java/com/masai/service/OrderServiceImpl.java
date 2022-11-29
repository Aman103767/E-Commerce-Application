package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Repository.CartDao;
import com.masai.Repository.OrderDao;
import com.masai.Repository.ProductDao;
import com.masai.exceptions.OrderException;
import com.masai.model.Cart;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.Product;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	CartDao cdao;
	
	@Autowired
	ProductDao pdao;
	
	
	@Autowired
    OrderDao odao;

	@Override
	public List<Product> OrderProducts(Integer cartId) throws OrderException {
		// TODO Auto-generated method stub
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

}
