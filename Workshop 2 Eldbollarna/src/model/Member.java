package model;

import java.util.ArrayList;
import java.util.List;

public class Member {
	
	private String name;		//The members name
	private int personalNr;		//Personal Identification for the member
	private int uniqueID;		//Unique ID for the member
	private List<Boat> boats;
	private int boatID = 1;
	
	/* Create a new member as well as generating and assigning an unique ID to the member */
	public Member(String name, int personalNr)
	{
		this.name = name;
		this.personalNr = personalNr;
		this.uniqueID = Registry.getUniqueID();
		boats = new ArrayList<Boat>();
	}
	
	/* Used to recreate a member from .txt file*/
	public Member(String name, int personalNr, int uniqueID)
	{
		this.name = name;
		this.personalNr = personalNr;
		this.uniqueID = uniqueID;
		boats = new ArrayList<Boat>();
	}
	
	/* Add boat to the members boat list */
	public void addBoat(Boat boat){
		boat.setBoatID(boatID++);
		boat.getBoatID();
		boats.add(boat);
	}
	
	/* Remove boat from the members boat list */
	public void removeBoat(Boat boat){
		boats.remove(boat);
	}

	public String getName() {
		return name;
	}
	
	public List<Boat> getBoatList(){
		return boats;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPersonalNr() {
		return personalNr;
	}

	public void setPersonalNr(int personalNr) {
		this.personalNr = personalNr;
	}

	public int getUniqueID() {
		return uniqueID;
	}

}
