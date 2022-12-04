package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	private String name;
	private String mobileNumber;
	private String password;
	private String email;
	private String buildingNo;
	private String city;
	private String state;
	private String country;
	private String pincode;
}
