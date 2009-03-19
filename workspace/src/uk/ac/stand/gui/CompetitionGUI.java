package uk.ac.stand.gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class CompetitionGUI extends JPanel {
	
	JTabbedPane tabbedPane = new JTabbedPane();
    TeamsTab teams = null;
    SpeakersTab speakers = null;
    DrawTabEnter rounds = null;
    SettingsTab settings = null;

	public CompetitionGUI(String title) {
		settings = new SettingsTab(true);
        
        //tabbedPane.addTab("Settings", settings);
        
        
        //tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        add(settings);
        //panel.setOpaque(true);
        
	}
	
	protected void competitionSetup() {
		
		teams = new TeamsTab();
	    speakers = new SpeakersTab();
	    rounds = new DrawTabEnter();
	    
	    tabbedPane.removeAll();
		
		tabbedPane.addTab("Teams", teams);
        tabbedPane.addTab("Speakers", speakers);
        tabbedPane.addTab("Rounds", rounds);
        
        tabbedPane.setSelectedComponent(teams);
        
	}
	
	

	
}
