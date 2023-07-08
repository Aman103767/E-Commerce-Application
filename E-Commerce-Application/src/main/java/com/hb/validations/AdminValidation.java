package com.hb.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.hb.models.Admin;
import com.hb.models.CustomerDTO;
import com.hb.models.ProductDTO;
@ControllerAdvice(assignableTypes = Admin.class)
public class AdminValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Admin.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
	    Admin admin =  (Admin)target;
		String [] arr  = admin.getUsername().split(" ");
		for(String i: arr) {
			if(i.charAt(0)<65 || i.charAt(0)> 90) {
				 errors.rejectValue("username", "try again","name first and last character of name must be capital");
				 return;

			}
		}
		
	}

}
