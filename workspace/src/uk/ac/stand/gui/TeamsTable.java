package uk.ac.stand.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.stand.gui.tablemodels.TeamTableModel;

import java.awt.Dimension;
import java.awt.GridLayout;


@SuppressWarnings("serial")
public class TeamsTable extends JPanel implements ListSelectionListener {
	
	private JTable teamtable = null;
	private TeamTableModel tm = null;

    public TeamsTable() {
        super(new GridLayout(1,0));

        tm = new TeamTableModel();
        
		teamtable = new JTable(tm);
		teamtable.setAutoCreateRowSorter(true);
        teamtable.setPreferredScrollableViewportSize(new Dimension(500, 400));
        teamtable.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(teamtable);

        //Add the scroll pane to this panel.
        add(scrollPane);
        
        teamtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teamtable.getSelectionModel().addListSelectionListener(this);
        
        
    }

	public void valueChanged(ListSelectionEvent e) {
		/*
		int row = teamtable.getSelectionModel().getLeadSelectionIndex();
		int col = teamtable.getColumnModel().getSelectionModel().getLeadSelectionIndex();
		 */
	}

	protected TeamTableModel getTableModel() {
		return tm;
	}
	
}
