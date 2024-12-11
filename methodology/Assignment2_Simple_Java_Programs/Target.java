
/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {

	/* circles' radius parametres */
	private static final int R1 = 72;
	private static final double R2 = 1.65 * 72 / 2.54;
	private static final double R3 = 0.76 * 72 / 2.54;

	public void run() {

		/* drawing circles */
		drawFirst();
		drawSecond();
		drawThird();

	}

	/* drawing first circle */
	private void drawFirst() {
		int HEIGHT = getHeight();
		int WIDTH = getWidth();
		int startX = WIDTH / 2 - R1;
		int startY = HEIGHT / 2 - R1;
		GOval X = new GOval(startX, startY, 2 * R1, 2 * R1);
		X.setFilled(true);
		X.setColor(Color.RED);
		add(X);
	}

	/* drawing second circle */
	private void drawSecond() {
		int HEIGHT = getHeight();
		int WIDTH = getWidth();
		double startX = WIDTH / 2 - R2;
		double startY = HEIGHT / 2 - R2;
		GOval X = new GOval(startX, startY, 2 * R2, 2 * R2);
		X.setFilled(true);
		X.setColor(Color.WHITE);
		add(X);
	}

	/* drawing third circle */
	private void drawThird() {
		int HEIGHT = getHeight();
		int WIDTH = getWidth();
		double startX = WIDTH / 2 - R3;
		double startY = HEIGHT / 2 - R3;
		GOval X = new GOval(startX, startY, 2 * R3, 2 * R3);
		X.setFilled(true);
		X.setColor(Color.RED);
		add(X);
	}
}
