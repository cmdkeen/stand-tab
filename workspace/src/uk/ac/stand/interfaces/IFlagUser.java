package uk.ac.stand.interfaces;

import uk.ac.stand.impl.Flag;
import uk.ac.stand.impl.Flags;
import uk.ac.stand.impl.exceptions.StoreException;


public interface IFlagUser {
	
	/**
	 * Each class will specify a number of builtin functions available.
	 * The flags returned from here should be used to generate the Flags file passed in to the class again through setFlags
	 * 
	 * @return the defined built in functions
	 */
	public Flag[] getBuiltInFunctions();

	/**
	 * Only the final concrete implementation will implement this. The class should have a static field so that all instances of the class have the same flags at runtime.
	 * 
	 * @return the flags object for this class
	 */
	public Flags getFlags();
	
	/**
	 * Fetches the object referenced by the flag value 
	 * 
	 * @param flag the flag
	 * @return the object, or null if not yet set
	 * @throws Exception
	 */
	public Object getFlagValue(Flag flag) throws Exception;
	
	/**
	 * Returns the object referenced by the flag value.
	 * Do not use if a multi-object flag  
	 * 
	 * @param flagName the name of the flag
	 * @return the flag value
	 * @throws Exception
	 */
	public Object getFlagValue(String flagName) throws Exception;

	/**
	 * Returns the object referenced by the flag value.
	 * For use by multi object flags
	 * 
	 * @param flagName the name of the flag
	 * @param index the index mapped to the sub value
	 * @return the stored object
	 * @throws Exception
	 */
	public Object getFlagValue(String flagName, int index) throws Exception;

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
	 * Stores the supplied data against the supplied flag, which can then be used to retrieve it
	 * 
	 * @param flag the flag to store the data against
	 * @param data the data to store
	 * @throws StoreException
	 */
	public void setFlagValue(Flag flag, Object data) throws StoreException;
	
	/**
	 * Stores the supplied data against the flag with the same name as supplied string, which can then be used to retrieve it
	 * Do not use for a flag with multiple pieces of data
	 * 
	 * @param flagName the name of the flag to store the data against
	 * @param data the data to store
	 * @throws StoreException
	 */
	public void setFlagValue(String flagName, Object data) throws StoreException;
	
	/**
	 * Stores the supplied data against the flag with the same name as supplied string mapped to the given index, which can then be used to retrieve it
	 * Use when the flag holds multiple indexed pieces of data
	 * 
	 * @param flagName the name of the flag to store the data against
	 * @param index the index of the data in the flag
	 * @param data the data to store
	 * @throws StoreException
	 */
	public void setFlagValue(String flagName, Integer index, Object data) throws StoreException;
	
	/**
	 * Allows implementation to define checks on the validity of data before storing. Can be combined with a rules file.
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public boolean isValid(String name, Object value);

}