package dataScience;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;


public class CW7Test {

	@Test
	public void testmultiply1() {
		assertTrue(cw7.multiply(3, 5) == 15);
	}
	
	
	@Test
	public void testmultiply2() {
		assertTrue(cw7.multiply(12, 12) == 144);
		
	}

	@Test
	public void testmultiply3() {
		assertTrue(cw7.multiply(1, 88) == 88);
	}
	
	@Test
	public void testfib1() {
		assertTrue(cw7.fibonacci(5) == 5);
	}
	
	@Test
	public void testfib2() {
		assertTrue(cw7.fibonacci(9) == 34);
	}
	
	
	@Test
	public void testpalindrome1() {
		assertTrue(cw7.isPalindrome("tacocat"));
	}
	public void testpalindrome3() {
		assertTrue(cw7.isPalindrome("racecar"));
	}
	public void testpalindrome4() {
		assertTrue(cw7.isPalindrome("kayak"));
	}
	
	@Test
	public void testpalindrome2() {
		assertFalse(cw7.isPalindrome("potato"));
	}
	
	@Test
	public void testreverse1() {
		Stack<Integer> stacky = new Stack<Integer>();
		stacky.add(1);
		stacky.add(2);
		stacky.add(3);
		
		stacky = cw7.reverse(stacky);
		
		assertTrue(stacky.peek() == 1);
	}
	
	@Test
	public void testreverse2() {
		Stack<Integer> stacky = new Stack<Integer>();
		stacky.add(1);
		stacky.add(2);
		stacky.add(3);
		
		stacky = cw7.reverse(stacky);
		stacky.pop();
		stacky = cw7.reverse(stacky);
		
		assertTrue(stacky.peek() == 3);
	}
	
	
	
	
	
	
}
