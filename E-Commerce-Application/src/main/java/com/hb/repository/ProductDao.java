package com.hb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hb.models.Product;
@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{
	
	@Query(value = "Select * from Product where product_Name LIKE %?1%", nativeQuery = true)
	public List<Product> searchAllProductByName(String s);

}
