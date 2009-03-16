package uk.ac.stand.testing;

import uk.ac.stand.impl.Flag;
import uk.ac.stand.impl.MultFlag;

public class MiscTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		Flag[] array = new Flag[4];
		
		Flag a = new MultFlag("a",1);
		Flag b = new Flag("b");
		Flag c = new Flag("c");
		Flag d = new MultFlag("a",0);
		
		array[0] = a;
		array[1] = b;
		array[2] = c;
		array[3] = d;
		
		Arrays.sort(array);
		int i = Arrays.binarySearch(array, a);
		
		System.out.println(i);
		*/
		
		Flag f = new Flag("a", Integer.class);
		
		MultFlag mf = new MultFlag("b",2, Integer.class);
		
		System.out.println(f.isAcceptable(f));
		
		System.out.println(f.getClass().isInstance(mf));
		
		System.out.println(Integer.class.isAssignableFrom(Integer.class));
		
		System.out.println(f.getClass().isAssignableFrom(MultFlag.class));
		
	}

}
