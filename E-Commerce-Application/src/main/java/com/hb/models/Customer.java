package com.hb.models;

import java.util.ArrayList;  
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User{
	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO) 
	private int customerId;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	private List<Address> addresses = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
    private Cart cart;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<Reviews> reviews = new ArrayList<>();

}
