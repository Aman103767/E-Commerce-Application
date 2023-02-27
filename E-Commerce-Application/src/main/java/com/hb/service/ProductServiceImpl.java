package com.hb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.hb.exceptions.AdminException;
import com.hb.models.Admin;
import com.hb.models.Category;
import com.hb.models.CurrentUserSession;
import com.hb.models.Product;
import com.hb.models.ProductDTO;
import com.hb.repository.AdminDao;
import com.hb.repository.ProductDao;
import com.hb.repository.SessionDao;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao pdao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private SessionDao sessionDao;

	@Override
	public Product createProduct(ProductDTO product) throws AdminException {
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

	@Override
	public String removeProduct(Integer productId) throws AdminException {
		// TODO Auto-generated method stub
		
	        
	       
	         Product product =  pdao.findById(productId).orElseThrow(()->new AdminException("product odes not exist with id: "+ productId));
	          	
	        pdao.delete(product);
	        return "Product is remove successfully";
	           
	  
	}

	@Override
	public Product updateProduct(ProductDTO product) throws AdminException {
		// TODO Auto-generated method stub
		
	       
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
	      
	
	

	@Override
	public Product productById(Integer productId) throws AdminException {
		// TODO Auto-generated method stub
	
	       
	        	Product p = pdao.findById(productId).orElseThrow(()-> new AdminException("product not found with id :"+ productId));
	        	return p;
	        
	       
	      
	        
		
	}

	@Override
	public List<Product> getAllProduct() throws AdminException {
		// TODO Auto-generated method stub
	
     
        
      
           List<Product> products = pdao.findAll();
           if(products.size()==0) {
        	   throw new AdminException("No product found");
           }
           return products;
        

	}



}
