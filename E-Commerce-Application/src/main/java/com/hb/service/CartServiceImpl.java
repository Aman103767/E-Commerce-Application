package com.hb.service;

import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.exceptions.AdminException;
import com.hb.exceptions.CartException;
import com.hb.exceptions.CustomerException;
import com.hb.models.Cart;

import com.hb.models.Customer;
import com.hb.models.Product;
import com.hb.models.ProductDtoSec;
import com.hb.repository.CartDao;
import com.hb.repository.CustomerDao;
import com.hb.repository.ProductDao;


@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private ProductDao pdao;
   @Autowired
	private CartDao cartdao;
   @Autowired
   private CustomerDao custdao;

	
	@Override
	public String addProductToCart(Integer customerId,Integer quantity,Integer productId) throws  CustomerException, CartException {
		// TODO Auto-generated method stub
	        	
//	          Optional<Product> pro = pdao.findById(productId);
//	          if(pro.isPresent() == false) {
	   
    Optional<Customer> c = custdao.findById(customerId);
	Optional<Product> p = pdao.findById(productId);
	if(p.isPresent()&& c.isPresent()) {
		Customer customer = c.get();
		Product product = p.get();
		
		if(product.getQuantity()< quantity || quantity == 0) {
			throw new CartException("Out of Stock");
		}
		Cart c1 = customer.getCart();
		  ProductDtoSec prodto2= new ProductDtoSec(); 
          prodto2.setProductId(productId);
          prodto2.setImagePath(product.getMainImg());
          prodto2.setProductName(product.getProductName());
          prodto2.setQuantity(quantity);
          prodto2.setDimension(product.getDimension());
          prodto2.setManufacturer(product.getManufacturer());
          prodto2.setPrice(product.getPrice());
          prodto2.setDiscountPercentage(product.getDiscountPercentage());
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

	@Override
	public List<ProductDtoSec> getAllProduct(Integer CustomerId ) throws CustomerException, CartException {
		
		
     
        
    
        Optional<Customer> cust = custdao.findById(CustomerId);
       if(cust.isPresent()== false) {
    	   throw new CustomerException("Customer not found");
       }
        Cart cart = cust.get().getCart();
        if(cart == null) {
        	throw new CartException("Please add product to cart first!");
        }
			if(cart.getCartproducts().size() == 0) {
				throw  new CartException("Cart is Empty");
			}
			return cart.getCartproducts();
	}
	
	

	@Override
	public String removeProductfromCart(Integer productId, Integer customerId) throws CustomerException, CartException {
		// TODO Auto-generated method stub
		 
	        
	    
	           Customer cust = custdao.findById(customerId).get();
	           Cart cart = cust.getCart();
	           if(cart == null) {
	        	   throw new CartException("Please first add product to the cart");
	           }
	           List<ProductDtoSec> products = cart.getCartproducts();
	           if(products.size() == 0) {
	        	   throw new CartException("Please first add product to the cart");
	           }
	          boolean flag = true;
	           for(int i =0;i<products.size();i++) {
	        	   if(productId == products.get(i).getProductId()) {
	        		   products.remove(i);
	        		   flag = false;
	        		   break;
	        	   }
	           }
	           if(flag) {
	        	   throw new CartException("Product is not added to cart please the product");
	           }
	           cart.setCartproducts(products);
	           cartdao.save(cart);
	           return "Product removed from the cart successfully";
	           
	        
			
	}

	@Override
	public ProductDtoSec updateQuantity(Integer productId,Integer quantity, Integer customerId)
			throws CustomerException, CartException {

	      
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
		        		  
		        		   if(quantity>product.getQuantity() || quantity == 0) {
		        			   throw new CartException("product out of Stock");
		        		   }
		        		   
		        		   products.get(i).setQuantity(quantity);
		        		   prodto = products.get(i);
		        		   System.out.println(prodto);
		        		   break;
		        	   }
		           }
		           cart.setCartproducts(products);
		           cartdao.save(cart);
		           
		          
	        	return prodto;
	        	
	        	
	        
		
	}

	@SuppressWarnings("unused")
	@Override
	public Cart removeAllProduct( Integer customerId) throws CustomerException, CartException {
	
	        
	      
	        
	   
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
		           
	        	
	        }
			
	
	
	
	
	

}
