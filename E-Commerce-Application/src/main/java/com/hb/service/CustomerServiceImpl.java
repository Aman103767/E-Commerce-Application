package com.hb.service;

import java.util.List;  
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hb.controller.LoginController;
import com.hb.exceptions.CustomerException;
import com.hb.models.Address;
import com.hb.models.AddressDto;
import com.hb.models.Customer;
import com.hb.models.CustomerDTO;
import com.hb.models.Orders;
import com.hb.repository.AddressDao;
import com.hb.repository.CartDao;
import com.hb.repository.CustomerDao;
import com.hb.repository.OrderDao;
import com.hb.security.JwtAuthResponse;


@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao custDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private LoginController loginContoller;

	@Autowired
	private BCryptPasswordEncoder bcript;

	@Override
	public Customer createCustomer(CustomerDTO customer) throws CustomerException {

		Customer existingCustomer= custDao.findByMobileNumber(customer.getMobileNumber());

		if(existingCustomer != null) 
			throw new CustomerException("Customer Already Registered with Mobile number");

		// TODO Auto-generated method stub
		Customer cust = new Customer();
		cust.setUsername(customer.getUsername());
		cust.setEmail(customer.getEmail());
		cust.setMobileNumber(customer.getMobileNumber());
		cust.setPassword(bcript.encode(customer.getPassword()));
		cust.setRole(customer.getRole());
		custDao.save(cust);
		return cust;


	}

	@Override
	public Customer updateCustomer(CustomerDTO customer,Integer customerId) throws CustomerException{



		Customer cust = custDao.findById(customerId).get();
		cust.setUsername(customer.getUsername());
		cust.setEmail(customer.getEmail());
		cust.setMobileNumber(customer.getMobileNumber());
		cust.setPassword(bcript.encode(customer.getPassword()));
		//If LoggedInUser id is same as the id of supplied Customer which we want to update
		return custDao.save(cust);
	}




	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {





		// TODO Auto-generated method stub
		Optional<Customer> customer = custDao.findById(customerId);
		if(customer != null ) {
			return customer.get();
		}
		else {
			throw new CustomerException("No Customer found");
		}


	}

	@Override
	public List<Customer> viewCustomerAll() throws CustomerException {



		// TODO Auto-generated method stub
		List<Customer> customerList = custDao.findAll();
		if(customerList.size() != 0) {
			return customerList;
		}
		else {
			throw new CustomerException("No Customer found");
		}

	}

	@Override
	public String deleteCustomer(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		Optional<Customer> customer = custDao.findById(customerId);
		if(customer.isPresent()) {
			//cartDao.delete(customer.get().getCart());
			List<Orders> orders = orderDao.findAll();
			for(int i=0;i<orders.size();i++) {
				if(orders.get(i).getCustomer().getCustomerId() == customerId) {
					orderDao.delete(orders.get(i));
				}
			}


			custDao.delete(customer.get());
			return "account is deleted";
		}
		throw new CustomerException("Customer not found");

	}

	@Override
	public String addAddress(AddressDto addressDto,String token) throws CustomerException {
		// TODO Auto-generated method stub

		JwtAuthResponse jwt = new JwtAuthResponse();
		jwt.setToken(token);		
		Customer customer = loginContoller.getCurrentEmployee(jwt);
		if(customer == null) {
			throw new CustomerException("customer not found");
		}
		if(addressDto != null && addressDto.getAddressId()!= null) {
		    Address add = addressDao.findById(addressDto.getAddressId()).get();
			add.setBuildingNo(addressDto.getBuildingNo());
			add.setCity(addressDto.getCity());
			add.setName(addressDto.getName());
			add.setCountry(addressDto.getCountry());
			add.setPincode(addressDto.getPincode());
			add.setState(addressDto.getState());
			add.setMobileNumber(addressDto.getMobileNumber());
			add.setAddress(addressDto.getAddress());
			List<Address> addresses = customer.getAddresses();
			for(Address address : addresses) {
				if(address.getAddressId() == addressDto.getAddressId()) {
					address = add;
				}
			}
			customer.setAddresses(addresses);
			custDao.save(customer);
			return "updated Address Successfully";
		}
		else {

			Address address = new Address();
			address.setBuildingNo(addressDto.getBuildingNo());
			address.setCity(addressDto.getCity());
			address.setName(addressDto.getName());
			address.setCountry(addressDto.getCountry());
			address.setPincode(addressDto.getPincode());
			address.setState(addressDto.getState());
			address.setMobileNumber(addressDto.getMobileNumber());
			address.setAddress(addressDto.getAddress());
			addressDao.save(address); 
			if(customer.getAddresses().size() == 0) {
				address.setSetDefault(true);
			}
			customer.getAddresses().add(address);
			custDao.save(customer);

			return "added Address Successfully";
		}

	}

	@Override
	public List<Address> getAllAddress(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		Customer customer  = custDao.findById(customerId).get();
		if(customer == null) {
			throw new CustomerException("customer not found");
		}
		List<Address> addresses = customer.getAddresses();
		if(addresses.size()== 0) {
			throw new CustomerException("address not found! please add the address");
		}
		return addresses;
	}

	@Override
	public Address getAddressById(Integer addressId) throws CustomerException {
		// TODO Auto-generated method stub
		Address address = addressDao.findById(addressId).get();
		if(address == null) {
			throw new CustomerException("address not found");
		}
		return address;
	}

	@Override
	public String deleteAddressById(Integer addressId,Integer CustomerId) throws CustomerException {
		// TODO Auto-generated method stub
		Customer customer = custDao.findById(CustomerId).get();
		if(customer == null) {
			throw new CustomerException("customer not found");
		}
		List<Address> addresses = customer.getAddresses();
		Address add = addressDao.findById(addressId).get();
		if(add == null) {
			throw new CustomerException("Addresses does not exist");
		}
		if(addresses!= null || addresses.size()>0) {
			for(int i = 0;i< addresses.size();i++) {
				if(addresses.get(i).getAddressId() == addressId) {
					addresses.remove(i);
					break;
				}
			}
			customer.setAddresses(addresses);
			custDao.save(customer);
			addressDao.delete(add);
			return "address deleted succcesfully";
		}else {
			throw new CustomerException("Addresses not found");
		}
	}

	@Override
	public String setDefaultAddress(Integer addressId,Integer CustomerId) throws CustomerException {
		// TODO Auto-generated method stub
		Address address  = addressDao.findById(addressId).get();
		Customer customer = custDao.findById(CustomerId).get();
		if(customer == null) {
			throw new CustomerException("customer not found");
		}
		if(address == null) {
			throw new CustomerException("address is not found");
		}
		List<Address> addresses = customer.getAddresses();
		for(Address add : addresses) {
			if(address.getAddressId() == add.getAddressId()) {
				add.setSetDefault(true);
			}else {
				add.setSetDefault(false);
			}
		}
		custDao.save(customer);
		return "default is set";
	}


}
