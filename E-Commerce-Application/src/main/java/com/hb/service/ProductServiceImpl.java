package com.hb.service;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hb.exceptions.AdminException;
import com.hb.exceptions.CustomerException;
import com.hb.exceptions.ProductException;
import com.hb.models.Admin;
import com.hb.models.Category;
import com.hb.models.Customer;
import com.hb.models.Orders;
import com.hb.models.Product;
import com.hb.models.ProductDTO;
import com.hb.models.ProductPage;
import com.hb.models.ProductSearchCritaria;
import com.hb.repository.AdminDao;
import com.hb.repository.CustomerDao;
import com.hb.repository.ProductCriteriaRepository;
import com.hb.repository.ProductDao;


@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao pdao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private CustomerDao custDao;
	@Autowired
	private  ProductCriteriaRepository productCriteriaRepository;


	@Override
	public Product createProduct(ProductDTO product,Integer adminId) throws ProductException, CustomerException {
		// TODO Auto-generated method stub
		Customer admin = custDao.findById(adminId).get();
		if(admin == null) {
			throw new CustomerException("Admin not found");
		}
		Product p = new Product();
		p.setProductName(product.getProductName());
		p.setMainImg(product.getMainImg());		
		p.setImagePath(product.getImagePath());
		p.setBrand(product.getBrand());
		p.setQuantity(product.getQuantity());
		p.setSpecification(product.getSpecification());
		p.setDimension(product.getDimension());
		p.setManufacturer(product.getManufacturer());
		p.setPrice(product.getPrice());
		p.setAboutItem(product.getAboutItem());
		p.setDiscountPercentage(product.getDiscountPercentage());
		p.setInDeliveryDays(product.getInDeliveryDays());
		p.setAdmin(admin);
		Category c = new Category();
		c.setCategoryName(product.getCategoryName());
		p.setCategory(c);
		c.getProducts().add(p);
	
		
		pdao.save(p);
		
		return p;
	
        
	

	}

	@Override
	public String removeProduct(Integer productId) throws ProductException {
		// TODO Auto-generated method stub
		
	        
	       
	         Product product =  pdao.findById(productId).orElseThrow(()->new ProductException("product does not exist with id: "+ productId));
	          	
	        pdao.delete(product);
	        return "Product is remove successfully";
	           
	  
	}

	@Override
	public Product updateProduct(ProductDTO product,Integer productId) throws ProductException {
		// TODO Auto-generated method stub
		
	       
			Optional<Product> p1 = pdao.findById(productId);
			if(p1.isPresent() == false) {
				throw new ProductException("Product not found with id :"+ product.getProductId());
			}
			Product p = p1.get();
			p.setProductName(product.getProductName());
			p.setQuantity(product.getQuantity());
			p.setImagePath(product.getImagePath());
			p.setSpecification(product.getSpecification());
			p.setDimension(product.getDimension());
			p.setManufacturer(product.getManufacturer());
			p.setAboutItem(product.getAboutItem());
			p.setPrice(product.getPrice());
			Category c = p.getCategory();
			c.setCategoryName(product.getCategoryName());
			List<Product> products = c.getProducts();
			for(int i = 0;i<products.size();i++) {
				if(products.get(i).getProductId() == productId) {
					products.set(i, p);
					break;
				}
			}
			c.setProducts(products);
			p.setCategory(c);
			pdao.save(p);
			
			return p;
		}
	      
	

	
	@Override
	public Product productById(Integer productId) throws ProductException {
		// TODO Auto-generated method stub
	
	       
	        	Product p = pdao.findById(productId).orElseThrow(()-> new ProductException("product not found with id :"+ productId));
	        	return p;
	        
	       
	      
	        
		
	}

	@Override
	public List<Product> getAllProduct() throws ProductException {
		// TODO Auto-generated method stu
		
           List<Product> products = pdao.findAll();
           if(products.size()==0) {
        	   throw new ProductException("No product found");
           }
           return products;
        

	}
	@Override
    public List<Product> sortProductAsc(String field) throws ProductException{

    	List<Product> products = pdao.findAll(Sort.by(Sort.Direction.ASC,field));
    	if(products.size()==0) {
    		throw new ProductException("No product found");
    	}
		return products;
   
    }
	@Override
    public Page<Product> findProductWithPagination(int offset, int pageSize){
    	Page<Product> page = pdao.findAll(PageRequest.of(offset, pageSize));
    return page;
    }

	@Override
	public List<Product> searchProductByName(String name) throws ProductException {
		// TODO Auto-generated method stub
		List<Product> products =  pdao.searchAllProductByName(name);
		if(products.size()==0) {
		  throw	new ProductException("No product found");
		}
	    return products;
	}

	@Override
	public List<Product> sortProductDsc(String field) throws ProductException {
		// TODO Auto-generated method stub
		List<Product> products = pdao.findAll(Sort.by(Sort.Direction.DESC,field));
    	if(products.size()==0) {
    		throw new ProductException("No product found");
    	}
		return products;
		
	}
	public Page<Product> getProduct(ProductPage productPage, 
			ProductSearchCritaria productSearchCritaria){
		return productCriteriaRepository.findAllWithFilters(productPage,productSearchCritaria);
	}
	

}
