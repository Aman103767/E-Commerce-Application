package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.model.Cart;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Product;
import com.masai.model.ProductDtoSec;
import com.masai.repository.CartDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.ProductDao;
import com.masai.repository.SessionDao;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private ProductDao pdao;
   @Autowired
	private CartDao cartdao;
   @Autowired
   private CustomerDao custdao;
   @Autowired
   private SessionDao sessionDao;
	
	@Override
	public String addProductToCart(Integer customerId,Integer quantity,Integer productId ,String key) throws  CustomerException, CartException {
		// TODO Auto-generated method stub
		 CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
		  if(loggedInUser == null) {
	        	throw new CustomerException("Please provide a valid key to get all products");
	        }
	        
	        if(customerId == loggedInUser.getUserId()) {
	        	
//	          Optional<Product> pro = pdao.findById(productId);
//	          if(pro.isPresent() == false) {
	   
    Optional<Customer> c = custdao.findById(customerId);
	Optional<Product> p = pdao.findById(productId);
	if(p.isPresent()&& c.isPresent()) {
		Customer customer = c.get();
		Product product = p.get();
		
		if(product.getQunatity()< quantity || quantity == 0) {
			throw new CartException("Out of Stock");
		}
		Cart c1 = customer.getCart();
		  ProductDtoSec prodto2= new ProductDtoSec(); 
          prodto2.setProductId(productId);
          prodto2.setProductName(product.getProductName());
          prodto2.setQuantity(quantity);
          prodto2.setDimension(product.getDimension());
          prodto2.setManufacturer(product.getManufacturer());
          prodto2.setPrice(product.getPrice());
		if(c1 != null) {
		//Cart cart = new Cart();
//		if(cart.getCartId() != null) {
//			cart.setCartId(cart.getCartId());
//		}
		List<ProductDtoSec> list = c1.getCartproducts();
		for(int i=0;i<list.size();i++) {
			if(productId == list.get(i).getProductId()) {
			   return "Product is already added to the cart";
			}
		}
	     
	          
		
		c1.getCartproducts().add(prodto2);
		}
		else {
		//	return c1.toString()
			c1 = new Cart();
			c1.getCartproducts().add(prodto2);
		    c1.setCustomer(customer);
		    customer.setCart(c1);
		    
		    
		}
		cartdao.save(c1);
		
		return "Product added to the cart";
	}
		
	      throw new CartException("Customerid or productid not found");
//	          }else {
//	        	  throw new CartException("product already added to the cart");
//	          }
	        }
	        else {
	    		throw new CustomerException("wrong Details please login first!");
	    	}
	}

	@Override
	public List<ProductDtoSec> getAllProduct(String key,Integer CustomerId ) throws CustomerException, CartException {
		
		  
        CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
        
        if(loggedInUser == null) {
        	throw new CustomerException("Please provide a valid key to get all products");
        }
        
        if(CustomerId == loggedInUser.getUserId()) {
        Customer cust = custdao.findById(CustomerId).get();
        Cart cart = cust.getCart();
        if(cart == null) {
        	throw new CartException("Please add product to cart first!");
        }
			if(cart.getCartproducts().size() == 0) {
				throw  new CartException("Cart is Empty");
			}
			return cart.getCartproducts();
	}
	else {
		throw new CustomerException("wrong Details please login first!");
	}
	}

	@Override
	public String removeProductfromCart(Integer productId,String key, Integer customerId) throws CustomerException, CartException {
		// TODO Auto-generated method stub
		 CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
	        
	        if(loggedInUser == null) {
	        	throw new CustomerException("Please provide a valid key to get all products");
	        }
	        
	        if(customerId == loggedInUser.getUserId()) {
	           Customer cust = custdao.findById(customerId).get();
	           Cart cart = cust.getCart();
	           if(cart == null) {
	        	   throw new CartException("Please first add product to the cart");
	           }
	           List<ProductDtoSec> products = cart.getCartproducts();
	           if(products.size() == 0) {
	        	   throw new CartException("Please first add product to the cart");
	           }
	           for(int i =0;i<products.size();i++) {
	        	   if(productId == products.get(i).getProductId()) {
	        		   products.remove(i);
	        		   break;
	        	   }
	           }
	           cart.setCartproducts(products);
	           cartdao.save(cart);
	           return "Product removed from the cart successfully";
	           
	        }else {
	    		throw new CustomerException("wrong Details please login first!");
	    	}
			
	}

	@Override
	public ProductDtoSec updateQuantity(Integer productId,Integer quantity, String key, Integer customerId)
			throws CustomerException, CartException {
		 CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
	        
	        if(loggedInUser == null) {
	        	throw new CustomerException("Please provide a valid key to get all products");
	        }
	        
	        if(customerId == loggedInUser.getUserId()) {
	        	Customer cust = custdao.findById(customerId).get();
	        	Cart cart = cust.getCart();
	        	if(cart == null) {
	        		throw new CartException("Please first add product to the cart");
	        	}
	        	List<ProductDtoSec> products = cart.getCartproducts();
		           if(products.size() == 0) {
		        	   throw new CartException("Please first add product to the cart");
		           }
		           
		         Product product = pdao.findById(productId).get();
		         if(product == null) {
		        	 throw new CartException("product not found with id "+ productId);
		         }
		         ProductDtoSec prodto = null;
		           for(int i =0;i<products.size();i++) {
		        	   if(productId == products.get(i).getProductId()) {
		        		  
		        		   if(quantity>product.getQunatity() || quantity == 0) {
		        			   throw new CartException("product out of Stock");
		        		   }
		        		   
		        		   products.get(i).setQuantity(quantity);
		        		   prodto = products.get(i);
		        		   break;
		        	   }
		           }
		           cart.setCartproducts(products);
		           cartdao.save(cart);
		          
	        	return prodto;
	        	
	        	
	        	
	        }else {
	    		throw new CustomerException("wrong Details please login first!");
	    	}
		
		
	}

	@SuppressWarnings("unused")
	@Override
	public Cart removeAllProduct(String key, Integer customerId) throws CustomerException, CartException {
		// TODO Auto-generated method stub
		 CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
	        
	        if(loggedInUser == null) {
	        	throw new CustomerException("Please provide a valid key to get all products");
	        }
	        
	        if(customerId == loggedInUser.getUserId()) {
	        	Customer cust = custdao.findById(customerId).get();
	        	Cart cart = cust.getCart();
	        	if(cust == null) {
	        		throw new CartException("please add product to the cart first!");
	        	}
	        	List<ProductDtoSec> products = cart.getCartproducts();
		           if(products.size() == 0) {
		        	   throw new CartException("Please first add product to the cart");
		           }
		           
		           cart.setCartproducts(new ArrayList<>());
		           return cartdao.save(cart);
		           
	        	
	        }else {
	        	throw new CustomerException("wrong Details please login first!");
	        }
			
	}
	
	
	
	

}
