
/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/* Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/* Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/* Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	public void run() {

		int HEIGHT = getHeight();
		int WIDTH = getWidth();
		/* j controls vertical coordinates */
		for (int j = 0; j < BRICKS_IN_BASE + 1; j++) {
			int startY = HEIGHT - (BRICK_HEIGHT * (j + 1));
			/*
			 * i controls horizontal coordinates and (BRICKS_IN_BASE - j) determines number
			 * of blocks in each line
			 */
			for (int i = 0; i < BRICKS_IN_BASE - j; i++) {
				int startX = WIDTH / 2 - ((BRICKS_IN_BASE - j) * BRICK_WIDTH / 2) + i * BRICK_WIDTH;
				GRect X = new GRect(startX, startY, BRICK_WIDTH, BRICK_HEIGHT);
				add(X);
			}
		}
	}
}
