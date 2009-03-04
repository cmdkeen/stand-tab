package uk.ac.stand.impl;

public class MultFlag extends Flag {
	
	private int index;

	public MultFlag(String name, int index) {
		super(name);
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
