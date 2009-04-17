package uk.ac.stand.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import uk.ac.stand.antlr.DrawFile;
import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Draw;
import uk.ac.stand.impl.Position;
import uk.ac.stand.impl.Room;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

@SuppressWarnings("serial")
public class DrawTabEnter extends JPanel implements ActionListener, ListSelectionListener {

	protected DrawTab dt;
	private JButton generateRound;
	private JButton enterResults;
	private EnterResults resultsPanel;
	
	public DrawTabEnter() {
		super(new GridLayout(3,1));
		
		dt = new DrawTab();
		
		dt.getTable().drawTable.getSelectionModel().addListSelectionListener(this);
		
		add(dt);
		
		JPanel lower = new JPanel();
		
		generateRound = new JButton("Draw Round");
		generateRound.setActionCommand("genRound");
		generateRound.addActionListener(this);
		generateRound.setEnabled(false);
			
		enterResults = new JButton("Enter Results");
		enterResults.setActionCommand("enterResults");
		enterResults.addActionListener(this);
		enterResults.setEnabled(false);
		
		lower.add(generateRound);
		lower.add(enterResults);
		
		add(lower);
		
		resultsPanel = new EnterResults(null);
		add(resultsPanel);
		
		dt.rounds.addListSelectionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("genRound")) {			
			int row = dt.getList().getSelectedIndex();
			
			if(row==-1) return;
			
			row++; //Row 0 is for round 1
			
			//FakeDraw fd = new FakeDraw(FakeDraw.draws[mrow]);
			try{
				DrawFile df = Competition.getInstance().getDrawFile();
				
				if(df==null) {
					JOptionPane.showMessageDialog(this,
						    "You need to select a Stab file with a draw algorithm in first",
						    "Draw cancelled",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int round = row;
				Draw d = df.doDraw(round);
				Competition.getInstance().addDraw(round, d);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this,
					    "Error:\n" + ex.getMessage(),
					    "Draw cancelled",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			dt.getTable().getTableModel().setRound(row);
			
			generateRound.setEnabled(false);
			
		} else if(e.getActionCommand().equals("enterResults")) {
			int row = dt.getList().getSelectedIndex();
			
			if(row==-1) return;
			row++;
			
			saveResults();
			
			enterResults(false);
		}
		
	}
	
	private void saveResults() {
		Room r = getRoom();
		Integer[] results = resultsPanel.getResults();
		for(int i : results) System.out.println(i);
		Position[] pa = Position.getPositionArray();
		for(int i = 0; i < pa.length; i++) {
			//Go through each position and add the results
			Position p = pa[i];
			ITeam t = r.getTeams().get(p);
			t.addResult(getRoundNum(), results[i*3]);
			ISpeaker[] sa = t.getSpeakers().toArray(new ISpeaker[2]);
			sa[0].addScore(getRoundNum(), results[i*3+1]);
			sa[1].addScore(getRoundNum(), results[i*3+2]);
		}
	}
	
	private int getRoundNum() {
		return dt.rounds.getSelectedIndex() + 1;
	}
	
	private int getRoomNum() {
		return dt.getTable().drawTable.getSelectedRow();
	}
	
	private Room getRoom() {
		Draw d = getDraw();
		if(d==null) return null;
		return d.getRooms().get(getRoomNum());
	}
	
	private Draw getDraw() {
		return Competition.getInstance().getDraws().get(getRoundNum());
	}

	/**
	 * Runs through the various list selections to decide which buttons should be enabled
	 * Generate round iff previous round (if any) has had results entered
	 * Enter results iff a room selected and results have not already been entered
	 */
	protected void testButtons() {
		int round = getRoundNum();

		if(round==0) { //A round is not selected
			generateRound.setEnabled(false);
			enterResults(false);
		} else {
			Competition c = Competition.getInstance();
			if(c.getDraws().containsKey(round)) { //A draw for this round has happened
				generateRound.setEnabled(false);
				int room = getRoomNum();
				if(room!=-1) {
					Room r = getRoom();
					if(r.resultEntered(round)) { //Have results for this room been entered yet?
						enterResults(false);
					} else {
						enterResults(r);
					}
				}
			} else {
				if(c.getTeams().size()>0 && (round==1 || c.getTeams().get(0).getTeamResult(round-1)!=null)) {
					//The previous round has results entered or the round is the first round
					generateRound.setEnabled(true);
					enterResults(false);
				} else {
					generateRound.setEnabled(false);
					enterResults(false);
				}
			}
		}
	}
	
	private void enterResults(boolean enable) {
		enterResults.setEnabled(enable);
	}
	
	private void enterResults(Room r) {
		enterResults.setEnabled(true);
		resultsPanel.setRoom(r);
	}

	public void valueChanged(ListSelectionEvent e) {
	
		testButtons();
		
	}
	
	class EnterResults extends JPanel {
		JTable table;
		EnterResultsModel model;
		
		public EnterResults(Room r) {
			super(new GridLayout(1,0));
			
			model = new EnterResultsModel(r);
			
			table = new EnterResultsTable(model);
			
			table.setPreferredScrollableViewportSize(new Dimension(500, 400));
			table.setFillsViewportHeight(true);
			
	        //Create the scroll pane and add the table to it.
	        JScrollPane scrollPane = new JScrollPane(table);
	        
	        add(scrollPane);
		}
		
		public Integer[] getResults() {
			return model.results;
		}
		
		public void setRoom(Room r) {
			model.setRoom(r);
		}
		
	}
	
	class EnterResultsTable extends JTable {
		
		public EnterResultsTable(DefaultTableModel tm) {
			super(tm);
			
			setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			setCellSelectionEnabled(true);  
		}
		
	}
	
	class EnterResultsModel extends DefaultTableModel {
		private String[] headers = new String[12];
		protected Integer[] results = new Integer[12];
		
		private int rows, cols;
		
		public EnterResultsModel(Room r) {
			
			setRoom(r);
			
		}
		
		public void setRoom(Room r) {
			if(r==null) {
				for(int i = 0; i < 12; i++) {
					headers[i] = null;
					results[i] = null;
					rows = 0;
					cols = 0;
				}
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				Position p = Position.getPositionArray()[i];
				ITeam t = r.getTeam(p);
				headers[i * 3] = t.toString();
				headers[i*3+1] = t.getSpeakers().toArray()[0].toString();
				headers[i*3+2] = t.getSpeakers().toArray()[1].toString();
			}
			
			for(int i = 0; i < 12; i++) results[i] = new Integer(0);
			
			rows = 1;
			cols = 12;
			
			fireTableStructureChanged();
		}
		
		public int getRowCount() {
			return rows;
		}
		
		public int getColumnCount() {
			return cols;
		}
		
		public String getColumnName(int col) {
			return headers[col];
		}
		
		public boolean isCellEditable(int row, int col) {
			return true;
		}
		
		public Object getValueAt(int row, int column) {
			return results[column];
		}
		
		public void setValueAt(Object value, int row, int column) {
			results[column] = (Integer) value;
		}
		
		@SuppressWarnings("unchecked")
		public Class getColumnClass(int col) {
			return Integer.class;
		}
	}
	

}
