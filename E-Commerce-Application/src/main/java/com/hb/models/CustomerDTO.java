package com.hb.models;

import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	private Integer id;
//	@NotNull(message = "username cannot be null")
	//@Size(min=4 , message = "length of username must be above 4 letters")

	private String username;
	//@NotNull(message = "mobile number cannot be null")
	//@Pattern(regexp = "[789]{1}[0-9]{9}",message = "invalid mobile number")
	private String mobileNumber;
//	@NotNull(message = "password cannot be null")
//	@Size(min=6 , max = 10 , message = "length of password must be between 6 & 10")
	private String password;
	   
	private String email;
	
	private String role;

}
