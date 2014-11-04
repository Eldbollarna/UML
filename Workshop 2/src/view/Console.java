package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

import model.Member;
import model.Registry;
import model.Boat;

public class Console 
{
	
	static Registry register = new Registry();
	static Console console = new Console();
	static Scanner scanner = new Scanner(System.in);
	static String input;
	
	public static void main(String[] args) {
		System.out.print("Register Index"+"\n"+
		"To Add Member press A"+"\n"+
		"To Remove Member press R"+"\n"+
		"To View Member press V"+"\n"+
		"To Update a Member press U"+"\n"+
		"To display Compact List press C"+"\n"+
		"To display Verbose List press L"+"\n"+
		"To Register a new boat to a member press N"+"\n"+
		"To remove a Boat from a member press B"+"\n"+
		"To update a members boat press W"+"\n"+
		"To Exit program press X"+"\n");
		
		while(input != "") {
			input = scanner.nextLine();
			switch (input) { 				 
				case "A":
					console.addMember();
					break;
				case "R":
					console.removeMember();
					break;
				case "V":
					console.viewMember();
					break;
				case "U":
					console.updateMember();
					break;
				case "C":
					console.compactList(register.getMemberList());
					break;
				case "L":
					console.verboseList(register.getMemberList());
					break;
				case "N":
					console.addBoat();
					break;
				case "B":
					console.removeBoat();
					break;
				case "W":
					console.updateBoat();
					break;
				case "X":
					return;
				}
			}
		
		}
	
	public void compactList(List<Member> memberList){
		System.out.println("------------------Compact-List------------------");
		for(Member m : memberList)
			System.out.println("Name: " + m.getName() + "\nPersonal Number: " + m.getPersonalNr() + "\nUnique ID: " + m.getUniqueID() + "\n");
	}
	
	public void verboseList(List<Member> memberList){
		
		System.out.println("------------------Verbose-List------------------");
		for(Member m : memberList){
			System.out.println("Name: " + m.getName() + "\nPersonal Number: " + m.getPersonalNr() + "\nUnique ID: " + m.getUniqueID() + "");
			
			for(Boat b : m.getBoatList()){
				System.out.println("Boat type: " + b.boatTypeToString() + " Length: " + b.getLengthCm() + " cm" + " BoatID: "+ b.getBoatID());
			}
			System.out.println();
		}

	}
	
	public void addMember() {
		try{ 
			System.out.println("------------------Add-Member------------------");
			System.out.print("Name: ");
			String name = scanner.nextLine();
			System.out.print("PersonalNr: ");
			int personalNr = scanner.nextInt();
			register.addMember(name, personalNr);
		} catch(InputMismatchException e){
			System.out.println("Invalid input. Please try again.");
		}
	
	}
	public void removeMember() {
		try{
			System.out.println("-------------------Remove-Member------------------");
			System.out.print("Members UniqueID: ");
			int uniqueID = scanner.nextInt();
			register.removeMember(uniqueID);
		} catch(InputMismatchException e){
			System.out.println("Invalid input. Please try again.");
		}
	}
	
	public void viewMember() {
		try { 
			System.out.println("------------------View-Member------------------");
			System.out.print("Member UniqueID: ");
			int uniqueID = scanner.nextInt();
			for(Member m : register.getMemberList()){
				if(m.getUniqueID() == uniqueID){
					
					System.out.print("Name: "+m.getName()+"\n"+
							"PersonalNr: "+m.getPersonalNr()+"\n");
					for(Boat b : m.getBoatList()){
						System.out.println("Boat type: " + b.boatTypeToString() + " Length: " + b.getLengthCm() + " cm");	
					}
				}	
			}
		}catch(InputMismatchException e){
			System.out.println("Invalid input. Please try again.");
		}
	}
	public void updateMember() {
		try{
			System.out.println("------------------Update-Member------------------");
			System.out.print("Member UniqueID: ");
			int uniqueID = scanner.nextInt();
			System.out.print("New Name: ");
			String name = scanner.next();
			System.out.print("New PersonalNr: ");		
			int	personalNr = scanner.nextInt();
			register.updateMember(name, personalNr, uniqueID);}
		catch(InputMismatchException e){
			System.out.println("Invalid input. Please try again.");
		}
			
	}
	public void addBoat() {
		try{
			System.out.println("------------------Add-Boat------------------");
			System.out.println("Boattypes : Rowboat, Sailboat, Motorsailer, Canoe, Other");
			System.out.print("Boattype: ");
			String boat = scanner.nextLine();
			System.out.print("Length (cm): ");
			int length = scanner.nextInt();
			System.out.print("Member UnigueID: ");
			int uniqueID = scanner.nextInt();
			register.generateBoatTypeForAdd(boat, length, uniqueID);
		} catch(InputMismatchException e){
			System.out.println("Invalid input. Please try again.");
		}
	}
	public void removeBoat() {
		System.out.println("------------------Remove-Boat------------------");
		try{
			System.out.print("BoatID: ");
			int boatID = scanner.nextInt();
			System.out.print("Member UniqueID: ");
			int uniqueID = scanner.nextInt();
			register.removeBoat(boatID, uniqueID);
		} catch(InputMismatchException e){
			System.out.println("Invalid input. Please try again.");
		}
	}
	public void updateBoat(){
		try{
			System.out.println("------------------Update-Boat------------------");
			System.out.print("Member UniqueID: ");
			int uniqueID = scanner.nextInt();
			System.out.print("Old BoatID: ");
			int oldBoatID = scanner.nextInt();
			System.out.println("Boattypes : Rowboat, Sailboat, Motorsailer, Canoe, Other");
			System.out.print("New Boattype: ");
			String boat = scanner.next();
			System.out.print("New Length (cm): ");
			int length = scanner.nextInt();
			register.removeBoat(oldBoatID, uniqueID);
			register.generateBoatTypeForAdd(boat, length, uniqueID);
		} catch(InputMismatchException e){
			System.out.println("Invalid input. Please try again.");
		}
	}
}