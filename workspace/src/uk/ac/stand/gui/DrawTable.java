package uk.ac.stand.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.stand.gui.tablemodels.DrawTableModel;

@SuppressWarnings("serial")
public class DrawTable extends JPanel implements ListSelectionListener {

	JTable drawTable = null;
	private DrawTableModel dm = null;
	
	public DrawTable(int round) {
		this();
		setRound(round);
	}
	
	public DrawTable() {
		super(new GridLayout(1,0));
		
		dm = new DrawTableModel(-1);
		drawTable = new JTable(dm);
		
		drawTable.setPreferredScrollableViewportSize(new Dimension(500, 400));
		drawTable.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(drawTable);

        //Add the scroll pane to this panel.
        add(scrollPane);
        
        drawTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        drawTable.getSelectionModel().addListSelectionListener(this);
	}
	
	public void setRound(int round) {
		dm.setRound(round);
	}

	public void valueChanged(ListSelectionEvent e) {
		
	}
	
	protected DrawTableModel getTableModel() {
		return dm;
	}
	
}
