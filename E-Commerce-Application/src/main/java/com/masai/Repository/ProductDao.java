package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

}
