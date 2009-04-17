package uk.ac.stand.testing.junit;

import org.junit.*;
import uk.ac.stand.testing.junit.StabTest.*;

@SuppressWarnings("unused") //Scala/Java interface warnings
public class StabTests {
	
	StabTest stab = new StabTest();
	
	private void setupCompetition() {
		
	}
	
	@Test
	public void test_printMatrix() {
		String result = stab.test_printMatrix();
		
		Assert.assertEquals("[[1, 2, 3], [1, 2, 3]]", result);
	}
	
	@Test
	public void test_param() {
		//System.out.print(stab.test_param1m() + "given test : int\n".equals(stab.test_param1m()));
		Assert.assertEquals("given test : int\n",stab.test_param1m());
		
		Assert.assertEquals("given test : int\n",stab.test_param2m());
		
		Assert.assertEquals("parameter test is 3\n",stab.test_param1p());
		
		Assert.assertEquals("parameter test is 1\n",stab.test_param2p());
		
		Assert.assertEquals("parameter test is 0\n",stab.test_param3p());
	}
	
	
	
}
