//FILE: Collector.java
//PROG: Marshall Chase Steely
//PURP: Creates a GameEnity Called Collector interface

package edu.tridenttech.CPT237.Steely.Program2;

public interface Collector extends GameEntity {

	int getNumVials();

	void addVials(int numVials);

	int relenquishVials();

}
