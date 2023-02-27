package com.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.models.Product;
import com.hb.models.ProductDtoSec;

public interface ProductDtoDao extends JpaRepository<ProductDtoSec, Integer>{

}
