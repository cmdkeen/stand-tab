package uk.ac.stand.antlr;

import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Draw;

public class DrawFunction {
	
	private String drawName;
	private AntlrFunction function;
	
	public DrawFunction(String drawName, AntlrFunction function) {
		this.drawName = drawName;
		this.function = function;
	}
	
	public String toString() {
		return drawName;
	}
	
	public Draw doDraw(int round) throws Exception {
		Object result = function.run(Competition.getInstance(), round);
		
		if(result instanceof Draw) return (Draw) result;
		
		throw new Exception("Return value not a draw");
	}

}
