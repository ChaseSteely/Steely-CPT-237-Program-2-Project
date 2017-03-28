//FILE: Warrior.java
//PROG: Marshall Chase Steely
//PURP: Creates an Interface named Warrior.

package edu.tridenttech.CPT237.Steely.Program2;

public interface Warrior extends GameEntity {
	
	int getSpeed();
	boolean canAttack();
	void attack(GameEntity gameentity);
	
}
