package dataScience;

import static org.junit.Assert.*;

import org.junit.Test;

public class Homework4Tests {

	@Test
	public void hashTest1() {
		// Arbitrarily set at 100 for 100 buckets
		HashTable ht = new HashTable(100);
		ht.put(1, 2);
		HashTable.tuple t = ht.get(3);
		assertTrue(t.getFirst() == 1);
		assertTrue(t.getSecond() == 2);
	}
	
	@Test
	public void hashTest2() {
		HashTable ht = new HashTable(100);
		ht.put(1, 4);
		ht.put(2, 3);
		HashTable.tuple t = ht.get(5);
		assertTrue(t.getFirst() + t.getSecond() == 5);
		assertTrue(t.getSecond() == 3 || t.getSecond() == 4);
	}
	@Test
	public void hashTest3() {
		HashTable ht = new HashTable(100);
		ht.put(1, 2);
		HashTable.tuple t = ht.get(3);
		assertTrue(t.getFirst() == 1);
		assertTrue(t.getSecond() == 2);
	}
	
	@Test
	public void graphTest1() {
		Graph g = new Graph(2);
		g.addEdge(0, 1);
		
		assertTrue(g.hasEdge(0, 1));
		assertFalse(g.hasEdge(1, 0));
	}
	
	
	@Test
	public void graphTest2() {
		Graph g = new Graph(3);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		
		assertTrue(g.hasEdge(0, 1));
		assertFalse(g.hasEdge(1, 0));
		assertTrue(g.hasCycle());
	}
	
	@Test
	public void graphTest3() {
		Graph g = new Graph(1);
		// Having a 1 vertex graph is legal
		g.addEdge(0, 0);
		assertTrue(g.hasEdge(0, 0));
	}
	
	@Test
	public void canGraduate1() {
		int[][] a = { {0, 1}, {1,0} };
		assertFalse(Homework4.canGraduate(2, a));

		int[][] a2 = { {0, 1} };
		assertTrue(Homework4.canGraduate(2, a2));
	}
	
	
	@Test
	public void canGraduate2() {
		
		int[][] a3 = { {0, 1}, {1,2}, {2, 0} };
		assertFalse(Homework4.canGraduate(3, a3));
		
	}
	
	@Test
	public void canGraduate3() {

		int[][] a = {{0,1}, {1,2}, {2,4}, {2,5}, {2,6}, {7,2}};
		assertTrue(Homework4.canGraduate(8, a));

	}
	

	
	
	@Test
	public void sumtest1() {
		int[] nums = {1, 2, 3, 4};
		assertTrue(Homework4.fourSum(nums, 10));
	}
	
	@Test
	public void sumtest2() {
		int[] nums = {1, 2, 3, 4, 5};
		assertFalse(Homework4.fourSum(nums, 21));
	}
	
	
	@Test
	public void sumtest3() {
		int[] nums = {1, 2, 3, 8, 9, 10, 20, 21, 22};
		assertTrue(Homework4.fourSum(nums, 60));
		
	}
	@Test
	public void sumtest4() {
		int[] nums = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
		assertTrue(Homework4.fourSum(nums, 100));
	}
	@Test
	public void sumtest5() {
		int[] nums = {5, 10, 20, 30, 35, 40, 45, 50};
		assertTrue(Homework4.fourSum(nums, 100));
	}
	@Test
	public void sumtest6() {
		int[] nums = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 134};
		assertTrue(Homework4.fourSum(nums, 71));
	}
	/*
	@Test
	public void sumtestn() {
		int[] nums = {};
		assertFalse(Homework4.fourSum(nums, ));
	}
	 */
	
	
	
	
	
	
}
