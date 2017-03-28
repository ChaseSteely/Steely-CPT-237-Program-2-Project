//FILE: Enemy.java
//PROG: Marshall Chase Steely
//PURP: Creates a Enemy Class with 2 constructors.

package edu.tridenttech.CPT237.Steely.Program2;

public class Enemy extends Obstacle implements Warrior, Collector {
	protected int speed;
	protected int numVials;
	protected boolean canAttack = false;

	public Enemy(String name, int strength, int speed, int numVials) {
		super(name, strength);
		this.speed = speed;
		this.numVials = numVials;
	}// END Enemy

	public Enemy(String name, int strength, int speed, int numVials, int points) {
		super(name, strength, points);
		this.speed = speed;
		this.numVials = numVials;

	}// END Enemy

	// This implements the attack method for the Enemy. Do not modify.
	public void attack(GameEntity player) {
		Game.getInstance().attack(this, player);
	}// END attack

	public int getSpeed() {
		return speed;
	}// END getSpeed

	public int getNumVials() {
		return numVials;
	}// END getNumVials

	public void setNumVials(int numVials) {
		this.numVials = numVials;
	}// END setNumVials

	public void addVials(int numVials) {

		numVials = this.numVials + numVials;

	}// END addVials

	public boolean canAttack() {

		return this.isAlive();
	}// END canAttack

	public int relenquishVials() {

		int v = this.numVials;
		this.numVials = 0;
		return v;
	}// END relenquishVials

}
