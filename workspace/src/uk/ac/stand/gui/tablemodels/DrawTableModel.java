package uk.ac.stand.gui.tablemodels;

import javax.swing.table.AbstractTableModel;

import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Draw;
import uk.ac.stand.impl.Position;
import uk.ac.stand.impl.Room;
import uk.ac.stand.impl.Settings;

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
		try {
			return draw.getRooms().size() * (Integer) Settings.getInstance().getFlagValue("teamsPerSide") * 2;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if(draw == null || draw.getRooms().size()==0) return null;
		
		int roomSize;
		try {
			roomSize = (Integer) Settings.getInstance().getFlagValue("teamsPerSide") * 2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
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
