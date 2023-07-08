package com.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.models.Cart;

public interface CartDao extends JpaRepository<Cart, Integer> {
	

}
