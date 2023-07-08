package com.hb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  int addressId;
	private String buildingNo;
	private String name;
	private String mobileNumber;
	private String city;
	private String state;
	private String country; 
	private String pincode;
	private boolean setDefault;
	private String address;

	
}
