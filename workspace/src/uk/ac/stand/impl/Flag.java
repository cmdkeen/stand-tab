package uk.ac.stand.impl;

import java.io.Serializable;

public class Flag implements Comparable<Flag>, Serializable {
	
	private static final long serialVersionUID = 50320091L;
	
	protected String name;
	private Class<?> type = Object.class;
	
	public Flag (String name, Class<?> type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * If isMultiple() is true then this will return the index location of this particular flag
	 * 
	 * @return the index into the collection or -1 if not a multiple
	 */
	public int getIndex() {
		return -1;
	}
	
	/**
	 * Test as to whether this flag has multiple values attached. Ie. if it is a collection
	 * 
	 * @return if this flag is a multiple
	 */
	public boolean isMultiple() {
		return false;
	}

	public int compareTo(Flag o) {
		return name.compareTo(o.getName());
	}
	
	public boolean equals(Flag o) {
		if(compareTo(o)==0) return true;
		return false;
	}
	
	public String toString() {
		return name;	
	}

	/**
	 * Checks whether a supplied instance could be assigned to a flag
	 * 
	 * @param o an instance of an object (or primitive)
	 * @return whether this instance's class inherits from the defined class for this flag
	 */
	public boolean isAcceptable(Object o) {
		return type.isAssignableFrom(o.getClass());
	}
	
	/**
	 * Checks whether a supplied class could be assigned to a flag
	 * 
	 * @param t the class type to check
	 * @return whether this class inherits from the defined class for this flag
	 */
	public boolean isAcceptable(Class<?> t) {
		return type.isAssignableFrom(t);
	}

	public Class<?> getType() {
		return type;
	}
}
