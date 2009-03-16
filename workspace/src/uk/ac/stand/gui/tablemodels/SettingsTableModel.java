package uk.ac.stand.gui.tablemodels;

import javax.swing.table.AbstractTableModel;

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
		return 2;
	}

	public int getRowCount() {
		return Settings.getFlagsStatic().getFields().length;
	}
	
	public String getColumnName(int col) {
		switch(col) {
		case 0: return "Setting";
		case 1: return "Value";
		default: return "Unknown"; //Shouldn't happen
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Flags flags = Settings.getFlagsStatic();
		switch(columnIndex) {
		case 0: return flags.getFields()[rowIndex];
		case 1: try {
					return Settings.getInstance().getFlagValue(flags.getFields()[rowIndex]);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
		default: return null;
		}
	}
	
	public Class<?> getColumnClass(int c) {
        Object o = getValueAt(0, c);
        
        if(o==null) return Object.class;
        
		return o.getClass();
    }

	public boolean isEditable() {
		return editable;
	}
	
	public void setValueAt(Object aValue, int row, int col) {
		try {
			Settings.getInstance().setFlagValue(Settings.getFlagsStatic().getFields()[row], aValue);
		} catch (StoreException e) {
			e.printStackTrace();
			return;
		}
		
		fireTableCellUpdated(row, col);

	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0: return false;
		case 1: 
			if(!editable) return false;
			return true;
		default: return false; //Shouldn't be able to reach
		}
	}

}
