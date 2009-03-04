package uk.ac.stand.testing;

import java.util.Arrays;

import uk.ac.stand.impl.Flag;
import uk.ac.stand.impl.MultFlag;

public class MiscTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
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

	}

}