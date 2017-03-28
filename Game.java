//FILE: Game.java
//PROG: Marshall Chase Steely
//PURP: Runs the game play.

package edu.tridenttech.CPT237.Steely.Program2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	final public int ATTACK_LOSS_FACTOR = 5;
	final public int DEFEND_LOSS_FACTOR = 6;
	final public int MAX_STRENGTH = 10;
	final public int DEFAULT_STRENGTH = 6;
	final public int MAX_ABILITIES = 15;

	private Well well;

	Scanner console = new Scanner(System.in);
	Random rand = new Random();
	static private Game instance = new Game();
	Scanner input;

	ArrayList<Obstacle> opponents = new ArrayList<>();

	private Game() {
	}// Empty Constructor

	public static Game getInstance() {
		return instance;
	}// END getInstance

	public void loadChallenges(String filename) throws FileNotFoundException {

		String line;
		String[] fields;
		int numVials;
		int strength;
		int speed;
		int points;
		String name;
		int lineNum = 0;
		input = new Scanner(new File(filename));
		while (input.hasNext()) {
			lineNum++;//counter for line error
			line = input.nextLine();
			fields = line.split(",");
			name = fields[0];
			switch (name) {
			case "Troll": {
				try {

					numVials = Integer.parseInt(fields[1]);
					opponents.add(new Troll(numVials));

				} catch (Exception e) {
					System.err.println("Invalid: Line Error on Line #" + lineNum);
				}
			}
				break;// END Case Troll

			case "Jinn": {
				try {
					numVials = Integer.parseInt(fields[1]);

					opponents.add(new Jinn(numVials));
				} catch (Exception e) {
					System.err.println("Invalid: Line Error on Line #" + lineNum);
				}
			}
				break;// END case Jinn

			case "Goblin": {

				try {
					numVials = Integer.parseInt(fields[1]);

					opponents.add(new Goblin(numVials));
				} catch (Exception e) {
					System.err.println("Invalid: Line Error on Line #" + lineNum);
				}
			}
				break;// END case Goblin

			case "Wall": {

				try {
					opponents.add(new Wall());
				} catch (Exception e) {
					System.err.println("Invalid: Line Error on Line #" + lineNum);
				}
			}
				break;// END Wall case

			case "Well":
				try {
					numVials = Integer.parseInt(fields[1]);
					well = new Well(numVials);

				} catch (Exception e) {
					System.err.println("Invalid: Line Error on Line #" + lineNum);
				}
				break;

			default:
				try {
					strength = Integer.parseInt(fields[2]);
					speed = Integer.parseInt(fields[3]);
					points = Integer.parseInt(fields[4]);
					numVials = Integer.parseInt(fields[1]);
					opponents.add(new Enemy(name, strength, speed, numVials, points));
				} catch (Exception e) {
					System.err.println("Invalid: Line Error on Line #" + lineNum);
				}
			}// END Switch

		} // END WHile
		input.close();
	}// END loadChallenges

	public void play() {
		Player player;
		Player player2;
		String name;
		int strength;
		Obstacle badGuy = null;

		// have we loaded the opponents?
		if (opponents.size() == 0) {
			System.err.println("No opponents have been loaded");
			return;
		}

		System.out.print("Please enter a name for your player. ");
		name = console.nextLine();
		System.out.printf("Please enter the strength for %s.[1-10] ", name);
		strength = console.nextInt();
		console.nextLine();

		if (strength > MAX_STRENGTH || strength < 1) {
			System.err.printf("invalid strength, setting to default(%d)%n", DEFAULT_STRENGTH);
			strength = DEFAULT_STRENGTH;
		}

		player = new Player(name, strength, MAX_ABILITIES - strength);

		System.out.print("Please enter a name for your player 2. ");
		name = console.nextLine();
		System.out.printf("Please enter the strength for %s.[1-10] ", name);
		strength = console.nextInt();
		console.nextLine();

		if (strength > MAX_STRENGTH || strength < 1) {
			System.err.printf("invalid strength, setting to default(%d)%n", DEFAULT_STRENGTH);
			strength = DEFAULT_STRENGTH;
		}
		player2 = new Player(name, strength, MAX_ABILITIES - strength);

		int count = 0;
		Player plyr = null;
		// while loop to play game
		while (opponents.size() > 0) {
			count++;
			int turn = count % 2;//uses remainders to take turns
			if (turn == 0) {
				plyr = player2;
			} else {
				plyr = player;
			}
			int selection;
			// get the opponent at the top of the list
			if (badGuy == null || !badGuy.isAlive()) {
				badGuy = opponents.remove(opponents.size() - 1);
			}

			// display information to the user
			show(plyr);
			show(badGuy);

			selection = getMenuSelection();
			switch (selection) {
			case 'R':
				int v = 0;//variable for vials to put in the well
				if (badGuy instanceof Enemy) {
					Enemy enemy = (Enemy) badGuy;
					v = enemy.getNumVials();
				}
				System.out.println("RETREAT!");
				retreat(plyr, badGuy);
				if (!badGuy.isAlive()) {
					// announce the defeat and award points
					System.out.printf("Congratulations!  You have defeated %s%n", badGuy.getName());

					well.addVials(v);
					show(well);

					plyr.addPoints(badGuy.getPoints());
				} else {
					// return the opponent to the list
					opponents.add(rand.nextInt(opponents.size() - 1), badGuy);
					badGuy = null;
				}
				break;
			case 'A':
				int w = 0;//variable for vials to put in the well
				if (badGuy instanceof Enemy) {
					Enemy enemy = (Enemy) badGuy;
					w = enemy.getNumVials();
				}
				System.out.println("ATTACK!");
				plyr.attack(badGuy);

				if (!badGuy.isAlive()) {
					System.out.printf("Congratulations!  You have defeated %s%n", badGuy.getName());
					well.addVials(w);
					show(well);
					plyr.addPoints(badGuy.getPoints());
				}
				break;
			case 'D':
				System.out.println("DRINK!");
				drink(plyr);
				break;
			case 'Q':
				System.out.println("QUIT!");
				return;
			}
			if (!plyr.isAlive()) {
				System.out.printf("Sorry, %s!  You have been killed %n", plyr.getName());
				return;
			}
		}
	}

	private int getMenuSelection() {
		char selection = 'x';

		System.out.println("What would you like to do?");
		System.out.println("(R)etreat");
		System.out.println("(A)ttack");
		System.out.println("(D)rink vial");
		System.out.println("(Q)uit");
		selection = console.next().charAt(0);
		return Character.toUpperCase(selection);
	}

	private void show(GameEntity o) {
		System.out.println("-----------------------------");
		System.out.printf("Name:     %10s%n", o.getName());
		System.out.printf("Strength: %10d%n", o.getStrength());
		System.out.printf("Health:   %10d%n", o.getHealth());
		System.out.printf("Points:   %10d%n", o.getPoints());
		if (o instanceof Collector) {
			System.out.printf("Vials:   %10d%n", ((Collector) o).getNumVials());
		}
	}

	// Note: may be called from player (attack) or enemy (retreat)
	public void attack(Warrior attacker, GameEntity defender) {
		GameEntity winner = null; //
		GameEntity loser = null;

		// determine losses based on random number
		// the plus one assures that some damage is always inflicted
		int defenderLoss = (rand.nextInt(attacker.getStrength()) + 1) * ATTACK_LOSS_FACTOR;

		// defender losses
		System.err.printf("Defender looses %d%n", defenderLoss);
		defender.reduceHealth(defenderLoss);

		// if defender is killed on first strike, attacker has no loss
		// otherwise, defender strikes back and attacker also loses health
		if (defender.isAlive()) {
			int attackerLoss = rand.nextInt(defender.getStrength()) * DEFEND_LOSS_FACTOR;
			System.err.printf("Attacker looses %d%n", attackerLoss);
			attacker.reduceHealth(attackerLoss);
			// if the attacker was killed, make the defender the winner
			if (!attacker.isAlive()) {
				winner = defender;
				loser = attacker;
			}
		} else {
			winner = attacker;
			loser = defender;
		}

		// If we have a winner (still alive) and they collects vials, move them
		// to the winner
		if (winner != null && winner.isAlive() && winner instanceof Collector && loser instanceof Collector)
			((Collector) winner).addVials(((Collector) loser).relenquishVials());
	}

	// called from player class
	public void retreat(Player player, GameEntity defender) {
		// can defender attack?
		if (defender instanceof Warrior) {
			Warrior pursuer = (Warrior) defender;
			// chase attacker
			// the player with the greater speed has an advantage, but doesn't
			// necessarily win
			if (player.getSpeed() * rand.nextDouble() < pursuer.getSpeed() * rand.nextDouble()) {
				pursuer.attack(player);
			}
		}
	}

	private void drink(Player player) {
		player.drink();
	}

}
