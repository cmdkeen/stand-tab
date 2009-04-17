package uk.ac.stand.testing.junit;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

import uk.ac.stand.impl.Competition;
import uk.ac.stand.testing.DataSetup;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ProjectTest.class,
  FlagTest.class,
  IOTest.class,
  MinionRun.class,
  StabTests.class
})

public class ProjectTest {
	
	static Competition c;
	
	@BeforeClass
	public static void setupCompetition() throws Exception {
		c = Competition.getInstance();
		
		DataSetup.addTeamsSpeakers();
	}
	
	@Test
	public void setupComplete() {
		Assert.assertTrue(c.isSetupComplete());
	}
	
}
