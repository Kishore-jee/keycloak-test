package com.example.observer;

public class FolllowerOne implements Observer {

	@Override
	public void updateMessage(MessageObserver msg) {
		System.out.println("FolllowerOne: "+ msg.getMessage());
	}

	@Override
	public void showStatus(MessageObserver msg) {
		System.out.println("FolllowerOne - showStatus: "+ msg.getMessage());
	}
}
