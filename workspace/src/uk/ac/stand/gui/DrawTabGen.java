package uk.ac.stand.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import uk.ac.stand.impl.Competition;
import uk.ac.stand.testing.DataSetup;

@SuppressWarnings("serial")
public class DrawTabGen extends JPanel implements ActionListener {

	DrawTab dt;
	private JButton generateRound;
	
	public DrawTabGen() {
		super(new GridLayout(2,1));
		
		dt = new DrawTab();
		
		add(dt);
		
		generateRound = new JButton("Draw Round");
		generateRound.setActionCommand("genRound");
		generateRound.addActionListener(this);
		
		add(generateRound);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("genRound")) {
			
			
			
			//int row = dt.getTable().drawTable.getSelectedRow();
			
			int row = dt.getList().getSelectedIndex();
			
			if(row==-1) return;
			
			row++; //Row 0 is for round 1
			
			Competition.getInstance().addDraw(row, DataSetup.genDraw(row));
			
			dt.getTable().getTableModel().setRound(row);
			
		}
		
	}
	

}
