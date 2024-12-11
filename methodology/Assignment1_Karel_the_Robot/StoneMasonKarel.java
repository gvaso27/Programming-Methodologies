
/*
 * File: BlankKarel.java
 * ---------------------
 * This class is a blank one that you can change at will.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	public void run() {
		while (frontIsClear()) {
			fixLine();
			moveToNextLine();
		}
		fixLine();
	}

	/*
	 * fixLine 
	 * fixes current column by putting single beepers in empty places
	 */
	private void fixLine() {
		turnLeft();
		while (frontIsClear()) {
			putBeepersIfNot();
			move();
		}
		putBeepersIfNot();
		goBack();
	}

	/*
	 * putBeepersIfNot 
	 * puts beeper where is not one present
	 */
	private void putBeepersIfNot() {
		if (noBeepersPresent()) {
			putBeeper();
		}
	}

	/*
	 * moveToNextLine 
	 * takes karel to the next column
	 */
	private void moveToNextLine() {
		for (int i = 0; i < 4; i++) {
			move();
		}
	}

	/*
	 * goBack 
	 * takes karel to the bottom of the current column
	 */
	private void goBack() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
		turnLeft();
	}
}
