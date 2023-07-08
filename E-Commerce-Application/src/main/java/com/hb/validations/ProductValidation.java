package com.hb.validations;

import org.springframework.validation.Errors; 
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.hb.controller.ProductController;
import com.hb.models.ProductDTO;
@ControllerAdvice(assignableTypes = ProductController.class)
public class ProductValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
	
	return ProductDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		ProductDTO pDto = (ProductDTO)target;
		String name = pDto.getProductName();
		if(name.charAt(0)<65 || name.charAt(0)>90 ) {
			 errors.rejectValue("productName", "try again","Product Name should be start with capital letter");
			
		}
	 
	}

}
