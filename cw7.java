package dataScience;

import java.util.Stack;

public class cw7 {

	/** returns x * y, but you are only allowed to use addition and subtraction
	 * You may assume only positive numbers
	 * 
	 */
	public static int multiply(int x, int y) {
		if (y == 0) {
			return 0;
		}
		return x + multiply(x, y-1);
	}
	
	/**
	 * returns the n'th fibonacci number. 
	 * fib(1) = 1
	 * fib(2) = 1
	 * fib(3) = 2
	 * Everything after is the sum of the previous two
	 * 
	 */
	public static int fibonacci(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return fibonacci(n-1)+fibonacci(n-2);
		}
	}
	
	
	/**
	 * Returns true if s is a palindrome
	 */
	public static boolean isPalindrome(String s) {
		if (s.length() == 0 || s.length() == 1) {
			return true;
		} else if (s.charAt(0) == s.charAt(s.length()-1)) {
			
			return isPalindrome(s.substring(1, s.length()-1));
			
		} else {
			return false;
		}
	}
	
	/**
	 * Returns a new stack with all the items reversed (you may modify the original stack)
	 * 
	 */
	public static Stack<Integer> reverse(Stack<Integer> s){
		Stack<Integer> t = new Stack<Integer>();
		return reverse(s,t);
	}
	
	private static Stack<Integer> reverse(Stack<Integer> a, Stack<Integer> b) {
		if (a.size() == 0) {
			return b;
		}
		int pancake = a.pop();
		b.push(pancake);
		return reverse(a,b);
	}
}
