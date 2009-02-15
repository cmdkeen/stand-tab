package uk.ac.stand.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SpeakersTab extends JPanel implements ActionListener {
	
	private SpeakersTable table = null;
	private JButton b1 = null;
	
	public SpeakersTab() {
		super(new GridLayout(2,0));
		
		table = new SpeakersTable();
		add(table);
		
		JPanel lower = new JPanel();
		
		b1 = new JButton("Button 1");
		b1.setActionCommand("b1");
		b1.addActionListener(this);
		
		lower.add(b1);
		 
		add(lower);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("b1")) {
			b1.setEnabled(false);
		}
		
	}

}
