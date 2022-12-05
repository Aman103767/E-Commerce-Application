package com.masai.service;

import java.util.List;

import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.model.Product;
import com.masai.model.ProductDtoSec;

public interface CartService {

	public String addProductToCart(Integer customerId , Integer quantity,Integer productId,String key) throws CustomerException,CartException;
	
	public List<ProductDtoSec> getAllProduct(Integer cartId,String key,Integer CustomerId) throws CustomerException,CartException;
	
}
