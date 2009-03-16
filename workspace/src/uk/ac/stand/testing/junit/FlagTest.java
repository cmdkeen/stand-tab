package uk.ac.stand.testing.junit;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.*;

import uk.ac.stand.impl.Flag;
import uk.ac.stand.impl.Flags;
import uk.ac.stand.impl.MultFlag;
import uk.ac.stand.impl.exceptions.StoreException;

public class FlagTest {
	
	private FlagTestImpl t;
	Flag a;
	Flag b;
	Flag c;
	Flag d;
	
	@Before
	public void SetUp() throws StoreException {
		t = new FlagTestImpl();
		a = new Flag("one", String.class);
		b = new Flag("two", Collection.class);
		c = new MultFlag("three", 0, Integer.class);
		d = new MultFlag("three", 1,  Integer.class);
		
		Flag[] temp = new Flag[4];
		temp[0] = a;
		temp[1] = b;
		temp[2] = c;
		temp[3] = d;
		
		Flags fl = new Flags(temp, null, null);
		
		Assert.assertNotNull(fl);
		
		FlagTestImpl.setFlags(fl);
		
		t.setFlagValue(c, 0);
		t.setFlagValue(d, 1);
	}
	
	@Test
	public void FlagValue() throws Exception {
		Assert.assertEquals(0, t.getFlagValue(c));
		Assert.assertEquals(1, t.getFlagValue(d));
	}
	
	@Test
	public void Acceptable() {
		//Test to check whether the acceptability test works

		Assert.assertTrue(a.isAcceptable("hello"));
		Assert.assertTrue(b.isAcceptable(new LinkedList<Object>()));
		Assert.assertFalse(a.isAcceptable(new LinkedList<String>()));
		
		Assert.assertTrue(a.isAcceptable(String.class));
	}
	
	@Test
	public void GetFromString() throws Exception {
		
		t.setFlagValue(a, "Hello");
		Assert.assertEquals("Hello", t.getFlagValue("one"));
		Assert.assertEquals(0, t.getFlagValue(t.getFlags().getFlagFromString("three",0)));
		Assert.assertEquals(0, t.getFlagValue("three", 0));
	}
	
}
