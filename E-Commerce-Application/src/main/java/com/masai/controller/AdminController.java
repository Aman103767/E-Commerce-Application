package com.masai.controller;

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

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.OrderException;
import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.Product;
import com.masai.model.ProductDTO;
import com.masai.service.AdminService;
import com.masai.service.CustomerService;
import com.masai.service.OrderService;
import com.masai.service.ProductService;

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
	public  ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin,@RequestParam(required = false) String key ) throws AdminException {
		
		
		Admin updatedCustomer= AService.updateAdmin(admin, key);
				
		return new ResponseEntity<Admin>(updatedCustomer,HttpStatus.OK);
		
	}
	@DeleteMapping("/detete")
	public ResponseEntity<String> deleteAdmin(@RequestParam Integer adminId, @RequestParam String key) throws AdminException{
		
		String DeleteAdmin = AService.deleteAdmin(adminId, key);
		
		return new ResponseEntity<String>(DeleteAdmin,HttpStatus.OK);
	}
	@GetMapping("/viewAll/{adminId}/{key}")
	public ResponseEntity<List<Customer>> findAllCustomer(@PathVariable("adminId") Integer AdminId,@PathVariable("key") String key) throws CustomerException{
		
		List<Customer> customers = custService.viewCustomerAll(AdminId,key);
		
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
	
	@GetMapping("/viewById/{customerId}/{key}/{adminId}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable("customerId") Integer customerId, @PathVariable("key") String key,@PathVariable("adminId") Integer adminId) throws CustomerException{
		Customer customer = custService.viewCustomer(customerId,key,adminId);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		
	}
	

	
	@PostMapping("/createProduct")
	public ResponseEntity<Product> createProduct(@RequestBody ProductDTO product ,@RequestParam Integer adminId ,@RequestParam String key) throws AdminException {
		Product p = pService.createProduct(product,key,adminId);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	
    @DeleteMapping("/removeProduct")
	public ResponseEntity<String> removeProduct(@RequestParam Integer productId,@RequestParam String key,@RequestParam Integer AdminId) throws AdminException {
		String mess = pService.removeProduct(productId, key, AdminId);
		return new ResponseEntity<String>(mess,HttpStatus.OK);
	}
    
	@PutMapping("/updateProduct")
	public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO product ,@RequestParam Integer adminId ,@RequestParam String key) throws AdminException {
		Product p = pService.updateProduct(product,key,adminId);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	
	@GetMapping("/getProductById") 
	public ResponseEntity<Product> getProductById(@RequestParam Integer productId,@RequestParam Integer adminId,@RequestParam String key) throws AdminException{
		Product p = pService.productById(productId, key, adminId);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	
	@GetMapping("/getAllProduct") 
	public ResponseEntity<List<Product>> getProductById(@RequestParam Integer adminId,@RequestParam String key) throws AdminException{
		List<Product> p = pService.getAllProduct(key, adminId);
		return new ResponseEntity<List<Product>>(p,HttpStatus.OK);
	}
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<Orders>> getAllOrders(@RequestParam String key,@RequestParam Integer Adminid) throws OrderException, CustomerException {
		List<Orders> order = orderService.getAllOrders(key, Adminid);
		return new ResponseEntity<List<Orders>>(order,HttpStatus.OK);
	}

}
