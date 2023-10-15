package dataScience;

import java.util.Random;
import dataScience.ProfArray;

public class GanbaInfusion {
	
	// TODO: Define your fields
	
	// studentA's should be 5 by default
	int studentA;
	ProfArray Prof = new ProfArray();
	
	GanbaInfusion(){
		// TODO: Implement a default constructor
		studentA = 5;
		Prof = new ProfArray();
	}
	
	GanbaInfusion(Professor[] prebuilt){
		// TODO: Implement a constructor from an array
		Prof = new ProfArray();
		studentA = 5;
		for (int i = 0; i < prebuilt.length; i++) {
			Prof.append(prebuilt[i]);
			
		}
		
	}

	
	
	/** Returns a reference to a professor in the list with the name "name" and the rarity level "r". 
	 * If there is none that exists, return Null
	 * 
	 * @return the Professor found, or null if not found
	 */
	public Professor getProf(String name, int r) {
		// TODO: Implement the function
		for (int i = 0; i<Prof.size(); i++) {
			if( Prof.getAt(i).getName() == name ) {
				if( Prof.getAt(i).getRarity() == r ) {
					//System.out.println(size);
					return Prof.getAt(i);
				}
			}
		}
		return null;
	}
	
	
	
	/**Returns the size of the list containing all the professors. This should be a total count of professors.
	 * 
	 * @return The count of professors
	 */
	public int getSize() {
		// TODO: Implement the function
		int sizeList = 0;
		for (int i = 0; i < Prof.size(); i++) {
			sizeList++;
		}
		return sizeList;
	}
	
	
	
	/**Returns the amount of studentA, our currency.
	 * 
	 * @return the amount of studentA
	 */
	public int getStudentA() {
		return studentA;
	}
	
	
	
	
	/**
	 * Sells a professor and gives you money equal to the rarity. This function should check if this function is in our list, 
	 * and if they are then remove the professor from our list and sell. 
	 * If the professor is not in our list, do nothing. 
	 * @param toSell
	 * @return The money received for selling.
	 */
	public int sell(Professor toSell) {
		//TODO: Implement the function.
		if (getProf(toSell.getName(), toSell.getRarity()) == null) {
			return -1;
		} 
		int worth = toSell.getRarity();
		studentA += worth;
		int index = Prof.findProf(toSell.getName());
		Prof.removeAt(index);
		return worth;
	}

	
	
	
	/** Returns our Professor list. 
	 * 	Items printed out should be formatted:
	 *  Name, Rarity; Name, Rarity; 
	 *  with commas separating the Name from the Rarity, and semicolons 
	 *  separating different entities
	 * 
	 */
	public String toString() {
		// TODO: Build our string to return
		String list = "";
		for (int i = 0; i<Prof.size(); i++) {
			Professor current = Prof.getAt(i);
			list += current.getName();
			list += ", ";
			list += current.getRarity();
			list += "; ";
		}
		return list;
	}
	
	
	
	
	/** Adds a new professor to our list. Costs 5 studentA. The rarity breakdown is as follows:
	 * 
	 * 60% to pull a rarity 3 Edmiston
	 * 30% to pull a rarity 4 BJ
	 * 10% to pull a rarity 5 Forney
	 * 
	 * @return a reference to the Professor that was pulled and added to the list
	 */
	public Professor pull() {
		
		// A string to hold the name of whom got pulled
		String whoGotPulled = "";
		
		// Generate a random number 1-100
		Random rand = new Random();
		int val = rand.nextInt(100); // Generates random value 1 in 100
		
		// Determines whom got pulled
		if (val <= 60) {
			whoGotPulled = "Edmiston";	
			
		} else if (val <= 90) { // This is between 60-90
			whoGotPulled = "BJ";
		} else {
			whoGotPulled = "Forney";
		}
			
		Professor prof = new Professor(whoGotPulled);
		if (studentA < 5) {
			System.out.println("need more A's bud");
			return prof;
		}
		studentA -= 5;
		Prof.append(prof);
		// TODO: Need to check if there is enough studentA, and subtract 5 from our count
		// TODO: Need to update our list with the new professor
		
		
		return prof;
	}
	
	/** If both professors are of the same type, fuse them to "increase rarity". If they are different types, do nothing.
	 * 
	 *  The base professor's rarity increases by 1 stage if successful.
	 * 
	 * @param base The professor that we are "ranking up".
	 * @param fodder The professor instance we are deleting rank another card up.
	 * @return The base professor after changes are made.
	 */
	public Professor fusion(Professor base, Professor fodder) {
		
		// TODO: Implement this function
		boolean same = false;
		if (base.getName() == fodder.getName()) {
			same = true;
		}
		if (same) {
			System.out.println(base.getRarity());
			base.setRarity(base.getRarity()+1);
			System.out.println(base.getRarity());
			fodder.setRarity(0);
		}
		return base;
	}
	
	/** Uses all professors in the Professor list to help students study. Each student that you successfully help awards 1 studentA.
	 *  
	 *  When this method is called, you will aid students in ascending rarity order, until you can no longer help a student.
	 *  The students start at rarity 1, and after each successful aid, the rarity of the next student increases by one from the previous.
	 *  
	 *  The default chance to successfully help a student is 50%.
	 *  For each rarity higher a professor is than the student, add 10% to the help chance. For example, a rarity 4 professor helping a rarity 2 student would have 70% chance (50% base + 20% for difference in rarity) to help the student earn an A.
	 *  For each rarity lower a professor is than the student, subtract 10% from the help chance. For example, a rarity 3 professor helping a rarity 4 student would have a 40% chance (50% base -10% for difference in rarity) to help the student earn an A.
	 *  
	 *  Once an instance of a Professor, that particular instance can no longer help students this try. However, the rest of the professors can still assist.
	 *  After the "study session" is over, all Professors get a break and are ready to try again the next time aidStudy is called.
	 *  
	 * @return Returns the highest rarity student that was successfully helped.
	 */
	public int aidStudy() {
		int studentRarity = 1;
		for (int i = 0; i < Prof.size(); i++) {
			int profR = Prof.getAt(i).getRarity();
			int success = (50) + (profR - studentRarity)*10;
			Random rand = new Random();
			int num = rand.nextInt(100);
			if (num < success) {
				studentRarity++;
				studentA++;
			}
		}
		 // Generates a random number between 1-100
		
		//TODO: Build the aidStudy function, that has professors help students
		
		//pull least rare professors first
		
		
		
		return studentRarity-1;
	}
	
	
}
