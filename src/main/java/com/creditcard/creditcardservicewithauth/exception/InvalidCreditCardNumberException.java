package com.creditcard.creditcardservicewithauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An Exception to handle invalid credit card number
 * @author Administrator
 *
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidCreditCardNumberException extends RuntimeException{

	public InvalidCreditCardNumberException(String message) {
		super(message);
		
	}

	
}
