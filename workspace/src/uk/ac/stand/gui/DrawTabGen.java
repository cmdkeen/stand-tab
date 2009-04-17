package uk.ac.stand.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.stand.antlr.DrawFile;
import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Draw;
import uk.ac.stand.testing.Simulation;

@SuppressWarnings("serial")
public class DrawTabGen extends JPanel implements ActionListener, ListSelectionListener {

	private DrawTab dt;
	private JButton generateRound;
	private JButton generateResults;
	
	public DrawTabGen() {
		super(new GridLayout(2,1));
		
		dt = new DrawTab();
		
		add(dt);
		
		JPanel lower = new JPanel();
		
		generateRound = new JButton("Draw Round");
		generateRound.setActionCommand("genRound");
		generateRound.addActionListener(this);
		generateRound.setEnabled(true);
		
		//methods = new JList(FakeDraw.draws);
		//methods.addListSelectionListener(this);
		
		generateResults = new JButton("Generate Results");
		generateResults.setActionCommand("genResults");
		generateResults.addActionListener(this);
		generateResults.setEnabled(true);
		
		//lower.add(methods);
		lower.add(generateRound);
		lower.add(generateResults);
		
		add(lower);
		
		dt.rounds.addListSelectionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("genRound")) {			
			int row = dt.getList().getSelectedIndex();
			//int mrow = methods.getSelectedIndex();
			
			if(row==-1) return;
			
			row++; //Row 0 is for round 1
			
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
			
			//generateRound.setEnabled(false);
			//generateResults.setEnabled(true);
			
		} else if(e.getActionCommand().equals("genResults")) {
			int row = dt.getList().getSelectedIndex();
			
			if(row==-1) return;
			row++;
			
			Simulation sim = new Simulation(123456);
			sim.makeResults(row);
			
			//generateResults.setEnabled(false);
		}
		
	}
	
	protected void testButtons() {
		/*
		System.out.println("o " + dt.rounds.getSelectedIndex());
		if(Competition.getInstance().getTeams().size()==0) return;
		if(dt.rounds.getSelectedIndex()==-1) { //A round is not selected
			generateRound.setEnabled(false);
			generateResults.setEnabled(false);
		} else if(Competition.getInstance().getDraws().containsKey(dt.rounds.getSelectedIndex()+1)) { //A draw for this round has happened
			generateRound.setEnabled(false);
			if(Competition.getInstance().getTeams().get(0).getTeamResult(dt.rounds.getSelectedIndex()+1)==null) {//No results have been entered for this round
				generateResults.setEnabled(true);
			} else {
				generateResults.setEnabled(false);
			}
		} else {
			System.out.println("Test " + dt.rounds.getSelectedIndex());
			//if(Competition.getInstance().getTeams().size()>0 && (dt.rounds.getSelectedIndex()==0||Competition.getInstance().getTeams().get(0).getTeamResult(dt.rounds.getSelectedIndex())!=null)) {
			if(dt.rounds.getSelectedIndex()==0 || Competition.getInstance().getTeams().get(0).getTeamResult(dt.rounds.getSelectedIndex())!=null) {
				//A draw method is selected & the previous round has results entered or the round is the first round
				generateRound.setEnabled(true);
				generateResults.setEnabled(false);
			} else {
				generateRound.setEnabled(false);
				generateResults.setEnabled(false);
			}
		}
		*/
	}

	public void valueChanged(ListSelectionEvent e) {
		testButtons();
	}
	

}
