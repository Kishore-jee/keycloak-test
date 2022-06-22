package com.example.observer;

public class FollowerThree implements Observer {

	@Override
	public void updateMessage(MessageObserver msg) {
		System.out.println("FollowerThree: "+ msg.getMessage());
	}
	
	@Override
	public void showStatus(MessageObserver msg) {
		System.out.println("FollowerThree - showStatus: "+ msg.getMessage());
	}
}
