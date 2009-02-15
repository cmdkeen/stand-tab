package uk.ac.stand.testing;

import javax.swing.JFrame;

import uk.ac.stand.gui.MainGUI;

public class GUITest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		@SuppressWarnings("unused")
		DataSetup ds = new DataSetup(3, 2, 16, 2);
					
		invoke();

	}
	
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MainGUI newContentPane = new MainGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        
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
