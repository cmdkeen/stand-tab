package uk.ac.stand.impl;

import java.io.Serializable;

public class MultFlag extends Flag implements Serializable {
	
	private static final long serialVersionUID = 50320091L;
	
	private int index;

	public MultFlag(String name, int index, Class<?> type) {
		super(name, type);
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}

	
	public int compareTo(Flag o) {
		if(o.isMultiple() && name.equals(o.getName())) {
			return index - o.getIndex();
		}
		return name.compareTo(o.getName());
	}
	
	public String toString() {
		return name + " " + index;
	}
	
	public boolean isMultiple() {
		return true;
	}
	
	public boolean equals(Flag o) {
		if(compareTo(o)==0) return true;
		return false;
	}
	
}
