package uk.ac.stand.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import uk.ac.stand.impl.Settings;

@SuppressWarnings("serial")
public class SettingsTab extends JPanel implements ActionListener, TableModelListener {
	
	private JButton createCompetition = null;
	private SettingsTable table;
	private MainGUI parent;
	
	public SettingsTab(MainGUI parent) {
		this(false, parent);
	}
	
	public SettingsTab(boolean editable, MainGUI parent) {	
		super(new GridLayout(2,0));
		
		this.parent = parent;
		
		table = new SettingsTable(editable);
		add(table);
		
		if(editable) {
			table.getTableModel().addTableModelListener(this);
			
        	JPanel lower = new JPanel();
        	
        	createCompetition = new JButton("Create Competition");
        	createCompetition.setActionCommand("create");
        	createCompetition.addActionListener(this);
        	
        	lower.add(createCompetition);
        	
        	add(lower);
        }
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("create")) {
			System.out.println("Competition set");
			createCompetition.setEnabled(false);
			parent.competitionSetup();
		}
	}
	
	public void tableChanged(TableModelEvent e) {
		//int row = e.getFirstRow();
        //int column = e.getColumn();
        //TableModel model = (TableModel)e.getSource();
        //String columnName = model.getColumnName(column);
        //Object data = model.getValueAt(row, column);
        /*
		Required.values()[row].setValue(data);
		*/
        
        if(Settings.getInstance().setupComplete()) {
        	createCompetition.setEnabled(true);
        } else {
        	createCompetition.setEnabled(false);
        }
		
	}

}
