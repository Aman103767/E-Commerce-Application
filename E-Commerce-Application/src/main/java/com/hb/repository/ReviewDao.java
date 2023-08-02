package com.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.models.Reviews;

public interface ReviewDao extends JpaRepository<Reviews,Integer> {

}
