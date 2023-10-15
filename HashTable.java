package dataScience;

import java.util.LinkedList;
import java.util.ArrayList;

public class HashTable {
	public class tuple{
		private int first, second;
		public tuple(int f, int s) {
			this.first = f;
			this.second = s;
		}
		public int getFirst() {
			return this.first;
		}
		public int getSecond() {
			return this.second;
		}
		// You might need to add more here!

	}
	//Fields
	private LinkedList<tuple>[] buckets;
	private int numItems;
	private int numBuckets;
		

	//CONSTRUCTOR
	HashTable(int b){
		
		this.numItems = 0;
		this.buckets = new LinkedList[b];
		this.numBuckets = b;
		for (int i = 0; i < buckets.length; i++) {
			this.buckets[i] = new LinkedList<tuple>();
		}
	}
	
	
	private int hash(int sum, int buckets) {
		return sum % buckets;
	}
	
	// Make a hash table of type:
	// Keys = integer, where the integer is the sum of your tuple
	// Values = tuple of two ints (from the original list)
	
	// HINT: Your HashTable will likely be much simpler than our in class example
	
	/**
	 * Creates a tuple t into our hash table at the key: sum of our two nums
	 * 
	 * O(1 + n/b)
	 * b buckets, n elements
	 * n/b = a; //load factor
	 * 
	 * @param t A tuple containing two integers from our query
	 */
	public void put(int one, int two) {
		tuple pair = new tuple(one, two);
		int key = hash((one + two), numBuckets);
		buckets[key].add(pair);
	}
	
	/**
	 * Gets back a tuple where the tuple sums to a certain size
	 * @param n The sum value to find
	 * @return A corresponding tuple with sum
	 */
	public tuple get(int sum) {
		int key = hash(sum, numBuckets);
		if (buckets[key].isEmpty()) {
			throw new IllegalArgumentException("Nothing at given sum");
		}
		int index = 0;
		tuple pairFind = buckets[key].get(index);
		while(pairFind.first + pairFind.second != sum) {
			pairFind = buckets[key].get(index++);
		}
		
		return pairFind;
		//tuple at bucket i
		//while tuple.one + tuple.two isnt equal to sum, check next i and update the tuple
		//once found, return the tuple
	}
	
	private int numItems() {
		return numItems;
	}
	
}



/*
 * need to make a data structure for all the sums of elements in the array
 * --->Linked List of linked lists
 * ---->Array of linked lists
 * [1, 2, 3, 4, 5] = 10 combinations == {(1,2),(1,3),(1,4),(1,5),
 * 										  (2,3),(2,4),(2,5),
 * 										  (3,4),(3,5),
 * 										  (4,5)}
 * 
 */


/*
 * helper function to make all the possible tuples
 */

/*
 * helper function to get the sum of each tuple
 *  3	(1,2)->(2,1)
 *  4	(1,3)
 *  5	(1,4)->(2,3)
 *  6	(1,5)->(2,4)
 *  7	(2,5)->(3,4)
 *  8	(3,5)
 *  9	(4,5)
 */

/*
 * hash the sums into a table
 */

/*
 * helper function to find the sum/tuple pair
 * 
 * for each bucket in the array of buckets
 * 		sum - bucket[i] = difference
 * 		if (arr.contains(difference))
 * 			//check if there is a duplicate (double for loop? Set?
 * 			for each tuple in bucket i
 * 				for each tuple in bucket j
 * 					add both numbers into the set
 * 					if all numbers are unique then we found a pair of tuples that add to the sum.
 * 			
 */
