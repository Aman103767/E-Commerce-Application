package com.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.models.Address;
import com.hb.models.Cart;

public interface AddressDao extends JpaRepository<Address, Integer> {

}
