package com.masai.service;

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

}
