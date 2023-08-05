package com.hb.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "main_image")
	private String mainImg;
	
	@Column(name = "product_image")
	@ElementCollection
	private List<String> imagePath; 
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "price")
	private Double price;

	@Column(name ="discounted_price")
	private Double discountedPrice;
	
	@Column(name = "dimension")
	private String dimension;
	
	@Column(name = "specification")
	private String specification;
	
	@Column(name = "manufacturer")
	private String manufacturer;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "discount_percentage")
	private Integer discountPercentage;
	
	@Column(name = "in_delivery_days")
	private Integer inDeliveryDays;
	
	@Column(name ="avg_Rating")
	private Integer avgRating;
	
	@ElementCollection
	@Column(name = "about_item")
	private List<String> aboutItem;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category = new Category();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Reviews> reviews = new ArrayList<>();
	
	@OneToOne
	private Customer admin;
	
	
}
