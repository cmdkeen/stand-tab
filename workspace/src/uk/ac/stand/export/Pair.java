package uk.ac.stand.export;

public class Pair<F,S> {

	F left;
	S right;
	
	public Pair (F left, S right){
		this.left = left;
		this.right = right;
	}
	
	public F getLeft() {
		return left;
	}
	
	public S getRight() {
		return right;
	}
	
}
