package uk.ac.stand.gui;

import java.awt.GridLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SpeakersTab extends JPanel {
	
	protected SpeakersTable table = null;
	
	public SpeakersTab() {
		super(new GridLayout(1,0));
		
		table = new SpeakersTable();
		add(table);
		
	}



}
