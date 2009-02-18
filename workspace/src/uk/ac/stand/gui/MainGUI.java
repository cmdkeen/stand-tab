package uk.ac.stand.gui;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class MainGUI extends JPanel{
	
	JTabbedPane tabbedPane = new JTabbedPane();
    JComponent teams = new TeamsTab();
    JComponent settings = new SettingsTab(true, this);
    JComponent speakers = new SpeakersTab();
    JComponent rounds = new DrawTabGen();

	public MainGUI() {
		super(new GridLayout(1, 1));
        
        tabbedPane.addTab("Settings", settings);
        
        
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        add(tabbedPane);

	}
	
	protected void competitionSetup() {
		tabbedPane.addTab("Teams", teams);
        tabbedPane.addTab("Speakers", speakers);
        tabbedPane.addTab("Rounds", rounds);
        
        tabbedPane.setSelectedComponent(teams);
        
        tabbedPane.remove(settings);
	}
	
	
}
