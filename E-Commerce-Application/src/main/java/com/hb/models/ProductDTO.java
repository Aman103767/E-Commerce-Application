package com.hb.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Integer productId;
	private String mainImg;
	private List<String> imagePath; 
	private String productName;
	private String brand;
	private Double price;
	private String dimension;
	private String specification;
	private String manufacturer;
	private Integer quantity;
	private String categoryName;
	private List<String> aboutItem;
	private Integer discountPercentage;
	private Integer inDeliveryDays;

}
