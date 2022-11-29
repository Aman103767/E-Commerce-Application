package com.masai.service;

import java.util.List;

import com.masai.exceptions.OrderException;
import com.masai.model.Product;

public interface OrderService {
  public List<Product> OrderProducts(Integer cartId) throws OrderException;
}
