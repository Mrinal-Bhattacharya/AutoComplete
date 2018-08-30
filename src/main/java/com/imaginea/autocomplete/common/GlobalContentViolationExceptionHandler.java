package com.imaginea.autocomplete.common;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalContentViolationExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {ConstraintViolationException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String handleValidationFailure(ConstraintViolationException ex) {

	    StringBuilder messages = new StringBuilder();

	    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	        messages.append(violation.getMessage() + "\n");
	    }

	    return messages.toString();
	}

}
