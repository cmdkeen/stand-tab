package uk.ac.stand.testing.junit;

import java.io.File;

import org.junit.*;

public class IOTest {

	@Test
	public void Directories() {
		//Code taken from uk.ac.stand.export.Export
		//Aim is to test the creation of directories for exported code
		
		String dirs = "test/one/two/";
		File d = new File(dirs);
		d.mkdirs();
		Assert.assertTrue(d.exists());
		
		//Cleanup
		if(d.delete()) {
			d = new File("test/one");
			if(d.delete()) {
				d = new File("test");
				d.delete();
			}
		}
		
	}
	
	
}
