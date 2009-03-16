package uk.ac.stand.gui;

import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.stand.impl.Settings;

@SuppressWarnings("serial")
public class DrawTab extends JPanel implements ListSelectionListener {

	private DrawTable table = null;
	protected JList rounds = null;
	
	public DrawTab() {
		super(new GridLayout(1,2));
		
		try {
			rounds = new JList(genRoundsArray());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		rounds.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		add(rounds);
		
		rounds.addListSelectionListener(this);
		
		table = new DrawTable();
		
		add(table);
	}
	
	private Integer[] genRoundsArray() throws Exception {
		Integer numRounds = (Integer) Settings.getInstance().getFlagValue("numRounds");
		
		Integer[] ret = new Integer[numRounds];
		
		
		for(int i = 0; i < numRounds; i++) {
			ret[i] = i+1;
		}
		
		return ret;
	}

	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource()==rounds && rounds.getSelectedIndex()!=-1) {
			table.setRound(rounds.getSelectedIndex()+1);
		}
	}
	
	protected DrawTable getTable() {
		return table;
	}
	
	protected JList getList() {
		return rounds;
	}
	
}
