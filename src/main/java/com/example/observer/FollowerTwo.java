package com.example.observer;

public class FollowerTwo implements Observer {

	@Override
	public void updateMessage(MessageObserver msg) {
		System.out.println("FollowerTwo: "+ msg.getMessage());
	}

	@Override
	public void showStatus(MessageObserver msg) {
		System.out.println("FollowerTwo - showStatus: "+ msg.getMessage());
	}
}
