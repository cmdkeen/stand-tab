package uk.ac.stand.gui.tablemodels;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Draw;
import uk.ac.stand.impl.Position;
import uk.ac.stand.impl.Room;

@SuppressWarnings("serial")
public class DrawTableModelLong extends AbstractTableModel {
	
	private int round;
	private Draw draw;
	
	private String[] colNames = null;;
	
	public DrawTableModelLong(int round) {
		this(round, Competition.getInstance().getDraw(round));
	}
	
	public DrawTableModelLong(int round, Draw draw) {
		this.round = round;
		this.draw = draw;
		
		List<String> cols = new LinkedList<String>();
		cols.add("Room");
		for(Position p : Position.getPositionArray()) cols.add(p.toString());
		
		colNames = cols.toArray(new String[cols.size()]);
	}
	
	public Class<?> getColumnClass(int c) {
        Object o = getValueAt(0, c);
        
        if(o==null) return Object.class;
        
		return o.getClass();
    }

	public int getColumnCount() {
		if(colNames==null) return 0;
		return colNames.length;
	}

	public int getRowCount() {
		if(draw==null) return 0;
		return draw.getRooms().size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if(draw == null || draw.getRooms().size()==0) return null;
				
		Room r = draw.getRooms().get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return r.getRoomName();
			
		default:
			//return Position.getPositionArray()[rowIndex % roomSize];
			return r.getTeam(Position.getPositionArray()[columnIndex-1]);
		}
		
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
