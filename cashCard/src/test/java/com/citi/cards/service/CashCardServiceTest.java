package com.citi.cards.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.citi.cards.constants.CardConstants;
import com.citi.cards.domain.CashCard;
import com.citi.cards.repository.CashCardRepository;
import com.citi.cards.response.CardResponse;
import com.citi.cards.tests.util.TestUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({})
public class CashCardServiceTest {

	@Mock
	private CashCardRepository cashCardRepository;
	
	@InjectMocks
	private CashCardService cashCardService;
	
	@Before
	public void initializeMockito() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addBalanceSuccessTest() throws Exception{
		Mockito.when(cashCardRepository.findByCardNumber(Mockito.any(String.class))).thenReturn(TestUtil.getCashCard());
		Mockito.when(cashCardRepository.saveAndFlush(Mockito.any(CashCard.class))).thenReturn(TestUtil.getCashCard());
		CardResponse<CashCard> cardResponse = cashCardService.addBalance(TestUtil.getCashCard());
		assertNotNull(cardResponse);
		assertEquals(cardResponse.getResponse().getAccountBalance(), new BigDecimal(20));
		assertEquals(cardResponse.getMessage(), CardConstants.SUCCESS);
	}
	
	@Test
	public void addBalanceFailureTest() throws Exception{
		Mockito.when(cashCardRepository.findByCardNumber(Mockito.any(String.class))).thenReturn(null);
		Mockito.when(cashCardRepository.saveAndFlush(Mockito.any(CashCard.class))).thenReturn(TestUtil.getCashCard());
		CardResponse<CashCard> cardResponse = cashCardService.addBalance(TestUtil.getCashCard());
		assertNotNull(cardResponse);
		assertEquals(cardResponse.getMessage(), CardConstants.INVALID_CARD_OR_PIN);
	}
	
	@Test
	public void spendBalanceSuccessTest() throws Exception{
		Mockito.when(cashCardRepository.findByCardNumber(Mockito.any(String.class))).thenReturn(TestUtil.getCashCard());
		Mockito.when(cashCardRepository.saveAndFlush(Mockito.any(CashCard.class))).thenReturn(TestUtil.getCashCard());
		CardResponse<CashCard> cardResponse = cashCardService.spendBalance(TestUtil.getCashCard());
		assertNotNull(cardResponse);
		assertEquals(cardResponse.getResponse().getAccountBalance(), new BigDecimal(0));
		assertEquals(cardResponse.getMessage(), CardConstants.SUCCESS);
	}
	
	@Test
	public void spendBalanceFailureTest() throws Exception{
		Mockito.when(cashCardRepository.findByCardNumber(Mockito.any(String.class))).thenReturn(null);
		Mockito.when(cashCardRepository.saveAndFlush(Mockito.any(CashCard.class))).thenReturn(TestUtil.getCashCard());
		CardResponse<CashCard> cardResponse = cashCardService.spendBalance(TestUtil.getCashCard());
		assertNotNull(cardResponse);
		assertEquals(cardResponse.getMessage(), CardConstants.INVALID_CARD_OR_PIN);
	}
	
	@Test
	public void addCardTest() throws Exception{
		Mockito.when(cashCardRepository.save(Mockito.any(CashCard.class))).thenReturn(TestUtil.getCashCard());
		CardResponse<CashCard> cardResponse = cashCardService.addCard(TestUtil.getCashCard());
		assertNotNull(cardResponse);
		assertEquals(cardResponse.getResponse().getAccountBalance(), new BigDecimal(10));
		assertEquals(cardResponse.getMessage(), CardConstants.SUCCESS);
		assertEquals(cardResponse.getResponse().getPinNumber(), CardConstants.PIN_DUMMY);
	}
	
	@Test
	public void getCardTest() throws Exception{
		Mockito.when(cashCardRepository.findByCardNumber(Mockito.any(String.class))).thenReturn(TestUtil.getCashCard());
		CardResponse<CashCard> cardResponse = cashCardService.getCard("1111111111111111");
		assertNotNull(cardResponse);
		assertEquals(cardResponse.getResponse().getAccountBalance(), new BigDecimal(10));
		assertEquals(cardResponse.getMessage(), CardConstants.SUCCESS);
		assertEquals(cardResponse.getResponse().getPinNumber(), CardConstants.PIN_DUMMY);
	}
}
