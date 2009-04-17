package uk.ac.stand.gui;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.table.TableModel;

import org.antlr.runtime.RecognitionException;

import uk.ac.stand.antlr.DrawFile;
import uk.ac.stand.antlr.Rules;
import uk.ac.stand.export.CSVExport;
import uk.ac.stand.export.Export;
import uk.ac.stand.export.Import;
import uk.ac.stand.export.Pair;
import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Settings;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;
import uk.ac.stand.testing.DataSetup;
import uk.ac.stand.testing.Simulation;

@SuppressWarnings("serial")
public class MainGUI extends JFrame implements ActionListener {
	
	JPanel panel = null;
	
    JMenuBar menubar;
    JMenu mCompetition, mTeams, mSpeakers, mdraw, tExport, sExport, dExport, mRules, rulesCompetition, rulesDraw, simulation;
    JMenuItem buildComp, save, open, tCreate, sCreate, simAddAll, simAddResults;
    
    CompetitionGUI cgui = null;
    
    String[] exportPossibilities = {"CSV - Headers", "CSV - No Header"};
    int[] exportKeyEvents = {KeyEvent.VK_H, KeyEvent.VK_W};
    
    class StabFileFilter implements FileFilter {
        public boolean accept(File f) {
            return f.getName().toLowerCase().endsWith(".stab");
        }
    }

	public MainGUI(String title) {
		super(title);
		
		panel = new JPanel(new GridLayout(1, 1));
				
		menubar = new JMenuBar();
		
		//Rules menu bar
			JMenuItem subrule;
			File d = new File("rules/");
			
			mRules = new JMenu("Rules");
			mRules.getAccessibleContext().setAccessibleDescription("Menu dealing with rules for the competition");
			
			//rulesCompetition = new JMenuItem("Set Competition Rules");
			rulesCompetition = new JMenu("Competition rules");
			rulesCompetition.getAccessibleContext().setAccessibleDescription("Loads the rules associated with a competition");
			mRules.add(rulesCompetition);
			
			subrule = new JMenuItem("Other");
			
			subrule.addActionListener(this);
			subrule.setActionCommand("rulesCompetition");
			subrule.getAccessibleContext().setAccessibleDescription("Loads from a user specified file");
			rulesCompetition.add(subrule);
			
			int ifile = 1;
			for(File f : d.listFiles(new StabFileFilter())) {
				subrule = new JMenuItem(f.getName());
				subrule.addActionListener(this); 
				subrule.setActionCommand("rulesCompetition_" + ifile);
				subrule.getAccessibleContext().setAccessibleDescription("Loads this file from the rules folder");
				rulesCompetition.add(subrule);
				ifile++;
			}
			
			//rulesDraw = new JMenuItem("Set Draw Rules");
			rulesDraw = new JMenu("Draw rules");
			rulesDraw.getAccessibleContext().setAccessibleDescription("Sets the rules for the next draw");
			mRules.add(rulesDraw);
			
			subrule = new JMenuItem("Other");
			
			subrule.addActionListener(this);
			subrule.setActionCommand("rulesDraw");
			subrule.getAccessibleContext().setAccessibleDescription("Loads from a user specified file");
			rulesDraw.add(subrule);
			
			ifile = 1;
			for(File f : d.listFiles(new StabFileFilter())) {
				subrule = new JMenuItem(f.getName());
				subrule.addActionListener(this); 
				subrule.setActionCommand("rulesDraw_" + ifile);
				subrule.getAccessibleContext().setAccessibleDescription("Loads this file from the rules folder");
				rulesDraw.add(subrule);
				ifile++;
			}
			menubar.add(mRules);
		
		
		//Competition menu bar
			mCompetition = new JMenu("Competition");
			mCompetition.getAccessibleContext().setAccessibleDescription("Menu dealing with competition level actions");
		
			buildComp = new JMenuItem("Build competiton");
			buildComp.setEnabled(false);
			buildComp.getAccessibleContext().setAccessibleDescription("Creates a competition from the given settings");
			buildComp.addActionListener(this);
			buildComp.setActionCommand("buildCompetition");
			buildComp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
				    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			mCompetition.add(buildComp);
			
			save = new JMenuItem("Save");
			save.setEnabled(false);
			save.getAccessibleContext().setAccessibleDescription("Saves the competition to a file");
			save.addActionListener(this);
			save.setActionCommand("saveCompeition");
			save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			mCompetition.add(save);
			
			open = new JMenuItem("Open");
			open.getAccessibleContext().setAccessibleDescription("Opens a competition from a file");
			open.addActionListener(this);
			open.setActionCommand("openCompeition");
			open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			mCompetition.add(open);
			
			menubar.add(mCompetition);
			
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
			mTeams = new JMenu("Teams");
			mTeams.setEnabled(false);
			mTeams.getAccessibleContext().setAccessibleDescription("Menu dealing with teams");
			
			tCreate = new JMenuItem("Add team(s)");
			tCreate.getAccessibleContext().setAccessibleDescription("Create new teams");
			tCreate.addActionListener(this);
			tCreate.setActionCommand("createTeam");
			tCreate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			mTeams.add(tCreate);
			
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
			
			mTeams.addSeparator();
			mTeams.add(tExport);
			
			menubar.add(mTeams);
			
		//Speakers menu bar
			mSpeakers = new JMenu("Speakers");
			mSpeakers.setEnabled(false);
			mSpeakers.getAccessibleContext().setAccessibleDescription("Menu dealing with speakers");
			
			sCreate = new JMenuItem("Add seaker(s)");
			sCreate.getAccessibleContext().setAccessibleDescription("Create new speakers");
			sCreate.addActionListener(this);
			sCreate.setActionCommand("createSpeaker");
			sCreate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			mSpeakers.add(sCreate);
			
				//Export submenu
				sExport = new JMenu("Export");
				sExport.getAccessibleContext().setAccessibleDescription("Exports the data to a file");
				for(int i = 0; i < exportPossibilities.length; i++) {
					JMenuItem mi = new JMenuItem(exportPossibilities[i]);
					mi.setActionCommand('s' + exportPossibilities[i]);
					mi.addActionListener(this);
					sExport.add(mi);
				}
			
			mSpeakers.addSeparator();
			mSpeakers.add(sExport);
			
			menubar.add(mSpeakers);
			
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
			
		//Simualtion menu bar
			simulation = new JMenu("Simulation");
			simulation.getAccessibleContext().setAccessibleDescription("Provides features for simulating a competition");
			
			simAddAll = new JMenuItem("Add Teams & Speakers");
			simAddAll.setActionCommand("simAddAll");
			simAddAll.addActionListener(this);
			
			simAddResults = new JMenuItem("Simulate Results");
			simAddResults.setActionCommand("addResults");
			simAddResults.addActionListener(this);
			
			simulation.add(simAddAll);
			simulation.add(simAddResults);
			menubar.add(simulation);
			
		
		//*** End of menu bar setup
			
		setJMenuBar(menubar);
		panel.setOpaque(true);
		
        setContentPane(panel);
	}
	
