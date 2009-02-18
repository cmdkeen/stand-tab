package uk.ac.stand.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.stand.gui.tablemodels.SpeakerTableModel;

@SuppressWarnings("serial")
public class SpeakersTable extends JPanel implements ListSelectionListener {

	private JTable speakertable = null;
	private SpeakerTableModel sm = null;
	
	public SpeakersTable() {
		super(new GridLayout(1,0));
		
		sm = new SpeakerTableModel();
		
		speakertable = new JTable(sm);
		speakertable.setAutoCreateRowSorter(true);
		speakertable.setPreferredScrollableViewportSize(new Dimension(500, 400));
		speakertable.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(speakertable);

        //Add the scroll pane to this panel.
        add(scrollPane);
        
        speakertable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        speakertable.getSelectionModel().addListSelectionListener(this);
	}
	
	public void valueChanged(ListSelectionEvent e) {
		/*
		int row = speakertable.getSelectionModel().getLeadSelectionIndex();
		int col = speakertable.getColumnModel().getSelectionModel().getLeadSelectionIndex();
		 */
	}
	
	protected SpeakerTableModel getTableModel() {
		return sm;
	}

}
