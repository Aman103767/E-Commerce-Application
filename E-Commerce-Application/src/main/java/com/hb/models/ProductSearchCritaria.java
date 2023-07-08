package com.hb.models;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
@Data
public class ProductSearchCritaria {
	@Autowired
  Product product = new Product();
  String price;
}
