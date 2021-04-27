package com.creditcard.creditcardservicewithauth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creditcard.creditcardservicewithauth.dtos.CreditCardDto;
import com.creditcard.creditcardservicewithauth.repository.CreditCardRepository;
import com.creditcard.creditcardservicewithauth.repository.entity.CreditCardEntity;

/**
 * An extra layer between API and repository. This manager is used to invoke creditcard repositories.
 * This layer will deal with entities and repositories.
 * 
 * @author Administrator
 *
 */
@Component
public class CCardService {
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	public Long saveCreditCard(CreditCardDto creditCardValue) {
		
		CreditCardEntity savedCreditCard = creditCardRepository.save(new CreditCardEntity(creditCardValue.getNameOnCard(), 
				creditCardValue.getCardNumber(), creditCardValue.getBalance(), creditCardValue.getCreditLimit()));
		
		return savedCreditCard.getId();
		
		
	}
	
	public List<CreditCardDto> getAllCreditCards(){
		
		List<CreditCardEntity> creditCardEntities = creditCardRepository.findAll();
		List<CreditCardDto> creditCardValues = new ArrayList<CreditCardDto>();
		for(CreditCardEntity source : creditCardEntities) {
			CreditCardDto target = new CreditCardDto();
			BeanUtils.copyProperties(source, target);
			creditCardValues.add(target);
		}
		
		return creditCardValues;
	}

}
