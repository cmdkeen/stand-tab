package uk.ac.stand.gui;

import java.awt.GridLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SpeakersTab extends JPanel {
	
	private SpeakersTable table = null;
	
	public SpeakersTab() {
		super(new GridLayout(2,0));
		
		table = new SpeakersTable();
		add(table);
		
		JPanel lower = new JPanel();
		

		 
		add(lower);
	}



}
