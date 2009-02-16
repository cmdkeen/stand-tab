package uk.ac.stand.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import uk.ac.stand.antlr.FakeDraw;
import uk.ac.stand.impl.Competition;
import uk.ac.stand.testing.Simulation;

@SuppressWarnings("serial")
public class DrawTabGen extends JPanel implements ActionListener {

	DrawTab dt;
	private JList methods;
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
		
		methods = new JList(FakeDraw.draws);
		
		generateResults = new JButton("Generate Results");
		generateResults.setActionCommand("genResults");
		generateResults.addActionListener(this);
		
		lower.add(methods);
		lower.add(generateRound);
		lower.add(generateResults);
		
		add(lower);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("genRound")) {
			
			
			
			//int row = dt.getTable().drawTable.getSelectedRow();
			
			int row = dt.getList().getSelectedIndex();
			int mrow = methods.getSelectedIndex();
			
			if(row==-1||mrow==-1) return;
			
			row++; //Row 0 is for round 1
			
			//Competition.getInstance().addDraw(row, DataSetup.genDraw(row));
			FakeDraw fd = new FakeDraw();
			Competition.getInstance().addDraw(row, fd.doDraw(row, FakeDraw.draws[mrow]));
			
			dt.getTable().getTableModel().setRound(row);
			
		} else if(e.getActionCommand().equals("genResults")) {
			int row = dt.getList().getSelectedIndex();
			
			if(row==-1) return;
			row++;
			
			Simulation sim = new Simulation(123456);
			sim.makeResults(row);
		}
		
	}
	

}
