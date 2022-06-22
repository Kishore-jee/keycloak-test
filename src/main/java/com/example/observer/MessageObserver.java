package com.example.observer;

// immutable class
public class MessageObserver {

	final private String message;
	
	MessageObserver(String msg){
		this.message = msg;
	}
	
	public String getMessage() {
		return message;
	}
}
