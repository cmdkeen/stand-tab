package uk.ac.stand.gui.tablemodels;

import javax.swing.table.AbstractTableModel;

import uk.ac.stand.enums.Required;

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
		return Required.values().length;
	}
	
	public String getColumnName(int col) {
		switch(col) {
		case 0: return "Setting";
		case 1: return "Value";
		default: return "Unknown"; //Shouldn't happen
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0: return Required.values()[rowIndex].getText();
		case 1: return Required.values()[rowIndex].getValue();
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
		Required.values()[row].setValue(aValue);
		
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
