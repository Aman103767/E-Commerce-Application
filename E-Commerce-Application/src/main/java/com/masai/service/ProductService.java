package com.masai.service;

import java.util.List;

import com.masai.exceptions.AdminException;
import com.masai.model.Product;
import com.masai.model.ProductDTO;

public interface ProductService {
public Product createProduct(ProductDTO product,String key,Integer adminId) throws AdminException;


}
