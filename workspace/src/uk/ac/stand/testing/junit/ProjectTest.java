package uk.ac.stand.testing.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  FlagTest.class,
  IOTest.class,
  MinionRun.class
})

public class ProjectTest {
	
}
