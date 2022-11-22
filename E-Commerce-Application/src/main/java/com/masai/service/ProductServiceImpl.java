package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Repository.ProductDao;
import com.masai.model.Category;
import com.masai.model.Product;
import com.masai.model.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao pdao;

	@Override
	public Product createProduct(ProductDTO product) {
		// TODO Auto-generated method stub
		Product p = new Product();
		p.setProductName(product.getProductName());
		p.setQunatity(product.getQunatity());
		p.setSpecification(product.getSpecification());
		p.setDimension(product.getDimension());
		p.setManufacturer(product.getManufacturer());
		p.setPrice(product.getPrice());
		
		Category c = new Category();
		c.setCategoryName(product.getCategoryName());
		p.setCategory(c);
		c.getProducts().add(p);
		
		pdao.save(p);
		
		return p;
		
	}



}
