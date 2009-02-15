package uk.ac.stand.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import uk.ac.stand.gui.tablemodels.SettingsTableModel;

@SuppressWarnings("serial")
public class SettingsTable extends JPanel {

	private JTable settingstable = null;
	
	
	public SettingsTable() {
		this(false);
	}
	
	public SettingsTable(boolean editable) {
		super(new GridLayout(1,0));
		
		settingstable = new JTable(new SettingsTableModel(editable));
		
		settingstable.setPreferredScrollableViewportSize(new Dimension(500, 400));
		settingstable.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(settingstable);

        //Add the scroll pane to this panel.
        add(scrollPane);
        
        settingstable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //settingstable.getSelectionModel().addListSelectionListener(this);
        
	}
	
	protected TableModel getTableModel() {
		return settingstable.getModel();
	}

	

	
	
}
