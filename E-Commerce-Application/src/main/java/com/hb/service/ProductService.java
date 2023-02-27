package com.hb.service;

import java.util.List;

import com.hb.exceptions.AdminException;
import com.hb.models.Product;
import com.hb.models.ProductDTO;

public interface ProductService {

	public Product createProduct(ProductDTO product) throws AdminException;
    
	public String removeProduct(Integer productId) throws AdminException;
	
	public Product updateProduct(ProductDTO product) throws AdminException;
	
	public Product productById(Integer productId) throws AdminException;

	public List<Product> getAllProduct() throws AdminException;

}
