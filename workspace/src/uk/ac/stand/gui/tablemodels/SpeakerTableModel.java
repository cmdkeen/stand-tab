package uk.ac.stand.gui.tablemodels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Flag;
import uk.ac.stand.impl.Speaker;
import uk.ac.stand.interfaces.ISpeaker;

@SuppressWarnings("serial")
public class SpeakerTableModel extends AbstractTableModel {
	
	public Class<?> getColumnClass(int c) {
        Object o = getValueAt(0, c);
        
        if(o==null) return Object.class;
        
		return o.getClass();
    }

	public int getColumnCount() {
		if(Speaker.getFlagsStatic()==null) return 0;
		return Speaker.getFlagsStatic().getFlags().length;
	}

	public String getColumnName(int columnIndex) {
		return Speaker.getFlagsStatic().getFlags()[columnIndex].toString();
	}

	public int getRowCount() {
		return Competition.getInstance().getSpeakers().size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		ISpeaker speaker = ((ArrayList<ISpeaker>)Competition.getInstance().getSpeakers()).get(rowIndex);
		
		Flag f = Speaker.getFlagsStatic().getFlags()[columnIndex];
		
		try {
		if(f.isMultiple()) {
			//It is a multi data object so we need to extract that data
			return speaker.getSubObject(f);
		} else {
			return speaker.getFlagValue(f);
		}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
