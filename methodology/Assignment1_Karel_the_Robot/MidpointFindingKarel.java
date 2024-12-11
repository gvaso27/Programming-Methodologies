
/*
 * File: BlankKarel.java
 * ---------------------
 * This class is a blank one that you can change at will.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	public void run() {
		fillLine();
		cornerSweep();
		while (beepersPresent()) {
			sweep();
		}
		turnAround();
		moveC();
		putBeeperIfNot();
	}

	/*
	 * putBeepersIfNot 
	 * puts beeper where is not one present
	 */
	private void putBeeperIfNot() {
		if (noBeepersPresent()) {
			putBeeper();
		}
	}

	/*
	 * fllLine 
	 * fills current line with beepers
	 */
	private void fillLine() {
		while (frontIsClear()) {
			putBeeper();
			moveC();
		}
		putBeeper();
		goBack();
	}

	/*
	 * goBack 
	 * takes karel to the starting location
	 */
	private void goBack() {
		turnAround();
		while (frontIsClear()) {
			moveC();
		}
		turnAround();
	}

	/*
	 * cornerSweep 
	 * takes beepers from the start of the line and from the last square
	 * of the line
	 */
	private void cornerSweep() {
		pickBeeper1();
		while (frontIsClear()) {
			moveC();
		}
		turnAround();
		pickBeeper1();

	}

	/*
	 * pickBeeper1 
	 * if front is beeper karel will take beeper from current location
	 * and move one step
	 */
	private void pickBeeper1() {
		moveC();
		if (beepersPresent()) {
			moveBack();
			pickBeeper();
			moveC();
		}
	}

	/*
	 * moveBack 
	 * karel moves back with one step
	 */
	private void moveBack() {
		turnAround();
		moveC();
		turnAround();
	}

	/*
	 * sweep 
	 * karel takes all beepers from the current line except the one in the
	 * middle
	 */
	private void sweep() {
		while (beepersPresent()) {
			moveC();
		}
		moveBack();
		turnAround();
		if (beepersPresent()) {
			pickBeeper1();
		}
	}

	/*
	 * moveC 
	 * is to make sure that the bug will not take place and it checks whether front
	 * is clear or not and if it is clear then moves one step
	 */
	private void moveC() {
		if (frontIsClear())
			move();
	}
}
