package com.hb.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Helpful {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; 
	Integer count;
	
	@OneToMany
	List<Customer> customers = new ArrayList<>();

}
