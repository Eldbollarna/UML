package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Registry 
{
	private String registryPath = "src\\Registry";		//Path to the registry folder where next uniqueID and members are stored
	private static int uniqueID;									//The last used uniqueID				
	private List<Member> members;									//Contains all members currently in the registry
	private Scanner scan;											//Used to get all member and boat information from the .txt files
	private PrintWriter writer;
	
	/* Load all members and boats from the member .txt's into the registry*/
	public Registry(){
		members = new ArrayList<Member>();
		initializeUniqueID();
		initializeMembers();
		
	}
	/* A Boat is created with the Boat type that match the string the client typed*/
	public void generateBoatTypeForAdd(String boat, int length, int uniqueID) {
		Boat b;
		switch(boat){
		case("Rowboat"):
			b = new Boat(Boat.BoatType.Rowboat, length);
			addBoat(b, uniqueID);
			break;
		case("Sailboat"):
			b = new Boat(Boat.BoatType.Sailboat, length);
			addBoat(b, uniqueID);
			break;
		case("Motorsailer"):
			b = new Boat(Boat.BoatType.Motorsailer, length);
			addBoat(b, uniqueID);
			break;
		case("Canoe"):
			b = new Boat(Boat.BoatType.Canoe, length);
			addBoat(b, uniqueID);
			break;
		case("Other"):
			b = new Boat(Boat.BoatType.Other, length);
			addBoat(b, uniqueID);
			break;
		}
	}
	
	
	/* Add boat to a member */
	public void addBoat(Boat b, int uniqueID){
		
		for(Member m : members){
			if(m.getUniqueID() == uniqueID){
				m.addBoat(b);
				saveMemberToFile(m);
				break;
			}
		}
	}
	
	/* Remove boat from a member */
	public void removeBoat(int boatID, int uniqueID){
		
		for(Member m : members){
			if(m.getUniqueID() == uniqueID){
				Member member = m;
				for(Boat b : member.getBoatList()) {
					if(b.getBoatID() == boatID) {
						member.removeBoat(b);
						deleteBoatFromFile(m);
						saveMemberToFile(m);
						break;
					}
				}
			}
		}
	}
	/*Delete Boat from member*/
	public void deleteBoatFromFile(Member m) {
		File file = new File(registryPath + "\\" + m.getName() + ".txt");
		if(file.exists()) {
			try {
				writer = new PrintWriter(new FileWriter(file, true));
				writeToFile(file, m);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/* Create and save a new member */
	public void addMember(String name, int personalNr){
		
		/* Create the member object */
		Member m = new Member(name, personalNr);
		
		/* Update member list in registry */
		members.add(m);
		updateUniqueIDFile();
		/* Save to .txt file */
		saveMemberToFile(m);
		
	}
	/*Write information to Member file */
	public void writeToFile(File file, Member m) {
		try {
			writer = new PrintWriter(file);
		
			writer.println(m.getName());
			writer.flush();
			writer.println(m.getPersonalNr());
			writer.flush();
			writer.println(m.getUniqueID());
			writer.flush();
		
			for(Boat b : m.getBoatList()){
				writer.println("-Boat-");
				writer.flush();
				writer.println(b.getBoatID());
				writer.flush();
				writer.println(b.boatTypeToString());
				writer.flush();
				writer.println(b.getLengthCm());
				writer.flush();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace(); }
	}
	
	/* Save member to a .txt file containing member info as well as boat info*/ 
	public void saveMemberToFile(Member m){
		
		try {
			/* Save to membername.txt */
			File file = new File(registryPath + "\\" + m.getUniqueID() + ".txt");
			if(file.exists()) {
				writer = new PrintWriter(new FileWriter(file, true));
				file.delete();
				writeToFile(file, m);
			} else { 
				writeToFile(file, m);
				updateUniqueIDFile();
			}
			writer.close();
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/* Increment the uniqueID.txt value by 1*/
	public void updateUniqueIDFile(){
		
			try {
				File f = new File(registryPath + "\\UniqueID.txt");
				writer = new PrintWriter(new FileWriter(f, true));
				f.delete();
				writer = new PrintWriter(f);
				writer.println(uniqueID);
				writer.flush();
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/* Remove a member with a specific unique ID.*/
	public void removeMember(int uniqueID){
		for(int i = 0; i < members.size(); i++){
			if(members.get(i).getUniqueID() == uniqueID){
	    		try{
	    			String file = (registryPath + "\\" +  members.get(i).getUniqueID() + ".txt");
	    			File f = new File(file);
	    			
	    			if(f.delete())
	    				members.remove(i);
	    			
	    		} catch(Exception e){
	        		e.printStackTrace();
	        	}
			}
		}
	}
	
	/* Loads the last used uniqueID */ 
	public void initializeUniqueID(){
		try {
			scan = new Scanner(new File(registryPath + "\\UniqueID.txt"));	
			uniqueID = scan.nextInt();								//Set uniqueID to the number stored in uniqueID
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("UniqueID.txt was not found.");
		}
	}
	
	/* Load all members and boats from .txt files */
	public void initializeMembers(){
		try {
			File file = new File(registryPath);
			if(file.isDirectory()){
				for(File f : file.listFiles()) {
					if(f.getName().contains("UniqueID.txt"))
						break;
					scan = new Scanner(f);
					String name = scan.nextLine();
					int personalNr = scan.nextInt();
					int uniqueID = scan.nextInt();
					
					Member m = new Member(name, personalNr, uniqueID);
					 
					while(scan.hasNext()){
						String line = scan.nextLine();
						if(line.equals("-Boat-")){
							int boatID = scan.nextInt();
							scan.nextLine();
							String boatString = scan.nextLine(); //Read the line containing boat type
							int length = scan.nextInt();		 //Read the line containing length
							Boat b;
							
							switch (boatString) { 				 //Set the correct boat type
							case "Rowboat":
								b = new Boat(Boat.BoatType.Rowboat, length);
								break;
							case "Sailboat":
								b = new Boat(Boat.BoatType.Sailboat, length);
								break;
							case "Motorsailer":
								b = new Boat(Boat.BoatType.Motorsailer, length);
								break;
							case "Canoe":
								b = new Boat(Boat.BoatType.Canoe, length);
								break;
								
							
							default:
								b = new Boat(Boat.BoatType.Other, length);
								break;
							}
							b.setBoatID(boatID);
							m.addBoat(b);
						}
						
					}
					members.add(m);	//Member is finished and can be added to member registry
					scan.close();
				}
			}
				
			else
				System.out.println("registryPath is not a directory.");
			
		} catch (FileNotFoundException e) {
			System.out.println("Directory not found. Make sure the registryPath is correct.");
		} catch (NoSuchElementException ne){
			ne.printStackTrace();
		}
	}
	
	/* Update existing member*/
	public void updateMember(String name, int personalNr, int uniqueID) {
		for(int i = 0; i < members.size(); i++){
			if(members.get(i).getUniqueID() == uniqueID){
				Member updatedMember = members.get(i);
				removeMember(uniqueID);
				
				updatedMember.setName(name);
				updatedMember.setPersonalNr(personalNr);
				members.add(updatedMember);
				saveMemberToFile(updatedMember);
			}
		}
		
	}
	
	/* Return a new unique ID */
	public static int getUniqueID() {
		uniqueID++;
		return uniqueID;
	}

	/* Return a list of all members in the registry */
	public List<Member> getMemberList(){
		return this.members;
	}
}
