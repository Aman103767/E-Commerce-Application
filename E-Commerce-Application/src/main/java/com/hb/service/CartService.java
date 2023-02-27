package com.hb.service;

import java.util.List;

import com.hb.exceptions.CartException;
import com.hb.exceptions.CustomerException;
import com.hb.models.Cart;
import com.hb.models.Product;
import com.hb.models.ProductDtoSec;

public interface CartService {

	public String addProductToCart(Integer customerId , Integer quantity,Integer productId) throws CustomerException,CartException;
	
	public List<ProductDtoSec> getAllProduct(Integer CustomerId) throws CustomerException,CartException;
	
    public String removeProductfromCart(Integer productId,Integer customerId) throws CustomerException,CartException;
	
    public ProductDtoSec updateQuantity(Integer productId,Integer quantity,Integer customerid) throws CustomerException,CartException;

    public Cart removeAllProduct(Integer customerid) throws CustomerException,CartException;

}
