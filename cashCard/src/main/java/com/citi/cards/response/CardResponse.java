package com.citi.cards.response;

import org.springframework.http.HttpStatus;

public class CardResponse<T> {

	private T response;
	
	private String message;
	
	

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CardResponse(T response, String message) {
		super();
		this.response = response;
		this.message = message;
	}

	
}
