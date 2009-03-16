package uk.ac.stand.impl;

import java.io.Serializable;

import uk.ac.stand.enums.Side;

public class Position implements Comparable<Position>, Serializable {

	private static final long serialVersionUID = 50320091L;
	
	private Side side;
	private int order;
	
	private static Position[] posArray = null;
	
	public Position(Side side, int order) {
		this.side = side;
		this.order = order;
	}

	public Side getSide() {
		return side;
	}

	public int getOrder() {
		return order;
	}
	
	public String toString() {
		return order + " " + side;
	}

	public int compareTo(Position o) {
		if(order<o.getOrder()) return -1;
		if(order>o.getOrder()) return 1;
		
		//Same order so return the comparison between the sides

		return this.side.compareTo(o.getSide());
		
	}
	
	public int absNumber() {
		int ret = (order-1)*2;
		if(side==Side.Opposition) ret++;
		return ret;
	}

	public static Position[] getPositionArray() {
		if(posArray==null) {
		
			int tps = 0;
			try {
				tps = (Integer) Settings.getInstance().getFlagValue("teamsPerSide");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			Position[] ret = new Position[tps*2];
			
			int j = 0;
			
			for(int i = 1; i <= tps; i++) {
				ret[j] = new Position(Side.Proposition,i);
				ret[j+1] = new Position(Side.Opposition,i);
				j+=2;
			}
			posArray = ret;
		}
				
		return posArray;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Position) {
			Position ot = (Position) o;
			if(this.order == ot.order && this.side == ot.side) return true;
		}
		return false;
	}
	
}
