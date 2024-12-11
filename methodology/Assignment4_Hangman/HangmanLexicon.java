
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class HangmanLexicon {
	//we store words in list variable
	private ArrayList <String> list = new ArrayList <String> ();
	public HangmanLexicon(){

		try {
			BufferedReader read = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				String word = read.readLine();
				if (word != null) {
					list.add(word);
				}
				if (word == null) {
					break;
				}
			}
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Returns the word at the specified index
	public String getWord(int index) {
		return list.get(index);
	}
	//Returns the number of words in the lexicon
	public int getWordCount() {
		return list.size();
	}	
}
