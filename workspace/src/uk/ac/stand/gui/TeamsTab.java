package uk.ac.stand.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import uk.ac.stand.testing.DataSetup;

@SuppressWarnings("serial")
public class TeamsTab extends JPanel implements ActionListener {

	protected TeamsTable table;
	//private JButton addTestData;

	public TeamsTab() {
		 super(new GridLayout(1,0));
		
		 table = new TeamsTable();
		 add(table);
		 
		 //JPanel lower = new JPanel();
		 
		 /*
		 addTestData = new JButton("Add Test Data");
		 addTestData.setActionCommand("testdata");
		 addTestData.addActionListener(this);
		 */
		 
		 
		 //lower.add(addTestData);
		 
		 //add(lower);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("testdata")) {
			try {
				DataSetup.addTeamsSpeakers();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			table.getTableModel().fireTableDataChanged();
			
			//addTestData.setEnabled(false);
		} else if(e.getActionCommand().equals("exportData")) {
			
		}
		
	}
	
}
