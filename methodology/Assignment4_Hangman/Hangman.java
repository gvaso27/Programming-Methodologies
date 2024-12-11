/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	//random generator
	private RandomGenerator rgen = RandomGenerator.getInstance();
	//import HangmanLexicon class
	private HangmanLexicon List = new HangmanLexicon();
	
	//number of tries left
	private int tries = 8;
	//creating variables
	private String s;
	private char ch;
	private int n;
	private String Word = getWord();
	private HangmanCanvas canvas;
	private String Sword = LetterCount();

	// import HangmanCanvas class
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

	public void run() {
		prepareGame();
		play();
	}

	//play is responsible for the game's process
	private void play() {
		while (true) {
			s = readLine();
			ch = s.charAt(0);
			n = (int) ch;
			println("Your guess: " + ch);
			//if the charachter is Lower case transform make it Upper case
			if (Character.isLowerCase(ch)) {
				ch = Character.toUpperCase(ch);
				s = "" + ch;
			}
			if (s.length() != 1 || n < 65 || n > 122 || (n > 90 && n < 97)) {
				println("wrong charachter(s) \ntry again");
			}
			check();
			if (tries == 0) {
				println("You're completely hung. \nThe word was: " + Word + "\nYou lose.");
				break;
			}
			if (Word.equals(Sword)) {
				println("you win");
			}
		}
	}
	//prepareGame to start playing
	private void prepareGame() {
		canvas.reset();
		println("Welcome to Hangman!");
		println("The word now looks like this: " + Sword);
		println("You have " + tries + " guesses left.");
	}
	//getWord() gives us the word to guess
	private String getWord() {
		int n = rgen.nextInt(0, List.getWordCount() - 1);
		String word = List.getWord(n);
		return word;
	}

	// this method gives us the number of letters in the secret word
	private String LetterCount() {
		String S = "";
		for (int i = 0; i < Word.length(); i++) {
			S = S + "-";
		}
		return S;
	}

	// this method checks given charachters and makes proper actions
	private void check() {
		if (Sword.contains(s)){
			println("Charachter already used.");
		}else
		if (Word.contains(s)) {
			println("The guess is correct.");
			for (int i = 0; i < Word.length(); i++) {
				if (ch == Word.charAt(i)) {
					if (i > 0) {
						Sword = Sword.substring(0, i) + ch + Sword.substring(i + 1);
					}
					if (i == 0) {
						Sword = ch + Sword.substring(1);
					}
				}
			}

		} else {
			println("There are no " + ch + "'s in the word.");
			tries--;
			canvas.noteIncorrectGuess(tries);
		}
		println("The word now looks like this: "+ Sword);
		println("You have " + tries + " guesses left.");
		canvas.displayWord(Sword);
	}
}