package uk.ac.stand.gui.tablemodels;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import uk.ac.stand.impl.Competition;
import uk.ac.stand.interfaces.ISpeaker;

@SuppressWarnings("serial")
public class SpeakerTableModel extends AbstractTableModel {
	
	public Class<?> getColumnClass(int c) {
        Object o = getValueAt(0, c);
        
        if(o==null) return Object.class;
        
		return o.getClass();
    }

	public int getColumnCount() {
		return Competition.getInstance().getSpeakerData().length;
	}

	public String getColumnName(int columnIndex) {
		return Competition.getInstance().getSpeakerData()[columnIndex];
	}

	public int getRowCount() {
		return Competition.getInstance().getSpeakers().size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		ISpeaker speaker = ((ArrayList<ISpeaker>)Competition.getInstance().getSpeakers()).get(rowIndex);
		
		Object flag = speaker.getFlag(Competition.getInstance().getSpeakerData()[columnIndex]);
		
		if(Competition.getInstance().getSpeakerDataMul()[columnIndex]>0) {
			//It is a multi data object so we need to extract that data
			
			//TODO some kind of typing here?
			Collection<?> cflag = (Collection<?>) flag;
			
			return cflag.toArray()[Competition.getInstance().getSpeakerDataMul()[columnIndex] - 1];
		} else {
			return flag;
		}
	}

}
