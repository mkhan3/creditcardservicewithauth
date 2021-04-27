package com.creditcard.creditcardservicewithauth.exception;

import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Customised Exception handler. Exceptions across controllers will be caught here and corresponding customised 
 * response will be sent to client.   
 * @author Administrator
 *
 */

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	

	private static final String CARD_NUMBER_EXIST_ERROR = "This card number already exists";
	private static final String VALIDATION_ERROR = "Validation Failed";

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request)  {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), CARD_NUMBER_EXIST_ERROR, request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.CONFLICT);
	}
	
	
	@ExceptionHandler(InvalidCreditCardNumberException.class)
	public final ResponseEntity<Object> handleInvalidCreditCardNumberException(InvalidCreditCardNumberException ex, WebRequest request)  {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), VALIDATION_ERROR, processFieldErrors(ex.getBindingResult().getFieldErrors()));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	private String processFieldErrors(List<FieldError> fieldErrors) {
		StringBuilder strBuilder = new StringBuilder();
		for(FieldError fieldError : fieldErrors ) {
			strBuilder.append("[" + fieldError.getField() +"]" + ":" + fieldError.getDefaultMessage() + ";");
		}
		return strBuilder.toString();
	}
	
}
