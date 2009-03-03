package uk.ac.stand.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uk.ac.stand.export.CSVExport;
import uk.ac.stand.export.Export;
import uk.ac.stand.testing.DataSetup;

@SuppressWarnings("serial")
public class TeamsTab extends JPanel implements ActionListener {

	private TeamsTable table;
	private JButton addTestData;
	private JButton exportData;

	public TeamsTab() {
		 super(new GridLayout(2,0));
		
		 table = new TeamsTable();
		 add(table);
		 
		 JPanel lower = new JPanel();
		 
		 addTestData = new JButton("Add Test Data");
		 addTestData.setActionCommand("testdata");
		 addTestData.addActionListener(this);
		 
		 exportData = new JButton("Export data");
		 exportData.setActionCommand("exportData");
		 exportData.addActionListener(this);
		 
		 lower.add(addTestData);
		 lower.add(exportData);
		 
		 add(lower);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("testdata")) {
			DataSetup.addTeamsSpeakers(); 
			
			table.getTableModel().fireTableDataChanged();
			
			addTestData.setEnabled(false);
		} else if(e.getActionCommand().equals("exportData")) {
			
			Object[] possibilities = {"CSV - Headers", "CSV - No Header"};
			String s = (String)JOptionPane.showInputDialog(
			                    this,
			                    "Select the type of output:\n",
			                    "Customized Dialog",
			                    JOptionPane.PLAIN_MESSAGE,
			                    null,
			                    possibilities,
			                    "CSV");

			//If a string was returned, say so.
			if ((s != null) && (s.length() > 0)) {
				Export ex = new CSVExport();
				if(s.substring(0,3).equals("CSV")) ex = new CSVExport();
				
				try {
					if(ex==null) return;
					
					if(s.equals(possibilities[0])) ex.export(table.getTableModel(), true);
					if(s.equals(possibilities[1])) ex.export(table.getTableModel(), false);
					
				} catch (IOException e1) {
					e1.printStackTrace();
					System.exit(-1);
				}
			}

			//return value was null
			return;	
			
			
		}
		
	}
	
}
