
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}
	//this method runs the game
	private void playGame() {
		int count = 0;
		usedChecker = new int[nPlayers][N_CATEGORIES];
		totalCounts = new int[nPlayers + 1][N_CATEGORIES];
		while (count < N_SCORING_CATEGORIES) {
			turn(usedChecker, totalCounts);
			count++;
		}
		totalCounter();
	}

	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	int[] dices = new int[N_DICE];
	//this massive is used to check if the score is already written in the category
	int[][] usedChecker;
	//this massive is used to count total scores
	int[][] totalCounts;
	//variable for upper score
	int UPPER;
	//variable for lower score
	int LOWER;
	//variables for bonus points
	int BONUS = 35;
	int BONUS_LIMIT = 63;
	//variables for winner score and winner player
	int tot;
	int winner;

	//this method rolls the dices 3 times and shows it
	private void roll(int playerN) {
		for (int i = 0; i < N_DICE; i++) {
			int n = rgen.nextInt(1, 6);
			dices[i] = n;
		}
		display.waitForPlayerToClickRoll(playerN);
		display.displayDice(dices);
		int n = 0;
		while (n < 2) {
			display.printMessage("Select the dice you wish to re-roll and click 'Roll Again'.");
			display.waitForPlayerToSelectDice();
			for (int j = 0; j < N_DICE; j++) {
				if (display.isDieSelected(j) == true) {
					int m = rgen.nextInt(1, 6);
					dices[j] = m;
				}
			}
			display.displayDice(dices);
			n++;
		}
	}

	//this method checks if combination of dices satisfays the category
	private boolean check(int[] n, int c) {
		if ((c >= ONES && c <= SIXES) || c == CHANCE) {
			return true;
		}
		if (c == THREE_OF_A_KIND) {
			for (int i = 0; i < N_DICE; i++) {
				for (int k = 0; k < N_DICE; k++) {
					for (int l = 0; l < N_DICE; l++) {
						if (n[i] == n[k] && n[k] == n[l] && i != k && k != l && l != i) {
							return true;
						}
					}
				}
			}
		}
		if (c == FOUR_OF_A_KIND) {
			for (int i = 0; i < N_DICE; i++) {
				for (int k = 0; k < N_DICE; k++) {
					for (int l = 0; l < N_DICE; l++) {
						for (int g = 0; g < N_DICE; g++) {
							if (n[i] == n[k] && n[k] == n[l] && n[l] == n[g] && i != k && i != l && i != g && k != l
									&& k != g && l != g) {
								return true;
							}
						}
					}
				}
			}
		}
		if (c == FULL_HOUSE) {
			for (int i = 0; i < N_DICE; i++) {
				for (int k = 0; k < N_DICE; k++) {
					for (int l = 0; l < N_DICE; l++) {
						if (n[i] == n[k] && n[k] == n[l] && i != k && k != l && l != i) {
							for (int h = 0; h < N_DICE; h++) {
								for (int g = 0; g < N_DICE; g++) {
									if (n[h] == n[g] && i != k && i != l && i != h && i != g && k != l && k != h
											&& k != g && l != h && l != g && h != g) {
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		if (c == SMALL_STRAIGHT) {
			for (int i = 0; i < N_DICE; i++) {
				for (int k = 0; k < N_DICE; k++) {
					for (int l = 0; l < N_DICE; l++) {
						for (int g = 0; g < N_DICE; g++) {
							if (n[i] + 3 == n[k] + 2 && n[k] + 2 == n[l] + 1 && n[l] + 1 == n[g]) {
								return true;
							}
						}
					}
				}
			}
		}
		if (c == LARGE_STRAIGHT) {
			for (int i = 0; i < N_DICE; i++) {
				for (int k = 0; k < N_DICE; k++) {
					for (int l = 0; l < N_DICE; l++) {
						for (int g = 0; g < N_DICE; g++) {
							for (int j = 0; j < N_DICE; j++) {
								if (n[j] + 4 == n[i] + 3 && n[i] + 3 == n[k] + 2 && n[k] + 2 == n[l] + 1
										&& n[l] + 1 == n[g]) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		if (c == YAHTZEE) {
			for (int i = 0; i < N_DICE; i++) {
				for (int k = 0; k < N_DICE; k++) {
					for (int l = 0; l < N_DICE; l++) {
						for (int g = 0; g < N_DICE; g++) {
							for (int j = 0; j < N_DICE; j++) {
								if (n[i] == n[k] && n[k] == n[l] && n[l] == n[g] && n[g] == n[j] && i != k && i != l
										&& i != g && i != j && k != l && k != g && k != j && l != g && l != j
										&& g != j) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	//this method counts scores for each player and for each category and stores them
	private int countScore(int[] n, int c, int k, int[][] M) {
		int m = 0;
		if (c >= ONES && c <= SIXES) {
			for (int i = 0; i < N_DICE; i++) {
				if (n[i] == c) {
					m = m + n[i];
				}
			}
			M[k - 1][c - 1] = m;
		} else if (c == THREE_OF_A_KIND) {
			if (check(n, c) == true) {
				for (int h = 0; h < N_DICE; h++) {
					m = m + n[h];
				}
			}
			M[k - 1][c - 1] = m;
		} else if (c == FOUR_OF_A_KIND) {
			if (check(n, c) == true) {
				for (int h = 0; h < N_DICE; h++) {
					m = m + n[h];
				}
			}
			M[k - 1][c - 1] = m;
		} else if (c == FULL_HOUSE) {
			if (check(n, c) == true) {
				m = 25;
			}
			M[k - 1][c - 1] = m;
		} else if (c == CHANCE) {
			for (int i = 0; i < N_DICE; i++) {
				m = m + n[i];
			}
			M[k - 1][c - 1] = m;
		} else if (c == SMALL_STRAIGHT) {
			if (check(n, c) == true) {
				m = 30;
			}
			M[k - 1][c - 1] = m;
		} else if (c == LARGE_STRAIGHT) {
			if (check(n, c) == true) {
				m = 40;
			}
			M[k - 1][c - 1] = m;
		} else if (c == YAHTZEE) {
			if (check(n, c) == true) {
				m = 50;
			}
			M[k - 1][c - 1] = m;
		}
		return m;
	}

	//this method is responsible for the one turn
	private void turn(int[][] usedChecker, int[][] M) {
		int playerN = 1;
		display.printMessage(playerNames[playerN - 1] + "'s turn! Click 'Roll Dice' button to roll the dice");
		roll(playerN);
		while (playerN < nPlayers + 1) {
			display.printMessage("Select a category for this roll.");
			int category = display.waitForPlayerToSelectCategory();
			if (usedChecker[playerN - 1][category - 1] == 0) {
				int score = countScore(dices, category, playerN, M);
				usedChecker[playerN - 1][category - 1] = 1;
				display.updateScorecard(category, playerN, score);
				playerN++;
				if (playerN != nPlayers + 1) {
					display.printMessage(
							playerNames[playerN - 1] + "'s turn! Click 'Roll Dice' button to roll the dice");
					roll(playerN);
				}
			}
		}
	}

	//this method counts total scores and bonuses and shows them
	private void totalCounter() {
		UPPER = 0;
		LOWER = 0;
		tot = 0;
		for (int i = 0; i < nPlayers; i++) {
			for (int j = 0; j < SIXES; j++) {
				UPPER = UPPER + totalCounts[i][j];
			}
			for (int l = 8; l < CHANCE; l++) {
				LOWER = LOWER + totalCounts[i][l];
			}
			display.updateScorecard(UPPER_SCORE, i + 1, UPPER);
			display.updateScorecard(LOWER_SCORE, i + 1, LOWER);
			if (UPPER > BONUS_LIMIT) {
				display.updateScorecard(UPPER_BONUS, i + 1, BONUS);
				display.updateScorecard(TOTAL, i + 1, BONUS + UPPER + LOWER);
				totalCounts[i][TOTAL - 1] = BONUS + UPPER + LOWER;
			} else {
				display.updateScorecard(UPPER_BONUS, i + 1, 0);
				display.updateScorecard(TOTAL, i + 1, UPPER + LOWER);
				totalCounts[i][TOTAL - 1] = UPPER + LOWER;
			}
			if (totalCounts[i][TOTAL - 1] > tot) {
				tot = totalCounts[i][TOTAL - 1];
				winner = i;
			}
			UPPER = 0;
			LOWER = 0;
		}
		display.printMessage(
				"Congratulations, " + playerNames[winner] + " , you’re the winner with a total score of " + tot + "!");
	}
}
