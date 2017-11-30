package com.citi.cards.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class CashCard {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
	private Long cardId;
	

	@Column
	private String cardNumber;
	
	@Column
	private String pinNumber;
	
	@Column
	private BigDecimal accountBalance;
	
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}
	
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	
	public CashCard(Long cardId, String cardNumber, String pinNumber, BigDecimal accountBalance) {
		super();
		this.cardId = cardId;
		this.cardNumber = cardNumber;
		this.pinNumber = pinNumber;
		this.accountBalance = accountBalance;
	}
	public CashCard() {
		super();
	}
	
	
}
