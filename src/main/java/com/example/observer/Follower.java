package com.example.observer;

public interface Follower {
	void addFollower(Observer observer);

	void removeFollower(Observer observer);

	void notifySubject(MessageObserver messageObserver);

	void showStatus(MessageObserver messageObserver);
}
