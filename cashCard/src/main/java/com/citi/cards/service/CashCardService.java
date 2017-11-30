package com.citi.cards.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.citi.cards.constants.CardConstants;
import com.citi.cards.domain.CashCard;
import com.citi.cards.repository.CashCardRepository;
import com.citi.cards.response.CardResponse;

@Service
@EnableTransactionManagement
public class CashCardService {

	@Autowired
	private CashCardRepository cashCardRepository;
	
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public CardResponse<CashCard> addCard(CashCard cashCard){
		
		CashCard cashCardPersistant = cashCardRepository.findByCardNumber(cashCard.getCardNumber());
		
		if(cashCardPersistant != null){
			return new CardResponse<>(cashCardPersistant, CardConstants.ERROR_CARD_ALREADY_EXISTS);
		}else{
			CashCard cashcard = cashCardRepository.save(cashCard);
			cashcard.setPinNumber(CardConstants.PIN_DUMMY);
			return new CardResponse<>(cashcard, CardConstants.SUCCESS);
		}
		
		
	}
	
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public CardResponse<CashCard> updateCard(CashCard cashCard){
		
		if(cashCard.getCardId() == null){
			cashCard.setPinNumber(CardConstants.PIN_DUMMY);
			return new CardResponse<>(cashCard, CardConstants.CARD_ID_CANNOT_BE_NULL);
		}else{
			CashCard cashCardRepoResponse = cashCardRepository.save(cashCard);
			cashCardRepoResponse.setPinNumber(CardConstants.PIN_DUMMY);
			return new CardResponse<>(cashCardRepoResponse, CardConstants.SUCCESS);
		}
		
	}
	
	public CardResponse<CashCard> getCard(String cardNumber){
		
		if(cardNumber == null){
			return new CardResponse<>(null, CardConstants.INVALID_CARD_OR_PIN);
		}else {
			CashCard cashCardRepoResponse = cashCardRepository.findByCardNumber(cardNumber);
			if(cashCardRepoResponse == null){
				return new CardResponse<>(null, CardConstants.INVALID_CARD_OR_PIN);
			}else{
				cashCardRepoResponse.setPinNumber(CardConstants.PIN_DUMMY);
				return new CardResponse<>(cashCardRepoResponse, CardConstants.SUCCESS);
			}
		
		}
		
	}
	
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public CardResponse<CashCard> addBalance(CashCard cashCard){
		
		CashCard cashCardPersistant = cashCardRepository.findByCardNumber(cashCard.getCardNumber());
		
		if(cashCardPersistant != null){
			BigDecimal updatedbalance = cashCardPersistant.getAccountBalance().add(cashCard.getAccountBalance());
			cashCardPersistant.setAccountBalance(updatedbalance);
			cashCardRepository.saveAndFlush(cashCardPersistant);
			cashCardPersistant.setPinNumber(CardConstants.PIN_DUMMY);
			return new CardResponse<>(cashCardPersistant, CardConstants.SUCCESS);
		}else{
			cashCard.setPinNumber(CardConstants.PIN_DUMMY);
			return new CardResponse<>(cashCard, CardConstants.INVALID_CARD_OR_PIN);
		}
	}
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public CardResponse<CashCard> spendBalance(CashCard cashCard){
		
		CashCard cashCardPersistant = cashCardRepository.findByCardNumber(cashCard.getCardNumber());
		if(cashCardPersistant != null){
			if(cashCardPersistant.getAccountBalance().compareTo(cashCard.getAccountBalance()) < 0){
				return new CardResponse<>(cashCardPersistant, CardConstants.NOT_ENOUGH_BALANCE);
			}else{
				BigDecimal updatedbalance = cashCardPersistant.getAccountBalance().subtract(cashCard.getAccountBalance());
				cashCardPersistant.setAccountBalance(updatedbalance);
				cashCardRepository.saveAndFlush(cashCardPersistant);
				cashCardPersistant.setPinNumber(CardConstants.PIN_DUMMY);
				return new CardResponse<>(cashCardPersistant, CardConstants.SUCCESS);
			}
		}else{
			cashCard.setPinNumber(CardConstants.PIN_DUMMY);
			return new CardResponse<>(cashCard, CardConstants.INVALID_CARD_OR_PIN);
		}
	}
	
}
