
/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	private int SENTINEL = 0;

	public void run() {

		println("This program finds the largest and smallest numbers.");

		/* this variable is for the largest number */
		int x = SENTINEL;

		/* this variable is for the smallest number */
		int y = SENTINEL;

		/* this method calculates the largest and smallest numbers and prints them */
		while (true) {
			int n = readInt("? ");
			if (n > x) {
				x = n;
			}
			if (n < y) {
				y = n;
			}
			if (n == SENTINEL) {
				println("largest: " + x);
				println("smallest: " + y);
				break;
			}
		}
	}
}
