package uk.ac.stand.gui.tablemodels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Flag;
import uk.ac.stand.impl.Team;
import uk.ac.stand.interfaces.ITeam;

/**
 * @author chris
 * Displays data about teams in a swing table
 */
@SuppressWarnings("serial")
public class TeamTableModel extends AbstractTableModel  {

	public int getColumnCount() {
		if(Team.getFlagsStatic()==null) return 0;
		return Team.getFlagsStatic().getFlags().length;
	}

	public int getRowCount() {
		return Competition.getInstance().getTeams().size();
	}
	
	public String getColumnName(int columnIndex) {
		return Team.getFlagsStatic().getFlags()[columnIndex].toString();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {		
		ITeam team = ((ArrayList<ITeam>)Competition.getInstance().getTeams()).get(rowIndex);
		
		Flag f = Team.getFlagsStatic().getFlags()[columnIndex];
		
		try {
			if(f.isMultiple()) {
				//It is a multi data object so we need to extract that data
				return team.getSubObject(f);
			} else {
				return team.getFlagValue(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Class<?> getColumnClass(int c) {
        Object o = getValueAt(0, c);
        
        if(o==null) return Object.class;
        
		return o.getClass();
    }

}
