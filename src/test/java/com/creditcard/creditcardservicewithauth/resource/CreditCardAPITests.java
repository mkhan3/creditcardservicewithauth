package com.creditcard.creditcardservicewithauth.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creditcard.creditcardservicewithauth.dtos.CreditCardDto;
import com.creditcard.creditcardservicewithauth.service.CCardService;

@ExtendWith(MockitoExtension.class)
public class CreditCardAPITests {
	
	@Mock private CCardService creditCardManager;
	
	@InjectMocks private CreditCardAPI creditCardAPI;
	
	@Test
	public void testRetrieveAllCreditCards() {
		
		Mockito.when(creditCardManager.getAllCreditCards()).thenReturn(new ArrayList<CreditCardDto>());
		ResponseEntity<List<CreditCardDto>> resEntity = creditCardAPI.retrieveAllCreditCards();
		assertEquals(HttpStatus.OK, resEntity.getStatusCode());
	}

	
	

}
