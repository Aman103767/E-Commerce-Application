package com.hb.service;

import java.util.List;

import com.hb.exceptions.CustomerException;
import com.hb.exceptions.OrderException;
import com.hb.models.AddressDto;
import com.hb.models.Orders;
import com.hb.models.Product;
import com.hb.models.ProductDtoSec;


public interface OrderService {
  public Orders OrderProducts(Integer customerId,AddressDto Address) throws OrderException,CustomerException;
  
  public String cancelOrder(Integer orderId ) throws OrderException,CustomerException;
  
  public Orders getOrderById(Integer orderId) throws OrderException,CustomerException;
  
  public List<Orders> getAllOrders() throws OrderException,CustomerException;
  
	public List<Orders> sortByfieldOrders(String field) throws OrderException, CustomerException;
}
