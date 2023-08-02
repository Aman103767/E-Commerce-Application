package com.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.models.Helpful;

public interface HelpfulDao extends JpaRepository<Helpful, Integer> {

}
