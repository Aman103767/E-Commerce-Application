package com.masai.service;

import java.util.List;

import com.masai.model.Product;

public interface CartService {

	public String addProductToCart(Integer customerId , Integer productId);
	
	public List<Product> getAllProduct(Integer cartId);
	
}
