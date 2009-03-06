package uk.ac.stand.impl;

import java.io.Serializable;
import java.util.Arrays;

import uk.ac.stand.interfaces.IFunction;

public class Flags implements Serializable {
	
	private static final long serialVersionUID = 50320091L;
	
	private Flag[] flags; //Stores all the flags in the order passed in (for tablemodel display purposes etc)
	private Flag[] sortedflags; //The flags sorted for quick searching
	private Flag[] fields; //The passed in fields, kept sorted
	private Flag[] builtin; //The passed in builtin functions, kept sorted
	private Flag[] interpreted; //The passed in interpreted functions names, kept sorted
	private IFunction[] interpretedFunctions; //The passed in interpreted functions, kept sorted
	
	public Flags(Flag[] fields, Flag[] builtin, IFunction[] interpretedFunctions) {
		this.fields = fields;
		this.builtin = builtin;
		if(interpretedFunctions!=null) {
			this.interpreted = new Flag[interpretedFunctions.length];
		} else {
			this.interpreted = new Flag[0];
		}
		if(interpretedFunctions!=null) {
			this.interpretedFunctions = interpretedFunctions;
		} else this.interpretedFunctions = new IFunction[0];
		
		if(interpretedFunctions!=null) for(int i = 0; i < interpretedFunctions.length; i++) this.interpreted[i] = interpretedFunctions[i].getFlag();
		
		flags = new Flag[fields.length+builtin.length+interpreted.length];
		System.arraycopy(fields, 0, flags, 0, fields.length);
		System.arraycopy(builtin, 0, flags, fields.length, builtin.length);
		if(interpreted.length>0) System.arraycopy(interpreted, 0, flags, fields.length+builtin.length, interpreted.length);
		
		sortedflags = new Flag[flags.length];
		System.arraycopy(flags, 0, sortedflags, 0, flags.length);
		Arrays.sort(sortedflags);
		
		Arrays.sort(this.fields);
		Arrays.sort(this.builtin);
		if(interpreted.length>0) Arrays.sort(this.interpreted);
		Arrays.sort(this.interpretedFunctions);
	}
	
	/**
	 * The array has been sorted. For the original ordering use Arrays.copyOfRange(getFlags(),0,getFields().length)
	 * 
	 * @return the field names
	 */
	public Flag[] getFields() {
		return fields;
	}
	
	public Flag getFlagFromString(String s) {
		return getFlagFromSimilar(new Flag(s)); //Should be quicker than going through all due to binary search
	}
	
	/**
	 * The array has been sorted. For the original ordering use Arrays.copyOfRange(getFlags(),getFields().length,getBuiltin().length)
	 * 
	 * @return the builtin function names
	 */
	public Flag[] getBuiltin() {
		return builtin;
	}
	
	/**
	 * The array has been sorted. For the original ordering use Arrays.copyOfRange(getFlags(),getFields().length+getBuiltin().length,getInterpreted().length)
	 * 
	 * @return the interpreted function names
	 */
	public Flag[] getInterpreted() {
		return interpreted;
	}
	
	public Flag[] getFlags() {
		return flags;
	}
	
	public Flag[] getSortedFlags() {
		return sortedflags;
	}
	
	public boolean containsFlag(Flag s) {
		return Arrays.binarySearch(sortedflags, s) < 0 ?  false : true; //Search the sorted string for this flag
	}
	
	/**
	 * Takes a flag and returns the flag in use such that s.equals(return)==true
	 * Used as a safety feature so that all used flags are the same object as used in the underlying data structures.
	 * 
	 * @param s
	 * @return the flag in use
	 */
	public Flag getFlagFromSimilar(Flag s) {
		int i = Arrays.binarySearch(sortedflags, s);
		if(i<0) return null;
		return sortedflags[i];
	}
	
	/**
	 * Same as !isFunction(s)
	 * 
	 * @param s the name of the flag
	 * @return whether a field
	 */
	public boolean isField(Flag s) {
		return Arrays.binarySearch(fields, s) < 0 ? false : true;
	}
	
	public boolean isBuiltinFunction(Flag s) {
		return Arrays.binarySearch(builtin, s) < 0 ? false : true;
	}
	
	public boolean isInterpretedFunction(Flag s) {
		return Arrays.binarySearch(interpreted, s) < 0 ? false : true;
	}
	
	public boolean isFunction(Flag flag) {
		return isBuiltinFunction(flag) || isInterpretedFunction(flag);
	}
	
	public IFunction getFunction(Flag s) {
		if(isInterpretedFunction(s)) return interpretedFunctions[Arrays.binarySearch(interpreted, s)];
		return null;
	}
	

}
