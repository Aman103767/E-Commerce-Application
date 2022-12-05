package com.masai.service;

import java.util.List;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.OrderException;
import com.masai.model.AddressDto;
import com.masai.model.Product;
import com.masai.model.ProductDtoSec;


public interface OrderService {
  public List<ProductDtoSec> OrderProducts(Integer cartId,String key,Integer customerId,AddressDto Address) throws OrderException,CustomerException;
}
