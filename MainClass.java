//FILE: MainClass.java
//PROG: Marshall Chase Steely
//PURP: Creates main class to run the game and load opponents file.

package edu.tridenttech.CPT237.Steely.Program2;

import java.io.FileNotFoundException;

public class MainClass {

	public static void main(String[] args) throws FileNotFoundException {

		Game game = Game.getInstance();
		game.loadChallenges("C:/Users/Marshall/Desktop/opponents.csv");
		game.play();
	}

}
