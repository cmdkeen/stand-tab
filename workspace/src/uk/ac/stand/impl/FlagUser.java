package uk.ac.stand.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uk.ac.stand.interfaces.IFlagUser;

public abstract class FlagUser implements IFlagUser, Serializable {
	
	//TODO Look at using generics to tie flags to certain objects e.g. Flag<String> for TeamName
	
	private static final long serialVersionUID = 50320091L;
	
	public Map<String, Object> store;
	
	public FlagUser() {
		store = new HashMap<String, Object>();
	}

	public Object getFlagValue(Flag flag) throws Exception {
		if(getFlags().isFunction(flag)) {
			return runFunction(flag, (Object[]) null);
		}
		return store.get(flag.getName());
	}
	
	@SuppressWarnings("unchecked")
	public Object getSubObject(Flag flag) throws Exception {
		if(!(flag instanceof MultFlag))	return null;
		//Index check important as default index value is -1
		//Previously checked size and index, but due to handling maps wasn't working so done later
		if(flag.getIndex() < 0)	return null;
		if(getFlagValue(flag) instanceof HashMap) {
			Map<Integer, Object> c = (Map<Integer, Object>)getFlagValue(flag);
			if(c==null) return null;
			//No need to check if key exists as returning null
			return c.get(flag.getIndex());
		} else {
			Collection<Object> c = (Collection<Object>)getFlagValue(flag);
			if(c==null) return null;
			if(c.size()<=flag.getIndex()) return null;
			return c.toArray()[flag.getIndex()];
		}
	}

	public boolean isMultiObject(Flag flag) {
		try {
			return (getFlagValue(flag) instanceof Collection) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked") //Know it is a collection from isMultiObject
	public int multiObjectSize(Flag flag) {
		if(isMultiObject(flag))
			try {
				return ((Collection<Object>) getFlagValue(flag)).size();
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		return -1;
	}

	public Object runFunction(Flag flag, Object... args) throws Exception {
		if(getFlags().isInterpretedFunction(flag)) return runInterpretedFunction(flag, args);
		if(getFlags().isBuiltinFunction(flag)) return runBuiltInFunction(flag, args);
		return null;
	}

	@SuppressWarnings("unchecked")
	public void setFlagValue(Flag flag, Object data) {
		if(getFlags().containsFlag(flag)) {
			if(flag.isMultiple()) {
				Collection<Object> c = (Collection<Object>) store.get(flag.getClass());
				if(c==null) {
					store.put(flag.getName(), data);
				} else if(c instanceof Map) {
					((Map)c).put(flag.getIndex(), data);
				} else if(c.size()<flag.getIndex()) {
					//Place data into c at a given position
					//TODO inefficient - is there a better way?
					Object[] array = c.toArray();
					array[flag.getIndex()] = data;
					c.clear();
					c.addAll(Arrays.asList(array));
				} else {
					//TODO deal with index not being c.size (ie. indexing error)
					c.add(data);
				}
			} else {
				store.put(flag.getName(), data);
			}
		}
	}
	
}
