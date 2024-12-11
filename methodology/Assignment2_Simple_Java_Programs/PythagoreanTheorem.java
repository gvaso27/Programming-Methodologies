
/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {

	public void run() {

		println("Enter values to compute Pythagorean theorem.");

		/* cathet parametres */
		int a = readInt("a: ");
		int b = readInt("b: ");

		/* calculating hypotenuse's square */
		int c = a * a + b * b;

		/* calculating hypotenuse */
		double result = Math.sqrt(c);

		println("c = " + result);
	}
}
