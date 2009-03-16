package uk.ac.stand.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import uk.ac.stand.impl.exceptions.StoreException;
import uk.ac.stand.interfaces.IFlagUser;

public abstract class FlagUser implements IFlagUser, Serializable {
	
	private static final long serialVersionUID = 50320091L;
	
	public Map<String, Object> indvStore;
	public Map<String, Map<Integer, Object>> multStore;
	
	public FlagUser() {
		indvStore = new HashMap<String, Object>();
		multStore = new HashMap<String, Map<Integer, Object>>();
	}

	public Object getFlagValue(Flag flag) throws Exception {
		if(getFlags().isFunction(flag)) {
			return runFunction(flag, (Object[]) null);
		}
		if(flag.isMultiple()) {
			//return multStore.get(flag.getName());
			return getSubObject(flag);
		} else {
			return indvStore.get(flag.getName());
		}
	}
	
	public Object getSubObject(Flag flag) throws Exception {
		if(!(flag instanceof MultFlag))	return null;
		//Index check important as default index value is -1
		//Previously checked size and index, but due to handling maps wasn't working so done later
		if(flag.getIndex() < 0)	return null;

		//Map<Integer, Object> c = (Map<Integer, Object>)getFlagValue(flag);
		Map<Integer, Object> m = multStore.get(flag.getName());
		if(m==null) return null;
		//No need to check if key exists as returning null
		return m.get(flag.getIndex());

			/*Collection<Object> c = (Collection<Object>)getFlagValue(flag);
			if(c==null) return null;
			if(c.size()<=flag.getIndex()) return null;
			return c.toArray()[flag.getIndex()];
			*/

	}

	public boolean isMultiObject(Flag flag) {
		try {
			return (getFlagValue(flag) instanceof Map) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int multiObjectSize(Flag flag) {
		if(isMultiObject(flag))
			try {
				//return ((Collection<Object>) getFlagValue(flag)).size();
				return multStore.get(flag.getName()).size();
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

	public void setFlagValue(Flag flag, Object data) throws StoreException {
		if(getFlags().containsFlag(flag)) {
			if(flag.isMultiple()) {
				//Collection<Object> c = (Collection<Object>) store.get(flag.getName());
				Map<Integer, Object> m = multStore.get(flag.getName());
				if(m==null) { //Create the multiple mapping
					Map<Integer, Object> n = new HashMap<Integer, Object>();
					n.put(flag.getIndex(), data);
					multStore.put(flag.getName(), n);
				} else if(m instanceof Map) {
					m.put(flag.getIndex(), data);
				} /*else if(c.size()<flag.getIndex()) {
					//Place data into c at a given position
					//TODO inefficient - is there a better way?
					Object[] array = c.toArray();
					array[flag.getIndex()] = data;
					c.clear();
					c.addAll(Arrays.asList(array));
				}*/ else {
						throw new StoreException("Stored flag not null or a Map");
				}
			
			} else {
				indvStore.put(flag.getName(), data);
			}
		}
	}
	
}
