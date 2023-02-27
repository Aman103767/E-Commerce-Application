package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Integer productId;
	private String productName;
	private double price;
	private String dimension;
	private String specification;
	private String manufacturer;
	private int qunatity;
	private String CategoryName;

}
