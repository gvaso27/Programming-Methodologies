
/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {

	/* rectangle parametres */
	private final int RECTL = 150;
	private final int RECTH = 50;

	/* texts in rectangles */
	private final String TEXT = "Program";
	private final String TEXT1 = "GraphicsProgram";
	private final String TEXT2 = "ConsoleProgram";
	private final String TEXT3 = "DialogProgram";

	public void run() {

		/* drawing rectangles */
		Rect();
		Rect1();
		Rect2();
		Rect3();

		/* drawing lines to connect rectangles */
		Lines();

	}

	/* drawing rectangle for "program" text and adding TEXT */
	private void Rect() {
		int HEIGHT = getHeight();
		int WIDTH = getWidth();
		int startX = (WIDTH - RECTL) / 2;
		int startY = HEIGHT / 2 - 2 * RECTH;
		GRect X = new GRect(startX, startY, RECTL, RECTH);
		add(X);
		GLabel bannerLabel = new GLabel(TEXT);
		int bannerX = (int) ((WIDTH - bannerLabel.getWidth()) / 2);
		int bannerY = (int) (HEIGHT / 2 - RECTH - ((RECTH - bannerLabel.getAscent()) / 2));
		add(bannerLabel, bannerX, bannerY);
	}

	/* drawing rectangle for "GraphicsProgram" text and adding TEXT1 */
	private void Rect1() {
		int HEIGHT = getHeight();
		int WIDTH = getWidth();
		int startX = (WIDTH - RECTL) / 2 - RECTL - RECTL / 6;
		int startY = HEIGHT / 2 + RECTH;
		GRect X = new GRect(startX, startY, RECTL, RECTH);
		add(X);
		GLabel bannerLabel = new GLabel(TEXT1);
		int bannerX = (int) ((WIDTH - RECTL) / 2 - RECTL / 6 - (RECTL + bannerLabel.getWidth()) / 2);
		int bannerY = (int) (HEIGHT / 2 + RECTH + (RECTH + bannerLabel.getAscent()) / 2);
		add(bannerLabel, bannerX, bannerY);
	}

	/* drawing rectangle for "ConsoleProgram" text and adding TEXT2 */
	private void Rect2() {
		int HEIGHT = getHeight();
		int WIDTH = getWidth();
		int startX = (WIDTH - RECTL) / 2;
		int startY = HEIGHT / 2 + RECTH;
		GRect X = new GRect(startX, startY, RECTL, RECTH);
		add(X);
		GLabel bannerLabel = new GLabel(TEXT2);
		int bannerX = (int) (WIDTH / 2 - bannerLabel.getWidth() / 2);
		int bannerY = (int) (HEIGHT / 2 + RECTH + (RECTH + bannerLabel.getAscent()) / 2);
		add(bannerLabel, bannerX, bannerY);
	}

	/* drawing rectangle for "DialogProgram" text and adding TEXT3 */
	private void Rect3() {
		int HEIGHT = getHeight();
		int WIDTH = getWidth();
		int startX = (WIDTH + RECTL) / 2 + RECTL / 6;
		int startY = HEIGHT / 2 + RECTH;
		GRect X = new GRect(startX, startY, RECTL, RECTH);
		add(X);
		GLabel bannerLabel = new GLabel(TEXT3);
		int bannerX = (int) ((WIDTH + RECTL) / 2 + RECTL / 6 + RECTL / 2 - bannerLabel.getWidth() / 2);
		int bannerY = (int) (HEIGHT / 2 + RECTH + (RECTH + bannerLabel.getAscent()) / 2);
		add(bannerLabel, bannerX, bannerY);
	}

	/* drawing lines */
	private void Lines() {
		int HEIGHT = getHeight();
		int WIDTH = getWidth();
		GLine First = new GLine(WIDTH / 2, HEIGHT / 2 - RECTH, WIDTH / 2, HEIGHT / 2 + RECTH);
		GLine Second = new GLine(WIDTH / 2, HEIGHT / 2 - RECTH, WIDTH / 2 - RECTL - RECTL / 6, HEIGHT / 2 + RECTH);
		GLine Third = new GLine(WIDTH / 2, HEIGHT / 2 - RECTH, WIDTH / 2 + RECTL + RECTL / 6, HEIGHT / 2 + RECTH);
		add(First);
		add(Second);
		add(Third);
	}
}
