
/*
 * File: BlankKarel.java
 * ---------------------
 * This class is a blank one that you can change at will.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	public void run() {
		goTo();
		pickBeeper();
		goBack();
	}

	/*
	 * goTo 
	 * takes karel to the destination
	 */
	private void goTo() {
		while (frontIsClear()) {
			move();
		}
		turnRight();
		move();
		turnLeft();
		move();
	}

	/*
	 * goBack 
	 * takes karel to the first location
	 */
	private void goBack() {
		turnAround();
		for (int i = 0; i < 3; i++) {
			move();
		}
		turnRight();
		move();
		turnRight();
	}
}
