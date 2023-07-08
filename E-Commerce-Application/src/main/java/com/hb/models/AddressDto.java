package com.hb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressDto {
    private Integer addressId;
	private String buildingNo;
	private String name;
	private String mobileNumber;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private String address;
	private Boolean setDefault;
}
