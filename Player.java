//FILE: Player.java
//PROG: Marshall Chase Steely
//PURP: Creates a Player able to interact with an Enemy.

package edu.tridenttech.CPT237.Steely.Program2;

public class Player implements Warrior, Collector {

	protected String name;
	protected int strength;
	protected int points;
	protected int health = 50;
	protected int speed;
	protected int numVials;

	public Player(String name, int strength, int speed) {
		this.name = name;
		this.strength = strength;
		this.speed = speed;
	}

	// DO NOT MODIFY
	public void run(GameEntity opponent) {
		// calls the retreat method in Game
		Game.getInstance().retreat(this, opponent);
	}

	// DO NOT MODIFY
	public void attack(GameEntity opponent) {
		// The player calls the 'attack' method in Game.
		Game.getInstance().attack(this, opponent);
	}

	public String getName() {

		return name;
	}

	public int getStrength() {
		return strength;

	}

	public int getHealth() {

		return health;
	}

	public int getPoints() {

		return points;
	}

	public void reduceHealth(int health) {

		this.health -= health;

	}

	public boolean isAlive() {
		return this.health > 0;

	}

	public int getNumVials() {

		return numVials;
	}

	public void addVials(int numVials) {

		this.numVials += numVials;
	}

	public int getSpeed() {

		return speed;
	}

	public boolean canAttack() {

		return this.isAlive();
	}

	public void addPoints(int points) {

		this.points += points;

	}

	public int relenquishVials() {

		int v = this.numVials;
		this.numVials = 0;
		return v;
	}

	public void drink() {

		this.health += 100;
		if (this.health >= 150)
			this.health = 150;
		this.numVials--;
	}
}