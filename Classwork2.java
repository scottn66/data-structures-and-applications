package dataScience;

import static org.junit.Assert.assertTrue;


// Classwork 2 for CS2120 Fall 2021, Section 3
public class Classwork2 {


	/*
	 * Doubles the value of each item in an array of ints
	 *  
	 * @param x Array of ints
	 * @return array of ints where the values are double of the input.
	 */
	public static int[] doubleArray(int[] x) {
		for (int i = 0; i < x.length; i++) {
			x[i] = x[i] * 2;
		}
		return x;
	}
	
	/*
	 * Takes two numbers and returns a string containing the first number followed by the second number
	 * 
	 * @param x First number in return string
	 * @param y Second number in return string
	 * @return String containing both the first and second numbers
	 */
	public static String appendNumbersAsStrings(int x, int y) {
		String a = "";
		String b = "";
		String c = "";
		a = String.valueOf(x);
		b = String.valueOf(y);
		c = a + b;
		return c;
	}
	
	/*
	 * Calculates a quadratic value of the form:
	 * ax^2 + bx + c
	 * where we evaluate for the value of x
	 * 
	 * @param x x variable in equation
	 * @param a first coefficient in equation
	 * @param b second coefficient in equation
	 * @param c constant in equation
	 * @return int with quadratic calculated
	 * 
	 */
	public static int evaluateQuadratic(int x, int a, int b, int c){
		int answer = 0;
		answer = a*x*x + b*x + c;
		return answer;
	}
}
