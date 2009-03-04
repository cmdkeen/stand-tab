package uk.ac.stand.impl;

public class Flag implements Comparable<Flag> {
	
	protected String name;
	
	public Flag (String name) {
		this.name = name;
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


}
