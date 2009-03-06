package uk.ac.stand.gui;

import java.awt.GridLayout;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class SettingsTab extends JPanel {
	
	private SettingsTable table;
	
	public SettingsTab() {
		this(false);
	}
	
	public SettingsTab(boolean editable) {	
		super(new GridLayout(1,0));
			
		table = new SettingsTable(editable);
		add(table);
		
	}

}
