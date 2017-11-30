package com.citi.cards.rest;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citi.cards.domain.CashCard;
import com.citi.cards.repository.CashCardRepository;
import com.citi.cards.response.CardResponse;
import com.citi.cards.service.CashCardService;

@Controller
@RequestMapping("/cashCard")
public class CashCardResource {

	@Autowired
	private CashCardService cashCardService;
	
	
	@PostMapping("/add")
	public ResponseEntity<CardResponse<CashCard>> addCard(@RequestBody CashCard cashCard){
		return new ResponseEntity<>(cashCardService.addCard(cashCard), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<CardResponse<CashCard>> updateCard(@RequestBody CashCard cashCard){
		return new ResponseEntity<>(cashCardService.updateCard(cashCard), HttpStatus.OK);
		
	}
	
	
	@GetMapping("/get/{cardNumber}")
	public ResponseEntity<CardResponse<CashCard>> getCard(@PathVariable String cardNumber){
		return new ResponseEntity<>(cashCardService.getCard(cardNumber), HttpStatus.OK);
		
	}
	
	@PostMapping("/addBalance")
	public ResponseEntity<CardResponse<CashCard>> addBalance(@RequestBody CashCard cashCard){
		return  new ResponseEntity<>(cashCardService.addBalance(cashCard), HttpStatus.OK);
		
	}
	
	@PostMapping("/spendBalance")
	public ResponseEntity<CardResponse<CashCard>> spendBalance(@RequestBody CashCard cashCard){
		return  new ResponseEntity<>(cashCardService.spendBalance(cashCard), HttpStatus.OK);
		
	}
	
	
	
}
