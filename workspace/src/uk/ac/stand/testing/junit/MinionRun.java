package uk.ac.stand.testing.junit;

import java.util.LinkedList;

import org.junit.*;

import uk.ac.stand.minion.EssenceToMinion;

public class MinionRun {
	
	@Test
	public void TestRuns() {
		
	}
	
	@Test
	public void TestSingleResults() {
		
	}
	
	@Test
	public void TestBestResult() {
		
	}
	
	@Test
	public void TestInteractive() {
		String interactiveTest = "language ESSENCE' 1.b.a\n" + 
			"letting n be domain int(1..10000)\n" + 
			"find i : n\n" + 
			"maximising i";
		
		EssenceToMinion etm = new EssenceToMinion(interactiveTest, "");
		
		etm.runMinionInteractive();
		
		while(etm.isRunning()) {
			etm.getLatestResult();
			Assert.assertTrue(etm.getObjectiveValue() < 10000);
		}
		
		etm.getLatestResult();
		Assert.assertEquals(10000, etm.getObjectiveValue());
		
		
	}
	
	@Test
	public void TestFail() {
		String failTest = "language ESSENCE' 1.b.a\n" + 
			"letting n be domain int(1..100)\n" + 
			"find i : n\n" + 
			"such that\n" + 
			"i > 100";
		
		EssenceToMinion etm = new EssenceToMinion(failTest, "");
		
		LinkedList<String> r = etm.runMinion();
			
		Assert.assertNull(r);
	}

}
