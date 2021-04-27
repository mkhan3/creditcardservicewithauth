package com.creditcard.creditcardservicewithauth.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

@ExtendWith(MockitoExtension.class)
public class CustomizedResponseEntityExceptionHandlerTests {
	
	@Mock
	private WebRequest webRequest;
	
	@Mock
	private BindingResult bindingResult;

	@Test
	public void testHandleConstraintViolationException() {
		
		CustomizedResponseEntityExceptionHandler customizedResponseEntityExceptionHandler = new CustomizedResponseEntityExceptionHandler();
		ConstraintViolationException ex = new ConstraintViolationException("Exception message", new SQLException(), "constraint name");
		Mockito.when(webRequest.getDescription(false)).thenReturn("");
		ResponseEntity<Object> resEntity = customizedResponseEntityExceptionHandler.handleConstraintViolationException(ex, webRequest);
		assertEquals(HttpStatus.CONFLICT, resEntity.getStatusCode());
		
	}
	
	@Test
	public void testHandleInvalidCreditCardNumberException() {
		
		CustomizedResponseEntityExceptionHandler customizedResponseEntityExceptionHandler = new CustomizedResponseEntityExceptionHandler();
		InvalidCreditCardNumberException ex = new InvalidCreditCardNumberException("Exception message");
		Mockito.when(webRequest.getDescription(false)).thenReturn("");
		ResponseEntity<Object> resEntity = customizedResponseEntityExceptionHandler.handleInvalidCreditCardNumberException(ex, webRequest);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, resEntity.getStatusCode());
		
	}
	
	@Test
	public void testHandleMethodArgumentNotValid() {
		
		CustomizedResponseEntityExceptionHandler customizedResponseEntityExceptionHandler = new CustomizedResponseEntityExceptionHandler();
		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
 
		Mockito.when(bindingResult.getFieldErrors()).thenReturn(new ArrayList<FieldError>());
		
		ResponseEntity<Object> resEntity = customizedResponseEntityExceptionHandler.handleMethodArgumentNotValid(ex, null, null, webRequest);
		
		assertEquals(HttpStatus.BAD_REQUEST, resEntity.getStatusCode());
		
	}
	
	
}
