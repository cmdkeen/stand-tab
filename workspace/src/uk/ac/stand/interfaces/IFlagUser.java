package uk.ac.stand.interfaces;

import uk.ac.stand.impl.Flag;
import uk.ac.stand.impl.Flags;


public interface IFlagUser {

	public void setFlagValue(Flag flag, Object data);

	public Object getFlagValue(Flag flag) throws Exception;

	/**
	 * Theoretically only the final concrete implementation will implement this. Having a static Flags instance associated with it.
	 * 
	 * @return the flags object for this class
	 */
	public Flags getFlags();

	/**
	 * If the value for this flag implements Collection
	 * 
	 * @param flag
	 * @return whether the value for this flag holds multiple pieces of data
	 */
	public boolean isMultiObject(Flag flag);
	
	/**
	 * Assumes isMultiObject(flag) == true, -1 otherwise
	 * 
	 * @param flag
	 * @return the size of the multi object collection
	 */
	public int multiObjectSize(Flag flag);

	/**
	 * Assumes flag.getIndex() < multiObjectSize(flag) or returns null
	 * Therefore assumes isMultiObject(flag), null otherwise
	 * 
	 * @param flag the flag to get, its index field will be used
	 * @return the Object in the collection returned by flag
	 * @throws Exception 
	 */
	public Object getSubObject(Flag flag) throws Exception;

	/**
	 * Expected that the calling method will know how to interpret results
	 * First attempts to run a interpreted function by that name, if none exists then tries to run a builtin function of that name. Otherwise returns null.
	 * Builtin functions can therefore be overridden.
	 * 
	 * @param name
	 * @param args
	 * @return the result(s) - the return will implement Collection if it has several values
	 * @throws Exception 
	 */
	public Object runFunction(Flag name, Object... args) throws Exception;
	
	/**
	 * Expected that the calling method will know how to interpret results
	 * Called by runFunction, but public in case any other need
	 * 
	 * @param name the flag to run
	 * @param args any arguments to pass
	 * @return the result(s) - the return will implement Collection if it has several values
	 */
	public Object runBuiltInFunction(Flag name, Object... args);
	
	/**
	 * Expected that the calling method will know how to interpret results
	 * Called by runFunction, but public in case any other need
	 * Expected concrete class to implement so can pass this to IFunction
	 * On error returns null
	 * 
	 * @param flag the flag to run
	 * @param args any arguments to pass
	 * @return the result(s) - the return will implement Collection if it has several values
	 * @throws Exception 
	 */
	public Object runInterpretedFunction(Flag flag, Object... args) throws Exception;

	/**
	 * Each class will specify a number of builtin functions available.
	 * The flags returned from here should be used to generate the Flags file passed in to the class again through setFlags
	 * 
	 * @return the defined built in functions
	 */
	public Flag[] getBuiltInFunctions();

}