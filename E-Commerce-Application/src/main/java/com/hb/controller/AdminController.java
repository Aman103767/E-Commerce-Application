package com.hb.controller;

import java.util.List; 

import javax.validation.Valid; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.hb.models.Admin;
import com.hb.models.Customer;
import com.hb.models.Orders;
import com.hb.models.Product;
import com.hb.models.ProductDTO;
import com.hb.service.AdminService;
import com.hb.service.CustomerService;
import com.hb.service.OrderService;
import com.hb.service.ProductService;

import io.swagger.models.Response;


@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	private AdminService AService;
	
	@Autowired
	private ProductService pService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
    private CustomerService custService;
	

	
	@PostMapping("/create")
	public ResponseEntity<Admin> saveAdmin(@Valid @RequestBody Admin admin) throws AdminException {
		
		Admin savedAdmin = AService.createAdmin(admin);
		
		
		return new ResponseEntity<Admin>(savedAdmin,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public  ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin) throws AdminException {
		
		
		Admin updatedCustomer= AService.updateAdmin(admin);
				
		return new ResponseEntity<Admin>(updatedCustomer,HttpStatus.OK);
		
	}
	@DeleteMapping("/detete")
	public ResponseEntity<String> deleteAdmin(@RequestParam Integer adminId) throws AdminException{
		
		String DeleteAdmin = AService.deleteAdmin(adminId);
		
		return new ResponseEntity<String>(DeleteAdmin,HttpStatus.OK);
	}
	@GetMapping("/viewAll")
	public ResponseEntity<List<Customer>> findAllCustomer() throws CustomerException{
		
		List<Customer> customers = custService.viewCustomerAll();
		
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
	
	@GetMapping("/viewById")
	public ResponseEntity<Customer> findCustomerById(@PathVariable("customerId") Integer customerId) throws CustomerException{
		Customer customer = custService.viewCustomer(customerId);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		
	}
	

	
	@PostMapping("/createProduct")
	public ResponseEntity<Product> createProduct(@RequestBody ProductDTO product ) throws AdminException {
		Product p = pService.createProduct(product);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	
    @DeleteMapping("/removeProduct")
	public ResponseEntity<String> removeProduct(@RequestParam Integer productId) throws AdminException {
		String mess = pService.removeProduct(productId);
		return new ResponseEntity<String>(mess,HttpStatus.OK);
	}
    
	@PutMapping("/updateProduct")
	public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO product ) throws AdminException {
		Product p = pService.updateProduct(product);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	
	@GetMapping("/getProductById") 
	public ResponseEntity<Product> getProductById(@RequestParam Integer productId) throws AdminException{
		Product p = pService.productById(productId);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	
	@GetMapping("/getAllProduct") 
	public ResponseEntity<List<Product>> getAllProduct() throws AdminException{
		List<Product> p = pService.getAllProduct();
		return new ResponseEntity<List<Product>>(p,HttpStatus.OK);
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
    @GetMapping("/getSortedOrdersByAnyField")
	public ResponseEntity<List<Orders>> getSortedOrderByAnyField(@RequestParam String field) throws OrderException, CustomerException {
		List<Orders> orders = orderService.sortByfieldOrders(field);
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}

}
