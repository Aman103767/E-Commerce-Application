package com.masai.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO) 
	private int customerId;
	private String name;
	private String mobileNumber;
	private String email;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	

    

}
