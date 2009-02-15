package uk.ac.stand.gui.tablemodels;

import javax.swing.table.AbstractTableModel;

import uk.ac.stand.enums.Required;
import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Draw;
import uk.ac.stand.impl.Position;
import uk.ac.stand.impl.Room;

@SuppressWarnings("serial")
public class DrawTableModel extends AbstractTableModel {
	
	private int round;
	private Draw draw;
	
	private String[] colNames = {"Room","Position","Team"};
	
	public DrawTableModel(int round) {
		this(round, Competition.getInstance().getDraw(round));
	}
	
	public DrawTableModel(int round, Draw draw) {
		this.round = round;
		this.draw = draw;
	}
	
	public Class<?> getColumnClass(int c) {
        Object o = getValueAt(0, c);
        
        if(o==null) return Object.class;
        
		return o.getClass();
    }

	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		if(draw==null) return 0;
		return draw.getRooms().size() * (Integer)Required.TEAMS_PER_SIDE.getValue() * 2;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if(draw == null || draw.getRooms().size()==0) return null;
		
		int roomSize = (Integer)Required.TEAMS_PER_SIDE.getValue() * 2;
		
		Room r = draw.getRooms().get(rowIndex/roomSize);
		
		switch (columnIndex) {
		case 0:
			return r.getRoomName();
			
		case 1:
			return Position.getPositionArray()[rowIndex % roomSize];
			
		case 2:
			return r.getTeam(Position.getPositionArray()[rowIndex % roomSize]);
		}
		
		return null;
	}
	
	public String getColumnName(int columnIndex) {
		return colNames[columnIndex];
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
	
		this.round = round;
		this.draw = Competition.getInstance().getDraw(round);
		
		this.fireTableDataChanged();
	}

}
