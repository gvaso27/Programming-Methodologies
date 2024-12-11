
/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {

	public void run() {

		/* count operations */
		int operations = 0;

		int n = readInt("Enter a number: ");

		/* calculating n number until it becomes 1 */
		while (true) {
			if (n % 2 == 0) {
				println(n + " is even, so I take half: " + n / 2);
				n = n / 2;
			} else {
				println(n + " is odd, so I make 3n + 1: " + (3 * n + 1));
				n = 3 * n + 1;
			}

			/* counting operations */
			operations++;

			if (n == 1) {
				break;
			}
		}
		println("The process took " + operations + " operations to reach 1");
	}
}