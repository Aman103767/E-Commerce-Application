package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	private Integer id;
	private String name;
	private String mobileNumber;
	private String password;
	private String email;

}
