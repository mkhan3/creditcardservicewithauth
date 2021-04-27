package com.creditcard.creditcardservicewithauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creditcard.creditcardservicewithauth.repository.entity.CreditCardEntity;

/**
 * Credit card repository
 * @author Administrator
 *
 */
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long>{

	
}
