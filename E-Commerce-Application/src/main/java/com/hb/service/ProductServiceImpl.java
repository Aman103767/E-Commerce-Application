package com.hb.service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List; 
import java.util.Optional;

import com.hb.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hb.exceptions.AdminException;
import com.hb.exceptions.CustomerException;
import com.hb.exceptions.ProductException;
import com.hb.repository.AdminDao;
import com.hb.repository.CustomerDao;
import com.hb.repository.HelpfulDao;
import com.hb.repository.OrderDao;
import com.hb.repository.ProductCriteriaRepository;
import com.hb.repository.ProductDao;
import com.hb.repository.ReviewDao;


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
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private HelpfulDao helpfulDao;

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
		if(product.getDiscountPercentage() != null){
			p.setDiscountedPrice(product.getPrice() - (product.getPrice()*product.getDiscountPercentage())/100);
		}else {
			p.setDiscountedPrice(product.getPrice());
		}
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
			p.setMainImg(product.getMainImg());
			p.setQuantity(product.getQuantity());
			p.setImagePath(product.getImagePath());
			p.setSpecification(product.getSpecification());
			p.setDimension(product.getDimension());
			p.setManufacturer(product.getManufacturer());
			p.setAboutItem(product.getAboutItem());
			p.setPrice(product.getPrice());
			if(product.getDiscountPercentage() != null){
				p.setDiscountedPrice(product.getPrice() - (product.getPrice()*product.getDiscountPercentage())/100);
			}else {
				p.setDiscountedPrice(product.getPrice());
			}
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
	public Page<Orders> getOrder(ProductPage OrderPage,
									OrderSearchCritaria orderSearchCritaria){
		return productCriteriaRepository.findAllOrderWithFilter(OrderPage,orderSearchCritaria);
	}

	@Override
	public Reviews addAndUpdateReview(Integer productId, Integer customerId,Integer orderId, Reviews review) throws ProductException, CustomerException {
		// TODO Auto-generated method stub
		Product product = pdao.findById(productId).orElseThrow(()-> new ProductException("product not found"));
		Orders order = orderDao.findById(orderId).orElseThrow(()-> new ProductException("order not found"));
		Customer customer = custDao.findById(customerId).orElseThrow(()-> new CustomerException("custome not found please login first"));	
		if(review == null) {
			throw new ProductException("Please give the review first");
		}
		if(review.getReviewId() == null) {
			review.setCustomerId(customer.getCustomerId());
			review.setOrderId(order.getOrderId());
			review.setReviewDate(LocalDate.now());
			reviewDao.save(review);
			product.getReviews().add(review);
			pdao.save(product);
			return review;
		}else {
			Reviews productReview = reviewDao.findById(review.getReviewId()).get();
//			productReview.setCountHelpful(review.getCountHelpful());
			productReview.setHeadline(review.getHeadline());
			productReview.setRating(review.getRating());
			productReview.setCustomerId(customer.getCustomerId());
			productReview.setOrderId(order.getOrderId());
			productReview.setReviewMessage(review.getReviewMessage());
            for(Reviews rew : product.getReviews()) {
            	if(rew.getReviewId() == review.getReviewId()) {
            		rew = productReview;
            		break;
            	}
            }
            reviewDao.save(review);
            pdao.save(product);
			return review;
		}
		
	}

	@Override
	public Reviews getReview(Integer productId, Integer customerId, Integer orderId) throws ProductException, CustomerException {
		// TODO Auto-generated method stub
		Product product = this.pdao.findById(productId).orElseThrow(()-> new ProductException("product not found"));
		Orders order = this.orderDao.findById(orderId).orElseThrow(()-> new ProductException("order not found"));
		Customer customer = custDao.findById(customerId).orElseThrow(()-> new CustomerException("custome not found please login first"));
		Reviews productReview = null;
		boolean flag = false;
		for(ProductDtoSec o : order.getProducts()) {
			if(o.getProductId() == productId) {
				List<Reviews> reviews = product.getReviews();
				for(Reviews review : reviews) {
					if(review.getCustomerId() == customerId) {
						if(review.getOrderId() == orderId) {
							productReview = review;
							flag = true;
							break;
						}
					}
				}
				if(flag) {
					break;
				}
			}
			if(productReview == null) {
				throw new ProductException("Review is not found");
			}
		}
		
		return productReview;
	}

	@Override
	public List<ReviewTo> getAllReview(Integer productId) throws ProductException {
		// TODO Auto-generated method stub
		Product product = this.pdao.findById(productId).orElseThrow(()-> new ProductException("product not found"));
		List<ReviewTo> reviewsTo = new ArrayList<>();
		if(product.getReviews().size()>0) {
			for(Reviews review : product.getReviews()) {	
				ReviewTo rTo = new ReviewTo();
				rTo.setCountHelpful(review.getHelpful());
				rTo.setCustomer(custDao.findById(review.getCustomerId()).get());
				rTo.setHeadline(review.getHeadline());
				rTo.setOrder(orderDao.findById(review.getOrderId()).get());
				rTo.setRating(review.getRating());
				rTo.setReviewDate(review.getReviewDate());
				rTo.setReviewId(review.getReviewId());
				rTo.setReviewMessage(review.getReviewMessage());
				reviewsTo.add(rTo);
			}
			return reviewsTo;
		};
		throw new ProductException("review not found");
	}

	@Override
	public Reviews addHelpfullCount(Integer reviewId, Integer customerId) throws ProductException, CustomerException {
		// TODO Auto-generated method stub
		Customer customer = custDao.findById(customerId).orElseThrow(()-> new CustomerException("custome not found please login first"));
		Reviews reviews = this.reviewDao.findById(reviewId).orElseThrow(()-> new ProductException("product not found"));
		if(reviews.getHelpful() != null) {
		if(reviews.getHelpful().getCount()!= 0)
		reviews.getHelpful().setCount(reviews.getHelpful().getCount()+1);
		else 
		reviews.getHelpful().setCount(1);
		boolean flag = true;
		for(Customer  cust : reviews.getHelpful().getCustomers()) {
			if(cust.getCustomerId() == customerId) {
				flag = false;
			}
		}
		if(flag) {
		reviews.getHelpful().getCustomers().add(customer);	
		return reviewDao.save(reviews);
		}
		else {
			throw new ProductException("customer is already added count for helpfull");
		}
		}else {
			Helpful helpful = new Helpful();
			helpful.setCount(1);
			helpful.getCustomers().add(customer);
			helpfulDao.save(helpful);
			reviews.setHelpful(helpful);
			return reviewDao.save(reviews);
		}
	   
	 } 
}
