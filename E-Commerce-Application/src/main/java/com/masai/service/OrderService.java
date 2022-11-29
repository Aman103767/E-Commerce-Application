package com.masai.service;

import java.util.List;

import com.masai.model.Product;

public interface OrderService {
  public List<Product> OrderProducts(Integer cartId);
}
