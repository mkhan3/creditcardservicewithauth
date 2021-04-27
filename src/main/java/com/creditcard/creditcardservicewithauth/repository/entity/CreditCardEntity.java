package com.creditcard.creditcardservicewithauth.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Credit card entity
 * @author Administrator
 *
 */
@Entity
public class CreditCardEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nameOnCard;
	@Column(unique = true)
	private String cardNumber;
	private Double balance = 0.0;
	private Double creditLimit;
	
	public CreditCardEntity() {
		
	}

	public CreditCardEntity(String nameOnCard, String cardNumber, Double balance, Double creditLimit) {
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.creditLimit = creditLimit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	@Override
	public String toString() {
		
		return String.format("CreditCardEntity [id=%s, name=%s, number=%s, balance=%s, limit=%s ]", id, nameOnCard, cardNumber, balance, creditLimit);
	}

}
