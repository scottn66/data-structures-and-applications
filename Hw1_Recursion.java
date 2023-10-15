package dataScience;

import java.lang.Math;

public class Hw1_Recursion {


		
	/** Returns the num'th fibonnaci number. The fibonacci numbers are defined as:
	 * 		The first two fibonnaci numbers are 1
	 * 		Each fibonnaci number after that is the sum of the previous two fibonacci numbers
	 * 
	 * @param num The num'th fibonacci number to calculate
	 * @return The num'th fibonacci number
	 */
	public static int fibonacci(int num) {
		if (num == 0) {
			return 0;
		} else if (num == 1) {
			return 1;
		} else {
			return fibonacci(num-2) + fibonacci(num-1);
		}
	}

	/**
	 * Takes an input and returns a string with each item in the String in alpha-numeric order. 
	 * You can use the character value to determine the order. 
	 * For example:
	 * stringToSort.charAt(1) < stringToSort.charAt(2)
	 * will compare the characters at indexes 1 and 2. Use this model of comparison to determine order in the final string. 
	 *
	 * You may not use the .sort() function! This will result in 0 credit for this problem.
	 * 
	 * @param stringToSort The string that will be sorted
	 * @return Sorted version of stringToSort
	 */
	public static String sorter(String stringToSort) {
		boolean unfinished = true;
		String result = "";
		char[] letters = new char[stringToSort.length()];
		for(int i= 0; i < letters.length; i++) {
			letters[i] = stringToSort.charAt(i);
		}
		while(unfinished) {
			boolean changed = false;
			for (int i=0; i < (letters.length-1); i++) {
				if ( letters[i] > letters[i+1] ) {
					letters = swap(letters, i);
					changed = true;
				}
			}
			if(!changed) {
				unfinished = false;
			}
		}
		for(int i= 0; i< letters.length; i++) {
			result += letters[i];
		}
		return result;
	}
	public static char[] swap(char[] ar, int index) {
		char copy = ar[index];
		ar[index] = ar[index+1];
		ar[index+1] = copy;
		return ar;
	}
	/** 
	 * Return a string where every word is pig latin. You may assume there is no punctuation. 
	 *
	 * For the purpose of this assignment, assume that y is always a vowel.
	 * 
	 * Don't forget to capitalize the first letter of your new sentence, and fix capitalization for the rest of the sentence (no need to worry about proper nouns).
	 * Pig latin definition courtesy of https://web.ics.purdue.edu/~morelanj/RAO/prepare2.html
	 * 
	 * 1. If a word starts with a consonant and a vowel, put the first letter of the word at the end of the word and add "ay."
	 * Example: Happy = appyh + ay = appyhay
	 * 2. If a word starts with two consonants move the two consonants to the end of the word and add "ay."
	 * Example: Child = Ildch + ay = Ildchay
	 * 3. If a word starts with a vowel add the word "ay" at the end of the word.
	 * Example: Awesome = Awesome +way = Awesomeay
	 * 
	 * @param sentence The sentence to convert to pig latin
	 * @return Return our input string but in pig latin
	 */
	public static boolean containsVowel(String s, int index) {
		char[] vowels = {'A','a', 'E','e', 'I','i', 'O','o', 'U','u', 'Y','y'};
		char firstLetter = s.charAt(index);
		for (int i = 0; i< vowels.length; i++) {
			if (firstLetter == vowels[i]) {
				return true;
			}
		}
		return false;
	}
	
	public static String pigLatin(String sentence) {
		//Rules:
		// if first letter is vowel - just add ay to string
		// if first letter is consonant:
			//if second letter is vowel - just take first letter to the end
			// else(cc) - take both c's to end +ay
		sentence = sentence.toLowerCase();
		String[] sent = sentence.split(" ");
		for (int i = 0; i < sent.length; i++) {
			String word = sent[i];
			if (containsVowel(word, 0)) {
				word += "ay";
				sent[i] = word;
			} else if (containsVowel(word, 1)) {
				char fl = word.charAt(0);
				String modify = word.substring(1);
				modify += fl;
				modify += "ay";
				sent[i] = modify;
			} else {
				char fl = word.charAt(0);
				char sl = word.charAt(1);
				String modify = word.substring(2);
				modify += fl;
				modify += sl;
				modify += "ay";
				sent[i] = modify;
			}
		}
		String finalsent = "";
		for (int i = 0; i < sent.length; i++) {
			if (i!=sent.length-1) { 
				sent[i] += " ";
			}
			finalsent += sent[i];
			
		}
		String capfirst = String.valueOf(finalsent.charAt(0)).toUpperCase();
		finalsent = capfirst + finalsent.substring(1);
		return finalsent;
	}
	
	/** 
	 * Given any non-negative integer number of cents, determine the most optimal way to make change with American coinage. 
	 *  Return your answer in an array of the form [quarters, dimes, nickels, pennies].
	 *  
	 *  @param cents The number of cents to make change
	 *  @return An array containing [quarters, dimes, nickels, pennies] for change to make
	 */
	public static int[] changeMaker(int cents) {
		//schemkels = {quarters, dimes, nickels, pennies}
		int[] schmekels = new int[4];
		while (cents >= 25) {
			cents -= 25;
			schmekels[0] += 1;
		} while (cents >= 10) {
			cents -= 10;
			schmekels[1] += 1;
		} while (cents >= 5) {
			cents -= 5;
			schmekels[2] += 1;
		} while (cents >= 1) {
			cents -= 1;
			schmekels[3] += 1;
		}
		//prints coinage
		for (int s=0; s<4;s++) {
			System.out.println(schmekels[s]);
		}
		return schmekels;
	}
	
	/** 
	 * Given a num greater than 1, return true if that number is prime, otherwise return false
	 * 
	 * @param num The number that needs to be determined if its prime or not
	 * @return True if num is a prime, otherwise false
	 */
	public static boolean isPrime(int num) {
		if (num < 2) {
			return true;
		}
		for (int k = 2; k <= Math.sqrt(num); k++) {
			//System.out.println(num%k==0);
			if (num % k == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String args[]) {
		for (int i=0; i <= 13; i++) {
			System.out.print(isPrime(i));
			System.out.println(i);
		}
	}
}

