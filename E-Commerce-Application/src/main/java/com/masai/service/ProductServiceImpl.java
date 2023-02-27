package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.model.Admin;
import com.masai.model.Category;
import com.masai.model.Product;
import com.masai.model.ProductDTO;
import com.masai.repository.AdminDao;
import com.masai.repository.ProductDao;
import com.masai.repository.SessionDao;
import com.masai.model.CurrentUserSession;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao pdao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private SessionDao sessionDao;

	@Override
	public Product createProduct(ProductDTO product,String key,Integer adminId) throws AdminException {
		// TODO Auto-generated method stub
         
          
        CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
        
        if(loggedInUser == null) {
        	throw new AdminException("Please provide a valid key to create a Product");
        }
        
        if(adminId == loggedInUser.getUserId()) {
       
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
        else {
        	throw new AdminException("Wrong details please login first");
        	
	}

	}

	@Override
	public String removeProduct(Integer productId, String key, Integer adminId) throws AdminException {
		// TODO Auto-generated method stub
		 CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
	        
	        if(loggedInUser == null) {
	        	throw new AdminException("Please provide a valid key to create a Product");
	        }
	        
	        if(adminId == loggedInUser.getUserId()) {
	         Product product =  pdao.findById(productId).orElseThrow(()->new AdminException("product odes not exist with id: "+ productId));
	          	
	        pdao.delete(product);
	        return "Product is remove successfully";
	           
	    }else {
		   throw new AdminException("Wrong details please login first");
	}
	}

	@Override
	public Product updateProduct(ProductDTO product, String key, Integer adminId) throws AdminException {
		// TODO Auto-generated method stub
		  CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
	        
	        if(loggedInUser == null) {
	        	throw new AdminException("Please provide a valid key to create a Product");
	        }
	        
	        if(adminId == loggedInUser.getUserId()) {
	       
			Product p = pdao.findById(product.getProductId()).get();
			if(p == null) {
				throw new AdminException("Product not found with id :"+ product.getProductId());
			}
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
	        else {
	        	throw new AdminException("Wrong details please login first");
	        	
		}
		
	}

	@Override
	public Product productById(Integer productId, String key, Integer adminId) throws AdminException {
		// TODO Auto-generated method stub
		  CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
	        
	        if(loggedInUser == null) {
	        	throw new AdminException("Please provide a valid key to create a Product");
	        }
	        
	        if(adminId == loggedInUser.getUserId()) {
	        	Product p = pdao.findById(productId).orElseThrow(()-> new AdminException("product not found with id :"+ productId));
	        	return p;
	        } 
	       
	        throw new AdminException("Wrong details please login first");
	        
		
	}

	@Override
	public List<Product> getAllProduct(String key, Integer adminId) throws AdminException {
		// TODO Auto-generated method stub
		CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
        
        if(loggedInUser == null) {
        	throw new AdminException("Please provide a valid key to create a Product");
        }
        
        if(adminId == loggedInUser.getUserId()) {
           List<Product> products = pdao.findAll();
           if(products.size()==0) {
        	   throw new AdminException("No product found");
           }
           return products;
        } 
       
        throw new AdminException("Wrong details please login first");
		

	}



}
