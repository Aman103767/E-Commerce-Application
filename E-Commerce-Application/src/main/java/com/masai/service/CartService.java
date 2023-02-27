package com.masai.service;

import java.util.List;

import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.model.Cart;
import com.masai.model.Product;
import com.masai.model.ProductDtoSec;

public interface CartService {

	public String addProductToCart(Integer customerId , Integer quantity,Integer productId,String key) throws CustomerException,CartException;
	
	public List<ProductDtoSec> getAllProduct(String key,Integer CustomerId) throws CustomerException,CartException;
	
    public String removeProductfromCart(Integer productId,String key,Integer customerId) throws CustomerException,CartException;
	
    public ProductDtoSec updateQuantity(Integer productId,Integer quantity,String key,Integer customerid) throws CustomerException,CartException;

    public Cart removeAllProduct(String key,Integer customerid) throws CustomerException,CartException;

}
