/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/** Resets the display so that only the scaffold appears */
	public void reset() {
		int n = getHeight() - 90;
		GLine scaffold = new GLine(FOOT_LENGTH, n, FOOT_LENGTH, n - SCAFFOLD_HEIGHT);
		GLine beam = new GLine(FOOT_LENGTH, n - SCAFFOLD_HEIGHT, FOOT_LENGTH + BEAM_LENGTH, n - SCAFFOLD_HEIGHT);
		GLine rope = new GLine(FOOT_LENGTH + BEAM_LENGTH, n - SCAFFOLD_HEIGHT, FOOT_LENGTH + BEAM_LENGTH,
				n - SCAFFOLD_HEIGHT + ROPE_LENGTH);
		add(scaffold);
		add(beam);
		add(rope);
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		int n = getHeight() - 90;
		GLabel secretword = new GLabel(word, FOOT_LENGTH, n + 45);
		if (getElementAt(FOOT_LENGTH,n + 45) != null){
			remove(getElementAt(FOOT_LENGTH,n + 45));
		}
		secretword.setFont("-30");
		add(secretword);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user. Calling
	 * this method causes the next body part to appear on the scaffold and adds the
	 * letter to the list of incorrect guesses that appears at the bottom of the
	 * window.
	 */
	public void noteIncorrectGuess(int n) {
		if (n==7) {
			drawHead();
		}
		if (n==6) {
			drawBody();
		}
		if (n==5) {
			drawLeftHand();
		}
		if (n==4) {
			drawRightHand();
		}
		if (n==3) {
			drawLeftLeg();
		}
		if (n==2) {
			drawRightLeg();
		}
		if (n==1) {
			drawLeftFoot();
		}
		if (n==0) {
			drawRightFoot();
		}
	}
	//drawing body parts
	private void drawHead() {
		int n = getHeight() - 90;
		int x = FOOT_LENGTH + BEAM_LENGTH - HEAD_RADIUS;
		int y = n - SCAFFOLD_HEIGHT + ROPE_LENGTH;
		GOval head = new GOval(x, y, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		add(head);
	}
	private void drawBody() {
		int n = getHeight() - 90;
		int x = FOOT_LENGTH + BEAM_LENGTH;
		int y = n - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS * 2;
		GLine body = new GLine(x, y, x, y + BODY_LENGTH);
		add(body);
	}
	private void drawLeftHand() {
		int n = getHeight() - 90;
		int x = FOOT_LENGTH + BEAM_LENGTH;
		int y = n - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		GLine leftHand = new GLine(x, y, x - UPPER_ARM_LENGTH, y);
		GLine leftLow = new GLine(x - UPPER_ARM_LENGTH, y, x - UPPER_ARM_LENGTH, y + LOWER_ARM_LENGTH);
		add(leftHand);
		add(leftLow);
	}
	private void drawRightHand() {
		int n = getHeight() - 90;
		int x = FOOT_LENGTH + BEAM_LENGTH;	
		int y = n - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		GLine rightHand = new GLine(x, y, x + UPPER_ARM_LENGTH, y);
		GLine leftLow = new GLine(x + UPPER_ARM_LENGTH, y, x + UPPER_ARM_LENGTH, y + LOWER_ARM_LENGTH);
		add(rightHand);
		add(leftLow);
	}
	private void drawLeftLeg() {
		int n = getHeight() - 90;
		int x = FOOT_LENGTH + BEAM_LENGTH;
		int y = n - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
		GLine leftHip = new GLine(x, y, x - HIP_WIDTH, y);
		GLine leftLeg = new GLine(x - HIP_WIDTH, y, x - HIP_WIDTH, y + LEG_LENGTH);
		add(leftHip);
		add(leftLeg);
	}
	private void drawRightLeg() {
		int n = getHeight() - 90;
		int x = FOOT_LENGTH + BEAM_LENGTH;
		int y = n - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
		GLine rightHip = new GLine(x, y, x + HIP_WIDTH, y);
		GLine rightLeg = new GLine(x + HIP_WIDTH, y, x + HIP_WIDTH, y + LEG_LENGTH);
		add(rightHip);
		add(rightLeg);
	}
	private void drawLeftFoot() {
		int n = getHeight() - 90;
		int x = FOOT_LENGTH + BEAM_LENGTH - HIP_WIDTH;
		int y = n - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
		GLine leftFoot = new GLine(x, y, x - FOOT_LENGTH, y);
		add (leftFoot);
	}
	private void drawRightFoot() {
		int n = getHeight() - 90;
		int x = FOOT_LENGTH + BEAM_LENGTH + HIP_WIDTH;
		int y = n - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
		GLine rightFoot = new GLine(x, y, x + FOOT_LENGTH, y);
		add(rightFoot);
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