	/**
	 * Use Simulation to add results to the competition
	 */
	private void addResults() {
		Simulation s = new Simulation();
		
		String ret = JOptionPane.showInputDialog("Enter the round to Simulate results for:");
		
		if(ret==null) return;
		
		try {
			s.makeResults(Integer.parseInt(ret));
		} catch(NumberFormatException e) {
			return;
		}
	}
	
	/**
	 * Use Simulation to add in test teams and speakers
	 */
	private void addAll() {
	
		try {
			DataSetup.addTeamsSpeakers();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
				    "Failed to add data:\n" + e.getMessage(),
				    "Simulation error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("addResults")) {addResults(); return;}
		if(e.getActionCommand().equals("simAddAll")) {addAll(); return;}
		
		if(e.getActionCommand().equals("saveCompeition")) {save(); return;}
		if(e.getActionCommand().equals("openCompeition")) {load(); return;}
		if(e.getActionCommand().equals("buildCompetition")) {build(); return;}
		
		if(e.getActionCommand().equals("createTeam")) {createTeam(); return;}
		if(e.getActionCommand().equals("createSpeaker")) {createSpeaker(); return;}
			
		for(String s : exportPossibilities) if(s.equals(e.getActionCommand().substring(1))) {
			if(e.getActionCommand().charAt(0)=='t') exportTM(s, cgui.teams.table.getTableModel());
			if(e.getActionCommand().charAt(0)=='s') exportTM(s, cgui.speakers.table.getTableModel());
			if(e.getActionCommand().charAt(0)=='d') exportTM(s, cgui.rounds.dt.getTable().getTableModel());
			//Add in others as needed
		}
		
		if(e.getActionCommand().equals("rulesDraw")) {try {
			loadDrawRules(Export.getFile("Load rules"));
		} catch (Exception e1) {
			e1.printStackTrace();
		} return;}
		if(e.getActionCommand().equals("rulesCompetition")) {try {
			loadRules(Export.getFile("Load rules"));
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (RecognitionException e2) {
			e2.printStackTrace();
		} return;}
		
		//Known rules in folder are put into the menus - identified by a number after the underscore
		if(e.getActionCommand().contains("rulesDraw_")) {
			String[] command = e.getActionCommand().split("_");
			if(command.length==2) {
				int t = Integer.valueOf(command[1]);
				
				File d = new File("rules/");
				File f = d.listFiles(new StabFileFilter())[t-1]; //menu values indexed from 1
				
				try {
					loadDrawRules(f);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getActionCommand().contains("rulesCompetition_")) {
			String[] command = e.getActionCommand().split("_");
			if(command.length==2) {
				int t = Integer.valueOf(command[1]);
				
				File d = new File("rules/");
				File f = d.listFiles(new StabFileFilter())[t-1]; //menu values indexed from 1
				
				try {
					loadRules(f);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (RecognitionException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	
	private void loadRules(File f) throws IOException, RecognitionException {
		Rules r = new Rules(f);
		
		Competition.getInstance().loadRules(r);
		r.storeDefaultValues();
		
		cgui = new CompetitionGUI();
		add(cgui);
		
		buildComp.setEnabled(true); //Allow user to try and build the competition when ready
		
	}
	
	private void loadDrawRules(File f) throws Exception {
		DrawFile df = new DrawFile(f);
		
		Competition.getInstance().setDrawFile(df);
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
			if(Import.importCompetition()) cgui.competitionSetup();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
				    "The import failed:\n" + e.getMessage(),
				    "Import error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Checks that all the required settings have been provided. If so sets up the competition so it can be run.
	 * The settings check depends on a the rule set that has been loaded
	 */
	private void build() {
		if(Settings.getInstance().setupComplete()) {
			Competition c = Competition.getInstance();
			c.loadDependantRules(c.getSettingsRules());
			
			c.init();
			
			cgui.competitionSetup();
			
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
