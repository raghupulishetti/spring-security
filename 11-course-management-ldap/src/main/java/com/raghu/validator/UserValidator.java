package com.raghu.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.raghu.model.UserDto;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDto user = (UserDto) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatPassword", "required");
		
		
		if(!user.getPassword().trim().equals(user.getRepeatPassword())) {
			errors.rejectValue("repeatPassword", "repeat.password.not.matched");
		}
		
	}

}
