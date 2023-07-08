package com.hb.models;

import com.hb.security.JwtAuthResponse;

import lombok.Data;

@Data
public class AuthAddress {

	private AddressDto address;
	private String token;
}
   