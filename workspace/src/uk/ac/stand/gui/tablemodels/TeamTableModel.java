package uk.ac.stand.gui.tablemodels;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import uk.ac.stand.impl.Competition;
import uk.ac.stand.interfaces.ITeam;

/**
 * @author chris
 * Displays data about teams in a swing table
 */
@SuppressWarnings("serial")
public class TeamTableModel extends AbstractTableModel  {

	public TeamTableModel() {

	}
	
	public int getColumnCount() {
		return Competition.getInstance().getTeamData().length;
	}

	public int getRowCount() {
		return Competition.getInstance().getTeams().size();
	}
	
	public String getColumnName(int col) {
		return Competition.getInstance().getTeamData()[col];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {		
		ITeam team = ((ArrayList<ITeam>)Competition.getInstance().getTeams()).get(rowIndex);
		
		Object flag = team.getFlag(Competition.getInstance().getTeamData()[columnIndex]);
		
		if(Competition.getInstance().getTeamDataMul()[columnIndex]>0) {
			//It is a multi data object so we need to extract that data
			
			//TODO some kind of typing here?
			Collection<?> cflag = (Collection<?>) flag;
			
			return cflag.toArray()[Competition.getInstance().getTeamDataMul()[columnIndex] - 1];
		} else {
			return flag;
		}
	}
	
	public Class<?> getColumnClass(int c) {
        Object o = getValueAt(0, c);
        
        if(o==null) return Object.class;
        
		return o.getClass();
    }

}
