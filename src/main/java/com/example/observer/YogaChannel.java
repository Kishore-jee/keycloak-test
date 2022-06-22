package com.example.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents to one Channel i.e Yoga Channel.
 * The channel can be some followers and that followers can get notifications and show some status of that channel
 * 
 * @author kisho
 *
 */
public class YogaChannel implements Follower {
	private List<Observer> observers = new ArrayList<Observer>();

	@Override
	public void addFollower(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeFollower(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void showStatus(MessageObserver messageObserver) {
		for (Observer observer : observers) {
			observer.showStatus(messageObserver);
		}
	}
	
	@Override
	public void notifySubject(MessageObserver messageObserver) {
		for (Observer observer : observers) {
			observer.updateMessage(messageObserver);
		}
	}
}
