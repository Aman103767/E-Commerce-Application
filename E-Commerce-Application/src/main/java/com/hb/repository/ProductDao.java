package com.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.models.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

}
