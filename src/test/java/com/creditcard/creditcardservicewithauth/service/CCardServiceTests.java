package com.creditcard.creditcardservicewithauth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import com.creditcard.creditcardservicewithauth.dtos.CreditCardDto;
import com.creditcard.creditcardservicewithauth.repository.CreditCardRepository;
import com.creditcard.creditcardservicewithauth.repository.entity.CreditCardEntity;


@ExtendWith(MockitoExtension.class)
public class CCardServiceTests {

	@Mock
	private CreditCardRepository creditCardRepository;
	private CreditCardDto creditCardValue1;
	@InjectMocks
	private CCardService cCardService;

	@BeforeEach
	public void setUp() {
		creditCardValue1 = new CreditCardDto("ABCDEF", "12345678", 0.0, 5000.0);

	}
	
	@Test
	public void testSave() {
		
		CreditCardEntity entity = new CreditCardEntity();
		BeanUtils.copyProperties(creditCardValue1, entity);
		entity.setId(100L);
		Mockito.when(creditCardRepository.save(Mockito.any())).thenReturn(entity);
		assertEquals(100L, cCardService.saveCreditCard(creditCardValue1));

	}
	
	public void testgetAllCreditCards() {
		
		CreditCardEntity entity1 = new CreditCardEntity();
		BeanUtils.copyProperties(creditCardValue1, entity1);
		CreditCardEntity entity2 = new CreditCardEntity();
		BeanUtils.copyProperties(creditCardValue1, entity2);
		List<CreditCardEntity> entities = Arrays.asList(entity1, entity2);  
		Mockito.when(creditCardRepository.findAll()).thenReturn(entities);
		assertEquals(2, cCardService.getAllCreditCards().size());
		
	}

}
