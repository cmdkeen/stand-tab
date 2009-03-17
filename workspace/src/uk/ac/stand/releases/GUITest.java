package uk.ac.stand.releases;

import javax.swing.JFrame;

import uk.ac.stand.gui.MainGUI;
import uk.ac.stand.impl.exceptions.StoreException;
import uk.ac.stand.testing.DataSetup;

public class GUITest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		try {
			@SuppressWarnings("unused")
			DataSetup ds = new DataSetup(3, 2, 16, 2, true);
		} catch (StoreException e) {
			e.printStackTrace();
			System.exit(-1);
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
        JFrame frame = new MainGUI("StAndTab Demo");
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
