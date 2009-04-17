package uk.ac.stand.gui.tablemodels;

import javax.swing.table.AbstractTableModel;

import uk.ac.stand.impl.Flag;
import uk.ac.stand.impl.Flags;
import uk.ac.stand.impl.Settings;
import uk.ac.stand.impl.exceptions.StoreException;

@SuppressWarnings("serial")
public class SettingsTableModel extends AbstractTableModel {
	
	private boolean editable;
	
	public SettingsTableModel() {
		
	}
	
	public SettingsTableModel(boolean editable) {
		this.editable = editable;
	}

	public int getColumnCount() {
		return Settings.getFlagsStatic().getFields().length;
	}

	public int getRowCount() {
		return 1;
	}
	
	public String getColumnName(int col) {
		/*switch(col) {
		case 0: return "Setting";
		case 1: return "Value";
		default: return "Unknown"; //Shouldn't happen
		}*/
		return Settings.getFlagsStatic().getFields()[col].getName();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Flags flags = Settings.getFlagsStatic();
		/*switch(columnIndex) {
		case 0: return flags.getFields()[rowIndex];
		case 1: try {
					return Settings.getInstance().getFlagValue(flags.getFields()[rowIndex]);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
		default: return null;
		}*/
		
		try {
			return Settings.getInstance().getFlagValue(flags.getFields()[columnIndex]);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Class<?> getColumnClass(int c) {
        /*Object o = getValueAt(0, c);
        
        if(o==null) return Object.class;
        
		return o.getClass();*/
		
		return Settings.getFlagsStatic().getFields()[c].getType();
    }

	public boolean isEditable() {
		return editable;
	}
	
	public void setValueAt(Object aValue, int row, int col) {
		/*try {
			Flag flag = Settings.getFlagsStatic().getFields()[row];
			if(!flag.isAcceptable(aValue)) {
				//Perhaps try conversions first?
				try {
					aValue = flag.getType().cast(aValue);
				} catch (ClassCastException e) {
					System.err.println("Couldn't assign value");
					return;
				}
			}
			Settings.getInstance().setFlagValue(flag, aValue);
		} catch (StoreException e) {
			e.printStackTrace();
			return;
		}*/
		
		Flag flag = Settings.getFlagsStatic().getFields()[col];
		if(!flag.isAcceptable(aValue)) {
			//Try conversions first?
			try {
				aValue = flag.getType().cast(aValue);
			} catch (ClassCastException e) {
				System.err.println("Couldn't assign value");
				return;
			}
		}
		
		try {
			Settings.getInstance().setFlagValue(flag, aValue);
		} catch (StoreException e) {
			System.err.println("Couldn't assign value");
			return;
		}
		
		
		fireTableCellUpdated(row, col);

	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		/*switch(columnIndex) {
		case 0: return false;
		case 1: 
			if(!editable) return false;
			return true;
		default: return false; //Shouldn't be able to reach
		}*/
		return editable;
	}

}
