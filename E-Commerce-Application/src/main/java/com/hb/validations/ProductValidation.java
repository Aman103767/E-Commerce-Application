package com.hb.validations;

import com.hb.models.PaginationDTO;
import com.hb.models.Reviews;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.hb.controller.ProductController;
import com.hb.models.ProductDTO;
@ControllerAdvice(assignableTypes = ProductController.class)
public class ProductValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
	boolean check = ProductDTO.class.equals(clazz);
	if(PaginationDTO.class.equals(clazz)){
		check = true;
	}
	if(Reviews.class.equals(clazz)){
		check = true;
	}
	return check;
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
