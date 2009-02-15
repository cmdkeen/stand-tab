package uk.ac.stand.enums;

public enum Side {
	Proposition ("Proposition"), Opposition ("Opposition");
	
	Side(String s){
		this.text = s;
	}
	
	String text;
	
	public String toString() {
		return text;
	}
}
