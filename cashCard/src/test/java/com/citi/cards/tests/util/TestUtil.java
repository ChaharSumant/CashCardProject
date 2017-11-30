package com.citi.cards.tests.util;

import java.math.BigDecimal;

import com.citi.cards.domain.CashCard;

public class TestUtil {

	
	public static CashCard getCashCard(){
		CashCard cashCard = new CashCard();
		cashCard.setCardId(1234L);
		cashCard.setCardNumber("1111111111111111");
		cashCard.setPinNumber("2222");
		cashCard.setAccountBalance(new BigDecimal(10));
		return cashCard;
	}
	
}
