//FILE: Well.java
//PROG: Marshall Chase Steely
//PURP: Creates a Collector that stashes the vials Player captures..

package edu.tridenttech.CPT237.Steely.Program2;

public class Well implements Collector {

	protected String name;
	protected int strength;
	protected int points;
	protected int health;
	protected int speed;
	protected int numVials;

	public Well(int numVials) {
		this.name = "Well";
		this.numVials = numVials;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public int getStrength() {
		// TODO Auto-generated method stub
		return strength;
	}

	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

	public int getPoints() {
		// TODO Auto-generated method stub
		return points;
	}

	public void reduceHealth(int health) {
		this.health -= health;

	}

	public boolean isAlive() {

		return this.health > 0;
	}

	public int getNumVials() {
		// TODO Auto-generated method stub
		return numVials;
	}

	public void addVials(int numVials) {
		this.numVials += numVials;

	}

	public int relenquishVials() {
		int v = this.numVials;
		this.numVials = 0;
		return v;
	}

}
