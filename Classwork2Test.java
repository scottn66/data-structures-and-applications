package dataScience;


import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class Classwork2Test {

	@Test
	public void testDoubleArray1() {
		int[] array1 = {3, 4};
		int[] array1Solution = {6, 8};
		int[] array1Attempt = Classwork2.doubleArray(array1);
		// This is a function of the Arrays class, that checks if the content of two arrays are identical
		assertTrue(Arrays.equals(array1Solution, array1Attempt)); 
		
	}
	
	@Test
	public void testDoubleArray2() {
		int[] array2 = {-6, 3};
		int[] array2Solution = {-12, 6};
		int[] array2Attempt = Classwork2.doubleArray(array2);
		assertTrue(Arrays.equals(array2Solution, array2Attempt));
	}
	
	@Test
	public void testDoubleArray3() {
		int[] array3 = {20, 50, -100, 0};
		int[] array3Solution = {40, 100, -200, 0};
		int[] array3Attempt = Classwork2.doubleArray(array3);
		assertTrue(Arrays.equals(array3Solution, array3Attempt));
	}
	
	
	
	
	@Test
	public void testAppendNumbersAsString1() {
		assertTrue(Classwork2.appendNumbersAsStrings(0, 0).equals("00"));
		assertTrue(Classwork2.appendNumbersAsStrings(3, 4).equals("34"));
		
	}
	@Test
	public void testAppendNumbersAsString2() {
		assertTrue(Classwork2.appendNumbersAsStrings(123, 456).equals("123456"));
		assertTrue(Classwork2.appendNumbersAsStrings(1, 2345678).equals("12345678"));
		
	}
	
	@Test
	public void testAppendNumbersAsString3() {
		assertTrue(Classwork2.appendNumbersAsStrings(-3, 4).equals("-34"));
		assertTrue(Classwork2.appendNumbersAsStrings(-3, -4).equals("-3-4"));

	}
	
	
	@Test
	public void testQuadratic() {
		assertTrue(Classwork2.evaluateQuadratic(2, 1, 1, 1) == 4 + 2 + 1);
		
		// TODO: Write at least 5 additional tests here
	}
	
	@Test
	public void testQuadratic1() {
		assertTrue(Classwork2.evaluateQuadratic(3, 2, 2, 2) == 18 + 6 + 2);
	}
	
	@Test
	public void testQuadratic2() {
		assertTrue(Classwork2.evaluateQuadratic(10, 2, 3, 6) == 200 + 30 + 6);
	}
	
	@Test
	public void testQuadratic3() {
		assertTrue(Classwork2.evaluateQuadratic(5, 2, 3, 4) == 50 + 15 + 4);
	}
	
	@Test
	public void testQuadratic4() {
		assertTrue(Classwork2.evaluateQuadratic(3, 4, 5, 18) == 36 + 15 + 18);
	}

	@Test
	public void testQuadratic5() {
		assertTrue(Classwork2.evaluateQuadratic(10, 4, 2, 0) == 400 + 20 + 0);
	}
}
