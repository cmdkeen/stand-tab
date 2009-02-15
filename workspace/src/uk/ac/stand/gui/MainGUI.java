package uk.ac.stand.gui;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class MainGUI extends JPanel{

	public MainGUI() {
		super(new GridLayout(1, 1));
        
        JTabbedPane tabbedPane = new JTabbedPane();
        JComponent teams = new TeamsTab();
        JComponent settings = new SettingsTab(true);
        JComponent speakers = new SpeakersTab();
        JComponent rounds = new DrawTabGen();
        
        tabbedPane.addTab("Settings", settings);
        tabbedPane.addTab("Teams", teams);
        tabbedPane.addTab("Speakers", speakers);
        tabbedPane.addTab("Rounds", rounds);
        
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        add(tabbedPane);

	}
	
	
}
