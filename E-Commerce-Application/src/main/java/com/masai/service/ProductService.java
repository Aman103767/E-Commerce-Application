package com.masai.service;

import java.util.List;

import com.masai.exceptions.AdminException;
import com.masai.model.Product;
import com.masai.model.ProductDTO;

public interface ProductService {

	public Product createProduct(ProductDTO product,String key,Integer adminId) throws AdminException;
    
	public String removeProduct(Integer productId,String key,Integer adminId) throws AdminException;
	
	public Product updateProduct(ProductDTO product,String key,Integer adminId) throws AdminException;
	
	public Product productById(Integer productId,String key,Integer adminId) throws AdminException;

	public List<Product> getAllProduct(String key,Integer adminId) throws AdminException;

}
