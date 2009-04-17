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

	public CompetitionGUI() {
		settings = new SettingsTab(true);
               
        add(tabbedPane);
        
        tabbedPane.add(settings);
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
