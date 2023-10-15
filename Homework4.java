package dataScience;

import java.util.ArrayList;


public class Homework4 {

	
	/**
	 * Checks if any four elements in the array add up to a given sum.
	 * Must use a hash table to solve the problem.
	 * Time complexity should be no worse than O(n^3), though this hinted solution is better.
	 * @param arr The array of integers to check. Each number is a unique positive integer.
	 * @param sum The target sum
	 * @return True if there exists any 4 elements that sum up to sum
	 */
	public static boolean fourSum(int[] arr, int sum) {
		// HINT: Consider breaking this problem down into another: Look for 2 sets of pairs where the pairs sum to our sum
		//how many combinations of tuple pairs can we make? == number of buckets
		
		HashTable cabinet = new HashTable(50);
		ArrayList<Integer> SumBuckets = new ArrayList<Integer>(); 
		
		for (int i = 0; i < arr.length; i++ ) {
			for (int j = i+1; j < arr.length; j++) {
				
				cabinet.put(arr[i], arr[j]);
				int sumCombo = arr[i] + arr[j];
				if (!SumBuckets.contains(sumCombo)) {
					SumBuckets.add(sumCombo);
				}
			}
			
		}
		for (int i = 0; i < SumBuckets.size(); i++ ) {
			int difference = sum - SumBuckets.get(i);
			
			if (SumBuckets.contains(difference)) {
				try {
					if (isUnique(cabinet.get(SumBuckets.get(i)), cabinet.get(difference))) {
						return true;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//First(a, b) Second(c, d)
		}
		
		
		return false;
	}
	
	/*
	 * helper function returns true if all numbers in pair of tuples are unique to the fourSum
	 * first(a,b) second(c, d)
	 */
	private static boolean isUnique(HashTable.tuple one, HashTable.tuple two) {
		if (one.getFirst() != two.getFirst() || one.getFirst() != two.getSecond()) {
			return true;
		} else if (one.getSecond() != two.getFirst() || one.getSecond() != two.getSecond()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * There are a total of num courses you have to take, labeled from 0 to num-1.
	 * You are given an array prerequisites where prerequisites[i] = [a_i, b_i] indicates
	 * that you must take course b_i first if you want to take course a_i
	 * 
	 * For example, the pair [1, 3] means to take course 1 you must take course 3
	 * 
	 * Return true if it is possible to finish all courses. Otherwise return false
	 * 
	 * You do not need to efficiently check. Just find the correct answer.
	 * 
	 * @param num the number of courses
	 * @param prerequisites array of prerequisite pairs
	 * 
	 */
	public static boolean canGraduate(int num, int[][] prerequisites) {
		// HINT: This is the same as finding if a cycle exists in a directed graph.
		// If a cycle exists, no ordering exists and therefore it is impossible
		Graph curriculum = new Graph(num);
		for(int i = 0; i < prerequisites.length; i++) {
			curriculum.addEdge(prerequisites[i][0], prerequisites[i][1]);
		}
		
		return !curriculum.hasCycle();
	}
	
	
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, 5, 6, 8, 9, 10};
		boolean summer = fourSum(nums, 28);
		System.out.println(summer);
	}
}
