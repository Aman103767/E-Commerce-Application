package com.masai.model;

import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Orders {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private LocalDate orderDate;
	private String OrderStatus;
	@ManyToOne
	private Customer customer;
	
	@ElementCollection
	@CollectionTable(name="product_order", joinColumns = @JoinColumn(name="order_id", referencedColumnName = "orderId"))
	private List<ProductDtoSec> products = new ArrayList<>();
	@OneToOne
	private Address address;

}

