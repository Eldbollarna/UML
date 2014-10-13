package model;

public class Boat 
{
	private BoatType boatType;			//What type of boat 
	private int lengthCm;				//What is the boat length in cm
	public int boatID;
	
	public enum BoatType{
		Rowboat,
		Sailboat,
		Motorsailer,
		Canoe,
		Other							//Boats that does not fit into other types
	}
	
	public Boat(BoatType boatType, int lengthCm){
		this.setBoatType(boatType);
		this.setLengthCm(lengthCm);
	}
	
	public String boatTypeToString(){
		String boatType = this.boatType + "";
		return boatType;
	}

	public int getLengthCm() {
		return lengthCm;
	}

	public void setLengthCm(int lengthCm) {
		this.lengthCm = lengthCm;
	}

	public BoatType getBoatType() {
		return boatType;
	}

	public void setBoatType(BoatType boatType) {
		this.boatType = boatType;
	}	
	
	public int getBoatID() {
		return boatID;
	}
	
	public void setBoatID(int boatID) {
		this.boatID = boatID;
	}
}
