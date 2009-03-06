package uk.ac.stand.impl;

import java.io.Serializable;

public class Flag implements Comparable<Flag>, Serializable {
	
	private static final long serialVersionUID = 50320091L;
	
	protected String name;
	private Class<?> type = Object.class;
	
	public Flag (String name) {
		this.name = name;
	}
	
	public Flag (String name, Object type) {
		this.name = name;
		this.type = type.getClass();
	}
	
	public String getName() {
		return name;
	}
	
	public int getIndex() {
		return -1;
	}
	
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
	
	//TODO get this working?
	public boolean isAcceptable(Object o) {
		if(type.isAssignableFrom(o.getClass())) return true;
		return false;
	}


}
