package com.hb.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hb.exceptions.AdminException;
import com.hb.exceptions.CustomerException;
import com.hb.exceptions.ProductException;
import com.hb.models.Product;
import com.hb.models.ProductDTO;

public interface ProductService {

	public Product createProduct(ProductDTO product,Integer adminId) throws ProductException, CustomerException;
    
	public String removeProduct(Integer productId) throws ProductException;
	
	public Product updateProduct(ProductDTO product,Integer productId) throws ProductException;
	
	public Product productById(Integer productId) throws ProductException;

	public List<Product> getAllProduct() throws ProductException;
	
	public List<Product> sortProductAsc(String field) throws ProductException;
	
	public List<Product> sortProductDsc(String field) throws ProductException;
	
	public Page<Product> findProductWithPagination(int offset, int pageSize) ;
	
	public List<Product> searchProductByName(String name) throws ProductException;
	


}
