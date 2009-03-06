package uk.ac.stand.gui;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.table.TableModel;

import uk.ac.stand.export.CSVExport;
import uk.ac.stand.export.Export;
import uk.ac.stand.export.Import;
import uk.ac.stand.export.Pair;
import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Settings;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

@SuppressWarnings("serial")
public class MainGUI extends JFrame implements ActionListener {
	
	JTabbedPane tabbedPane = new JTabbedPane();
    TeamsTab teams = null;
    SettingsTab settings = null;
    SpeakersTab speakers = null;
    DrawTabEnter rounds = null;
    
    JMenuBar menubar;
    JMenu mcompetition, mteams, mspeakers, mdraw, tExport, sExport, dExport;
    JMenuItem buildComp, save, open, tCreate, sCreate;
    
    String[] exportPossibilities = {"CSV - Headers", "CSV - No Header"};
    int[] exportKeyEvents = {KeyEvent.VK_H, KeyEvent.VK_W};

	public MainGUI(String title) {
		super(title); //Construct the JFrame
		JPanel panel = new JPanel(new GridLayout(1, 1));
		
		menubar = new JMenuBar();
		
		
		//Competition menu bar
			mcompetition = new JMenu("Competition");
			mcompetition.getAccessibleContext().setAccessibleDescription("Menu dealing with competition level actions");
		
			buildComp = new JMenuItem("Build competiton");
			if(!Settings.getInstance().setupComplete()) buildComp.setEnabled(false);
			buildComp.getAccessibleContext().setAccessibleDescription("Creates a competition from the given settings");
			buildComp.addActionListener(this);
			buildComp.setActionCommand("buildCompetition");
			buildComp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
				    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			mcompetition.add(buildComp);
			
			save = new JMenuItem("Save");
			save.setEnabled(false);
			save.getAccessibleContext().setAccessibleDescription("Saves the competition to a file");
			save.addActionListener(this);
			save.setActionCommand("saveCompeition");
			save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			mcompetition.add(save);
			
			open = new JMenuItem("Open");
			open.getAccessibleContext().setAccessibleDescription("Opens a competition from a file");
			open.addActionListener(this);
			open.setActionCommand("openCompeition");
			open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			mcompetition.add(open);
			
			menubar.add(mcompetition);
			
		//Export submenu
			tExport = new JMenu("Export");
			tExport.getAccessibleContext().setAccessibleDescription("Exports the data to a file");
			for(int i = 0; i < exportPossibilities.length; i++) {
				JMenuItem mi = new JMenuItem(exportPossibilities[i]);
				mi.setActionCommand(exportPossibilities[i]);
				mi.addActionListener(this);
				mi.setAccelerator(KeyStroke.getKeyStroke(exportKeyEvents[i],
					    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
				tExport.add(mi);
			}
			
		//Teams menu bar
			mteams = new JMenu("Teams");
			mteams.setEnabled(false);
			mteams.getAccessibleContext().setAccessibleDescription("Menu dealing with teams");
			
			tCreate = new JMenuItem("Add team(s)");
			tCreate.getAccessibleContext().setAccessibleDescription("Create new teams");
			tCreate.addActionListener(this);
			tCreate.setActionCommand("createTeam");
			tCreate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			mteams.add(tCreate);
			
				//Export submenu
				tExport = new JMenu("Export");
				tExport.getAccessibleContext().setAccessibleDescription("Exports the data to a file");
				for(int i = 0; i < exportPossibilities.length; i++) {
					JMenuItem mi = new JMenuItem(exportPossibilities[i]);
					mi.setActionCommand('t' + exportPossibilities[i]);
					mi.addActionListener(this);
					mi.setAccelerator(KeyStroke.getKeyStroke(exportKeyEvents[i],
						    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
					tExport.add(mi);
				}
			
			mteams.addSeparator();
			mteams.add(tExport);
			
			menubar.add(mteams);
			
		//Speakers menu bar
			mspeakers = new JMenu("Speakers");
			mspeakers.setEnabled(false);
			mspeakers.getAccessibleContext().setAccessibleDescription("Menu dealing with speakers");
			
			sCreate = new JMenuItem("Add seaker(s)");
			sCreate.getAccessibleContext().setAccessibleDescription("Create new speakers");
			sCreate.addActionListener(this);
			sCreate.setActionCommand("createSpeaker");
			sCreate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			mspeakers.add(sCreate);
			
				//Export submenu
				sExport = new JMenu("Export");
				sExport.getAccessibleContext().setAccessibleDescription("Exports the data to a file");
				for(int i = 0; i < exportPossibilities.length; i++) {
					JMenuItem mi = new JMenuItem(exportPossibilities[i]);
					mi.setActionCommand('s' + exportPossibilities[i]);
					mi.addActionListener(this);
					sExport.add(mi);
				}
			
			mspeakers.addSeparator();
			mspeakers.add(sExport);
			
			menubar.add(mspeakers);
			
		//Draw menu bar
			mdraw = new JMenu("Draws");
			mdraw.setEnabled(false);
			mdraw.getAccessibleContext().setAccessibleDescription("Menu dealing with draws");
			
			//Export submenu
				dExport = new JMenu("Export");
				dExport.getAccessibleContext().setAccessibleDescription("Exports the draw to a file");
				
				for(int i = 0; i < exportPossibilities.length; i++) {
					JMenuItem mi = new JMenuItem(exportPossibilities[i]);
					mi.setActionCommand('d' + exportPossibilities[i]);
					mi.addActionListener(this);
					dExport.add(mi);
				}
				
			mdraw.add(dExport);
			menubar.add(mdraw);
		
		//*** End of menu bar setup
			
		setJMenuBar(menubar);
		
		settings = new SettingsTab(true);
        
        tabbedPane.addTab("Settings", settings);
        
        
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        panel.add(tabbedPane);
        panel.setOpaque(true);
        
        setContentPane(panel);
	}
	
	protected void competitionSetup() {
		if(!Competition.getInstance().isSetupComplete()) Competition.getInstance().setup();
		
		mteams.setEnabled(true);
		mspeakers.setEnabled(true);
		mdraw.setEnabled(true);
		
		teams = new TeamsTab();
	    speakers = new SpeakersTab();
	    rounds = new DrawTabEnter();
	    
	    tabbedPane.removeAll();
		
		tabbedPane.addTab("Teams", teams);
        tabbedPane.addTab("Speakers", speakers);
        tabbedPane.addTab("Rounds", rounds);
        
        tabbedPane.setSelectedComponent(teams);
        
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("saveCompeition")) {save(); return;}
		if(e.getActionCommand().equals("openCompeition")) {load(); return;}
		if(e.getActionCommand().equals("buildCompetition")) {build(); return;}
		
		if(e.getActionCommand().equals("createTeam")) {createTeam(); return;}
		if(e.getActionCommand().equals("createSpeaker")) {createSpeaker(); return;}
			
		for(String s : exportPossibilities) if(s.equals(e.getActionCommand().substring(1))) {
			if(e.getActionCommand().charAt(0)=='t') exportTM(s, teams.table.getTableModel());
			if(e.getActionCommand().charAt(0)=='s') exportTM(s, speakers.table.getTableModel());
			if(e.getActionCommand().charAt(0)=='d') exportTM(s, rounds.dt.getTable().getTableModel());
			//Add in others as needed
		}
		
	}
	
	private void createTeam() {
		Pair<List<ITeam>,List<ISpeaker>> load = null;
		try {
			 load = Import.loadTeams();
			 if(load==null) return;
			 
			 Competition c = Competition.getInstance();
			 
			 for(ITeam t : load.getLeft()) c.addTeam(t);
			 System.out.println("Added " + load.getLeft().size() + " teams");
			 for(ISpeaker s : load.getRight()) c.addSpeaker(s);
			 System.out.println("Added " + load.getRight().size() + " speakers");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
				    "The load failed:\n" + e.getMessage(),
				    "Import error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void createSpeaker() {
		System.out.println("Import speakers not implemented yet");
		JOptionPane.showMessageDialog(this,
			    "Sorry - Importing just speakers is not yet implemented",
			    "Import not implemented",
			    JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void save() {
		try {
			Export.exportCompetition();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
				    "The export failed:\n" + e.getMessage(),
				    "Export error",
				    JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void load() {
		try {
			if(Import.importCompetition()) competitionSetup();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
				    "The import failed:\n" + e.getMessage(),
				    "Import error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void build() {
		if(Settings.getInstance().setupComplete()) {
			competitionSetup();
			
			save.setEnabled(true);
			buildComp.setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(this,
				    "The setup of the settings is not complete",
				    "Settings error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void exportTM(String s, TableModel tm) {
		if(tm==null) {
			JOptionPane.showMessageDialog(this,
				    "The supplied table was null, check it is displaying",
				    "Export error",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}

		//If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
			Export ex = new CSVExport();
			if(s.substring(0,3).equals("CSV")) ex = new CSVExport();
			
			try {
				if(ex==null) return;
				
				if(s.equals(exportPossibilities[0])) ex.export(tm, true);
				if(s.equals(exportPossibilities[1])) ex.export(tm, false);
				
			} catch (IOException e1) {
				e1.printStackTrace();
				System.exit(-1);
			}
		}

		//return value was null
		return;	
		
		
	}

	
}
