//FILE: GameEntity.java
//PROG: Marshall Chase Steely
//PURP: Creates a the interface GameEntity.

package edu.tridenttech.CPT237.Steely.Program2;

public interface GameEntity {

	String getName();

	int getStrength();

	int getHealth();

	int getPoints();

	void reduceHealth(int health);

	boolean isAlive();

}// END GameEntity
