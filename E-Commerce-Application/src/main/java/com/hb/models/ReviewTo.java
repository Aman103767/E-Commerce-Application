package com.hb.models;

import java.time.LocalDate;

import javax.persistence.criteria.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewTo {
	private Integer reviewId;
	
	private Integer rating;
	
	private String headline;
	
	private String  reviewMessage;
	
	private Helpful countHelpful;
	
	private Orders order;
	
	private Customer customer;
	
	private LocalDate reviewDate;
}
