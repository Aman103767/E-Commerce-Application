package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Product;
import com.masai.model.ProductDtoSec;

public interface ProductDtoDao extends JpaRepository<ProductDtoSec, Integer>{

}
