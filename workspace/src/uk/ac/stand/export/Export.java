package uk.ac.stand.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.table.TableModel;

import uk.ac.stand.impl.Competition;

public abstract class Export {
	
	public abstract void export(TableModel data, boolean header) throws IOException;
	
	public void export(TableModel data) throws IOException {export(data, false);}
	
	/**
	 * Saves the state of the entire competition to a file
	 * 
	 * @throws IOException
	 */
	public static void exportCompetition() throws Exception {
		File f = getFile("Save location");
		if(f!=null) exportCompetition(f);
	}
	
	/**
	 * Saves the state of the competition to a file
	 * 
	 * @param fileName the name of the file to save to
	 * @throws IOException
	 */
	public static void exportCompetition(String directories, String fileName) throws Exception {
		if(fileName==null) return;
		File d = new File(directories);
		d.mkdirs(); //Create any nescessary directories
		File f = new File(directories + fileName);
		if(f!=null) exportCompetition(f);
	}
	
	/**
	 * Saves the state of the competition to a file
	 * 
	 * @param file the file to save to
	 * @throws IOException
	 */
	private static void exportCompetition(File file) throws Exception {
		
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(Competition.getInstance());
		oos.close();
	}

	public static File getFile(String approveText) {
		File file = null;
		String wd = System.getProperty("user.dir");
		JFileChooser fc = new JFileChooser(wd);
		int rc = fc.showDialog(null, approveText);
		if (rc == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
		} else {
			return null; //Cancel clicked
		}
		
		return file;
	}
	
}
