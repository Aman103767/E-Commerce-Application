package com.masai.service;

import java.util.List;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.OrderException;
import com.masai.model.Product;


public interface OrderService {
  public List<Product> OrderProducts(Integer cartId,String key,Integer customerId) throws OrderException,CustomerException;
}
