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
		return null;
	}

	public Object runBuiltInFunction(Flag name, Object... args) {
		return null;
	}

	public Object runInterpretedFunction(Flag flag, Object... args)
			throws Exception {
		return null;
	}

	public boolean isValid(String name, Object value) {
		return true;
	}
}
