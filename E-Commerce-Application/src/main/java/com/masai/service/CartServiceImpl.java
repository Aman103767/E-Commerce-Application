package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Repository.CartDao;
import com.masai.Repository.CustomerDao;
import com.masai.Repository.ProductDao;
import com.masai.model.Cart;
import com.masai.model.Customer;
import com.masai.model.Product;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private ProductDao pdao;
   @Autowired
	private CartDao cdao;
   @Autowired
   private CustomerDao custdao;
	
	@Override
	public String addProductToCart(Integer customerId,Integer productId) {
		// TODO Auto-generated method stub
    Optional<Customer> c = custdao.findById(customerId);
	Optional<Product> p = pdao.findById(productId);
	if(p.isPresent()&& c.isPresent()) {
		Customer customer = c.get();
		Product product = p.get();
		Cart cart = new Cart();
		cart.setCustomer(customer);
		cart.getProducts().add(product);
		
		cdao.save(cart);
		return "Product added to the cart";
	}
		
		return "Product not added to the cart" ;
	}

	@Override
	public List<Product> getAllProduct(Integer cartId) {
		// TODO Auto-generated method stub
	Optional<Cart> c = 	cdao.findById(cartId);
		if(c.isPresent()) {
			Cart cart =c.get();
			return cart.getProducts();
		}
		return null;
	}
	

}
