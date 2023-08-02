package com.hb.models;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Reviews {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reviewId;
	
	private Integer rating;
	
	private String headline;
	
	private String  reviewMessage;
	
	@OneToOne
	private Helpful Helpful;
	
	private Integer orderId;
	
	private Integer customerId;
	
	private LocalDate reviewDate;

	public Reviews(Integer reviewId, Integer rating, String headline, String reviewMessage, Helpful countHelpful,
			Integer orderId, Integer customerId, LocalDate reviewDate) {
		super();
		this.reviewId = reviewId;
		this.rating = rating;
		this.headline = headline;
		this.reviewMessage = reviewMessage;
		this.Helpful = countHelpful;
		this.orderId = orderId;
		this.customerId = customerId;
		this.reviewDate = LocalDate.now();
	}
}
