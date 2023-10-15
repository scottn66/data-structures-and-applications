package dataScience;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTests {

	@Test
	public void test_pull1() {
		GanbaInfusion game = new GanbaInfusion();
		Professor pulled = game.pull();
		assertTrue(pulled.getRarity() <= 5 && pulled.getRarity() >= 3);
		
	}
	
	
	@Test
	public void test_getSize1() {
		GanbaInfusion game = new GanbaInfusion();
		game.pull();
		assertTrue(game.getSize() == 1);
	}
	
	
	@Test
	public void test_getSize2() {
		GanbaInfusion game = new GanbaInfusion();
		game.pull();
		game.pull(); // Should not increase size because not enough studentA's
		assertTrue(game.getSize() == 1);
	}
	
	@Test
	public void test_getStudentA1() {
		GanbaInfusion game = new GanbaInfusion();
		game.pull();
		assertTrue(game.getStudentA() == 0);
	}
	
	@Test
	public void test_getStudentA2() {
		GanbaInfusion game = new GanbaInfusion();
		assertTrue(game.getStudentA() == 5);
	}
	
	@Test
	public void test_sell() {
		GanbaInfusion game = new GanbaInfusion();
		Professor pulled = game.pull();
		game.sell(pulled);	
		assertTrue(game.getSize() == 0);
		assertTrue(game.getStudentA() <= 6 && game.getStudentA() >= 3);
		
	}
	
	@Test
	public void test_getProf() {
		GanbaInfusion game = new GanbaInfusion(new Professor[] {new Professor("Edmiston")});
		assertTrue(game.getSize() == 1);
		assertTrue(game.getProf("Edmiston", 3) != null);
	}
	
	
	@Test
	public void test_fusion() {
		Professor e1 = new Professor("Edmiston");
		Professor e2 = new Professor("Edmiston");
		
		GanbaInfusion game = new GanbaInfusion(new Professor[] {e1, e2});
		
		System.out.println(game.getProf("Edmiston", 3) != null);
		
		assertTrue(game.getProf("Edmiston", 3) != null);
		
		
		game.fusion(e1, e2);
		
		System.out.println(game.getProf("Edmiston", 3) == null);
		
		
		assertTrue(game.getProf("Edmiston", 3) == null);
		System.out.println(game.getProf("Edmiston", 4) == e1);
		assertTrue(game.getProf("Edmiston", 4) == e1);
	}
	
	
	@Test
	public void test_aidStudy() {
		GanbaInfusion game = new GanbaInfusion();
		game.pull();
		int sum = game.aidStudy();
		sum += game.aidStudy();
		sum += game.aidStudy();
		assertTrue(sum == game.getStudentA());	
	}
	
	
	@Test
	public void bigTest() {
		Professor t = new Professor("Forney");
		GanbaInfusion game = new GanbaInfusion(new Professor[] {t});
		game.pull();
		
		for(int i = 0; i < 10000; i++) {
			game.aidStudy();
		}
		
		// Pull a bunch of professors 
		int ecount = 0;
		int bcount = 0;
		int fcount = 0;
		while(game.getStudentA() > 5) {
			Professor p = game.pull();
			if(p.getName().equals("Edmiston")) {
				ecount += 1;
			}
			if(p.getName().equals("BJ")) {
				bcount += 1;
			}
			if(p.getName().equals("Forney")) {
				fcount += 1;
			}
		}
		
		// Check the odds
		double sum = ecount + bcount + fcount;
		assertTrue((ecount/sum) > 0.55 && (ecount/sum) < 0.65);
		assertTrue((bcount/sum) > 0.25 && (bcount/sum) < 0.35);
		assertTrue((fcount/sum) > 0.05 && (fcount/sum) < 0.15);
		
		
		assertTrue(game.getSize() > 20);		
		
	}
		

}
