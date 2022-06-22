package com.example.observer;

/**
 * 
 * @author kisho
 *
 */
public interface Observer {
	void updateMessage(MessageObserver msg);

	void showStatus(MessageObserver msg);
}
