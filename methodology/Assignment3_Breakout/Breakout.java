
/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private int NTURNS = 3;

	/* DELAY is the speed of the ball */
	private int DELAY = 10;
	/* we use RandomGenerator to randomize the start of the game */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	/* vx and vy are how the cordinates of the ball change in time */
	private double vx;
	private double vy;

	/* creating variables for paddle, bricks and ball */
	private GRect paddle;
	private GRect bricks;
	private GOval ball;

	/* NBRICK is the ammount of bricks */
	private int NBRICK = NBRICK_ROWS * NBRICKS_PER_ROW;

	/* Method: run() */
	/** Runs the Breakout program. */

	public void init() {
		/* adding mouse listeners to use mouse events */
		addMouseListeners();
	}

	public void run() {
		this.setSize(APPLICATION_WIDTH, getHeight());
		visual();
		process();
	}

	/* visual is responsible for the application's display and graphics */
	private void visual() {
		drawBricks();
		drawPaddle();
		drawBall();
	}

	/* process is responsible for the game's process */
	private void process() {
		ballDirection();
		/* you have to click mouse to start the game */
		waitForClick();
		while (true) {
			moveBall();
			if (ball.getY() >= getHeight() - BALL_RADIUS * 2) {
				ball.setLocation(WIDTH / 2 - BALL_RADIUS, getHeight() / 2 - BALL_RADIUS);
				/* you have to click mouse to start the next turn */
				waitForClick();
				NTURNS--;
			}
			/*
			 * if ball touches the bottom line three times we will lose and the game will
			 * end
			 */
			if (NTURNS == 0) {
				break;
			}
			/* if all the bricks were destroied the game will end and we will win */
			if (NBRICK == 0) {
				break;
			}

		}
	}

	/* drawBricks is responsible for adding bricks */
	private void drawBricks() {
		for (int i = 0; i < NBRICK_ROWS; i++) {
			int X = BRICK_SEP / 2 + i * (BRICK_WIDTH + BRICK_SEP);
			for (int j = 0; j < NBRICK_ROWS / 5; j++) {
				for (int l = 0; l < NBRICK_ROWS / 2; l++) {
					int Y = l * 2 * (BRICK_HEIGHT + BRICK_SEP) + BRICK_Y_OFFSET + j * (BRICK_SEP + BRICK_HEIGHT);
					bricks = new GRect(X, Y, BRICK_WIDTH, BRICK_HEIGHT);
					bricks.setFilled(true);
					if (l == 0) {
						bricks.setColor(Color.RED);
						add(bricks);
					}
					if (l == 1) {
						bricks.setColor(Color.ORANGE);
						add(bricks);
					}
					if (l == 2) {
						bricks.setColor(Color.YELLOW);
						add(bricks);
					}
					if (l == 3) {
						bricks.setColor(Color.GREEN);
						add(bricks);
					}
					if (l == 4) {
						bricks.setColor(Color.CYAN);
						add(bricks);
					}
				}
			}
		}
	}

	/* drawing paddle */
	private void drawPaddle() {
		int X = (WIDTH - PADDLE_WIDTH) / 2;
		int Y = getHeight() - PADDLE_Y_OFFSET;
		paddle = new GRect(X, Y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}

	/* paddle movement */
	public void mouseMoved(MouseEvent e) {
		if (e.getX() >= PADDLE_WIDTH / 2 && e.getX() + PADDLE_WIDTH / 2 <= WIDTH) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_OFFSET);
		}
	}

	/* drawing ball */
	private void drawBall() {
		int X = WIDTH / 2 - BALL_RADIUS;
		int Y = getHeight() / 2 - BALL_RADIUS;
		ball = new GOval(X, Y, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
	}

	/* giving the ball it's direction */
	private void ballDirection() {
		vx = rgen.nextDouble(1.0, 3.0);
		vy = +3.0;
		if (rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
	}

	/* ball movement */
	private void moveBall() {
		ball.move(vx, vy);
		if (ball.getX() <= 0 || ball.getX() + BALL_RADIUS * 2 >= WIDTH) {
			vx = -vx;
		}
		if (ball.getY() <= 0) {
			vy = -vy;
		}
		/* collider is an object which was hit by the ball */
		GObject collider = getCollidingObject();
		/* if ball hits paddle it will be reflected */
		if (collider == paddle) {
			if (ball.getY() >= getHeight() - PADDLE_Y_OFFSET - BALL_RADIUS * 2
					&& ball.getY() <= getHeight() - PADDLE_Y_OFFSET - BALL_RADIUS * 2 + 2) {
				vy = -vy;
			}

			/* if ball hits brick remove brick */
		} else if (collider != null) {
			NBRICK--;
			remove(collider);
			vy = -vy;
		}
		pause(DELAY);
	}

	/* getCollidingObjects returns those objects which were touched by the ball */
	private GObject getCollidingObject() {
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			return getElementAt(ball.getX(), ball.getY());
		} else if (getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY()) != null) {
			return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY());
		} else if (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2) != null) {
			return getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2);
		} else if (getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2) != null) {
			return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2);
		} else {
			return null;
		}
	}
}