package com.citi.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.cards.domain.CashCard;

public interface CashCardRepository extends JpaRepository<CashCard, Long> {

	public CashCard findByCardNumber(String cardNumber);

}
