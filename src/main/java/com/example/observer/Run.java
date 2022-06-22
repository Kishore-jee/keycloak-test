package com.example.observer;

/**
 * Observer -> followerONe, 2, 3,
 * YogaChannel -> Follower
 * 
 * @author kisho
 *
 */
public class Run {
	public static void main(String[] args) {

		YogaChannel publisher = new YogaChannel();
		FolllowerOne folllowerOne = new FolllowerOne();
		FollowerTwo followerTwo = new FollowerTwo();
		FollowerThree followerThree = new FollowerThree();
		
		publisher.addFollower(folllowerOne);
		publisher.addFollower(followerTwo);
		publisher.notifySubject(new MessageObserver("update notification."));

		System.out.println();
		System.out.println("Afer remove TWO\n");
		publisher.removeFollower(followerTwo);
		publisher.addFollower(followerThree);
		publisher.notifySubject(new MessageObserver("update notification."));
		
		System.out.println();
		publisher.showStatus(new MessageObserver("show status."));
		
	}
}
