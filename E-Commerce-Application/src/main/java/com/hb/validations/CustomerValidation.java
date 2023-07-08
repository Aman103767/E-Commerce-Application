package com.hb.validations;

import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;

import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.hb.controller.CustomerController;
import com.hb.models.CustomerDTO;

import ch.qos.logback.core.boolex.Matcher;


@ControllerAdvice(assignableTypes = CustomerController.class)
public class CustomerValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
	  return CustomerDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		CustomerDTO custDto =  (CustomerDTO)target;
		if(custDto.getUsername() == "" || custDto.getUsername() == null) {
			errors.rejectValue("username", "try again","username should not be null or empty");
		}
		if(custDto.getEmail() == null) {
			errors.rejectValue("email", "try again","email should not be null or empty");
		}
		else {
			if(custDto.getEmail().contains("@") == false) {
				errors.rejectValue("email", "try again","invalid email");
			}
		}
		Pattern pattern = Pattern.compile("[789]{1}[0-9]{9}");
		java.util.regex.Matcher matcher = pattern.matcher(custDto.getMobileNumber());
		if(matcher.matches() == false) {
				errors.rejectValue("mobileNumber", "try again","invalid Mobile Number");
		}
		Pattern pattern1 = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
		java.util.regex.Matcher matcher1 = pattern1.matcher(custDto.getPassword());
		if(matcher1.matches() == false) {
			errors.rejectValue("password", "try again","please enter strong password");
	}
		
		String [] arr  = custDto.getUsername().split(" ");
		for(String i: arr) {
			if(i.charAt(0)<65 || i.charAt(0)> 90) {
				 errors.rejectValue("username", "try again","name first and last character must be capital");
				

			}
		}

		
	   
		
	
	}

	
	
	

}
