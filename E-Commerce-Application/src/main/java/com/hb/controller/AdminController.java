package com.hb.controller;

import java.util.List; 

import javax.validation.Valid; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hb.exceptions.AdminException;
import com.hb.exceptions.CustomerException;
import com.hb.exceptions.OrderException;
import com.hb.exceptions.ProductException;
import com.hb.models.Admin;
import com.hb.models.Customer;
import com.hb.models.Orders;
import com.hb.models.Product;
import com.hb.models.ProductDTO;
import com.hb.service.AdminService;
import com.hb.service.CustomerService;
import com.hb.service.OrderService;
import com.hb.service.ProductService;
import com.hb.validations.AdminValidation;
import com.hb.validations.ProductValidation;

import io.swagger.models.Response;


@RestController
@RequestMapping(value = "/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
	
	@Autowired
	private AdminService AService;
	
	@Autowired
	private ProductService pService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
    private CustomerService custService;
	
   // @Autowired
	//private ProductValidation productValidator;
    
    @Autowired
    private  AdminValidation adminValidator;
	

    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	//binder.setValidator(productValidator);
    	binder.setValidator(adminValidator);
    }
   
	
	@PostMapping("/create")
	public ResponseEntity<?> saveAdmin(@Valid @RequestBody Admin admin, Errors errors) throws AdminException {
		
		if(errors.hasErrors()) {
			return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		
		Admin savedAdmin = AService.createAdmin(admin);
		
		
		return new ResponseEntity<Admin>(savedAdmin,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public  ResponseEntity<?> updateAdmin(@Valid @RequestBody Admin admin, Errors errors ) throws AdminException {
		
		if(errors.hasErrors()) {
			return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		
		Admin updatedCustomer= AService.updateAdmin(admin);
				
		return new ResponseEntity<Admin>(updatedCustomer,HttpStatus.OK);
		
	}
	@DeleteMapping("/detete/{adminId}")
	public ResponseEntity<String> deleteAdmin(@PathVariable Integer adminId) throws AdminException{
		
		String DeleteAdmin = AService.deleteAdmin(adminId);
		
		return new ResponseEntity<String>(DeleteAdmin,HttpStatus.OK);
	}
	@GetMapping("/viewAll")
	public ResponseEntity<List<Customer>> findAllCustomer() throws CustomerException{
		
		List<Customer> customers = custService.viewCustomerAll();
		
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
	
	@GetMapping("/viewById/{customerId}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable Integer customerId) throws CustomerException{
		Customer customer = custService.viewCustomer(customerId);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		
	}
	

	
	
	
    @DeleteMapping("/removeProduct/{productId}")
	public ResponseEntity<String> removeProduct(@PathVariable Integer productId) throws ProductException {
		String mess = pService.removeProduct(productId);
		return new ResponseEntity<String>(mess,HttpStatus.OK);
	}
    

	
	@GetMapping("/getProductById/{productId}") 
	public ResponseEntity<Product> getProductById(@PathVariable Integer productId) throws ProductException{
		Product p = pService.productById(productId);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	

	@GetMapping("/getAllOrders")
	public ResponseEntity<List<Orders>> getAllOrders() throws OrderException, CustomerException {
		List<Orders> orders = orderService.getAllOrders();
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
    @GetMapping("/getAllOrdersInLastMonth")
    public ResponseEntity<List<Orders>> getAllOrdersInLastMonth(){
    	List<Orders> orders = AService.getAllOrderLastMonth();
    	return new ResponseEntity<>(orders,HttpStatus.OK);
    }
    @GetMapping("/getSortedOrdersByAnyField/{field}")
	public ResponseEntity<List<Orders>> getSortedOrderByAnyField(@PathVariable String field) throws OrderException, CustomerException {
		List<Orders> orders = orderService.sortByfieldOrders(field);
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}

}
