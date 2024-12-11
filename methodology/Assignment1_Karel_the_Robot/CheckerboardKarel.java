
/*
 * File: BlankKarel.java
 * ---------------------
 * This class is a blank one that you can change at will.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	public void run() {
		fillLine();
		while (leftIsClear()) {
			fillLine2();
			fillLine1();
		}
	}

	/*
	 * fillLine 
	 * it fills only the first line with beepers properly
	 */
	private void fillLine() {
		putBeeper();
		moveC();
		fill();
	}

	/*
	 * fillLine1 
	 * takes karel to the next line and if that line is odd it will be
	 * filled properly
	 */
	private void fillLine1() {
		if (leftIsClear()) {
			goUp();
			putBeeper();
			moveC();
			fill();
		}
	}

	/*
	 * fillLine2 
	 * takes karel to the next line and if that line is even it will be
	 * filled properly
	 */
	private void fillLine2() {
		if (leftIsClear()) {
			goUp();
			fill();
		}
	}

	/*
	 * moveC 
	 * is to make sure that the bug will not take place and it checks whether front
	 * is clear or not and if it is clear then moves one step
	 */
	private void moveC() {
		if (frontIsClear()) {
			move();
		}
	}

	/*
	 * goBack 
	 * takes karel to the start of the current line
	 */
	private void goBack() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
		turnAround();
	}

	/*
	 * goUp 
	 * takes karel to the next line
	 */
	private void goUp() {
		turnLeft();
		move();
		turnRight();
	}

	/*
	 * fill 
	 * puts Beepers in the line by skipping one square
	 */
	private void fill() {
		while (frontIsClear()) {
			moveC();
			putBeeper();
			moveC();
		}
		goBack();
	}
}
