package uk.ac.stand.releases;

import javax.swing.JFrame;

import uk.ac.stand.gui.MainGUI;

public class FinalDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		if(System.getProperty("os.name").equals("Mac OS X")) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name","StAnd Tab");
		}
		
		invoke();
	}
	
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new MainGUI("StAndTab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void invoke() {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
