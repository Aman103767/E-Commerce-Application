package com.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.models.Orders;

public interface OrderDao extends JpaRepository<Orders, Integer>{

}
