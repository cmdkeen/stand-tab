package uk.ac.stand.testing.junit;

import uk.ac.stand.impl.Flag;
import uk.ac.stand.impl.FlagUser;
import uk.ac.stand.impl.Flags;

@SuppressWarnings("serial")
public class FlagTestImpl extends FlagUser {
	
private static Flags flags = null;
	
	public static void setFlags(Flags flags) {
		FlagTestImpl.flags = flags;
	}
	
	public Flags getFlags() {
		return flags;
	}

	public Flag[] getBuiltInFunctions() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object runBuiltInFunction(Flag name, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object runInterpretedFunction(Flag flag, Object... args)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
