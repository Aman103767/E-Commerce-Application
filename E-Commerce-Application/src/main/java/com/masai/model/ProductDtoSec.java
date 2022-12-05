package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoSec {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	private Integer productId;

	private String productName;

	private Double price;

	private String dimension;

	private String manufacturer;

	private Integer quantity;

}
