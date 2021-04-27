package com.creditcard.creditcardservicewithauth.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This is a value object. It is used to receive request or generate response for client.
 * @author Administrator
 *
 */

@ApiModel(description="Credit Card information")
public class CreditCardDto {

	@NotEmpty(message="nameOnCard cannot be empty")
	@ApiModelProperty(notes="nameOnCard cannot be empty")
	private String nameOnCard;
	@Pattern(regexp = "\\d+", message="cardNumber only accepts numbers")
	@Size(max = 19, message="cardNumber should not be more than 19 digits")
	@ApiModelProperty(notes="cardNumber only accepts numbers and should not be more than 19 digits")
	private String cardNumber;
	private Double balance = 0.0;
	private Double creditLimit;
	
	public CreditCardDto() {
		
	}

	public CreditCardDto(String nameOnCard, String cardNumber, Double balance, Double creditLimit) {
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.creditLimit = creditLimit;
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
		
		return String.format("CreditCardValue [name=%s, number=%s, balance=%s, limit=%s ]", nameOnCard, cardNumber, balance, creditLimit);
	}

}

