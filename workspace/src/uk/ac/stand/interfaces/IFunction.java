package uk.ac.stand.interfaces;

import uk.ac.stand.impl.Flag;

public interface IFunction extends Comparable<IFunction> {

	/**
	 * @param caller the calling class must implement FlagUser - pass in Competition if nothing else
	 * @param arguments the required arguments, if the wrong number are supplied will fail
	 * @return the return values, if several then in some form of collection - assumes calling code will know how to deal
	 * @throws Exception mainly for the wrong number of arguments, could be other reasons (e.g. IO)
	 */
	public Object run(Object caller, Object... arguments) throws Exception;
	
	public Flag getFlag();

}
