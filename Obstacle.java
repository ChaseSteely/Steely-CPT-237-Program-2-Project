//FILE: Obstacle.java
//PROG: Marshall Chase Steely
//PURP: Creates a Obstacle class that implements GameEntity.

package edu.tridenttech.CPT237.Steely.Program2;

public class Obstacle implements GameEntity {
	protected String name;
	protected int strength;
	protected int points;
	protected int health = 50;

	protected Obstacle(String name, int strength) {
		this(name, strength, 2 * strength);
	}

	protected Obstacle(String name, int strength, int points) {
		this.name = name;
		this.strength = strength;
		this.points = points;
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
}