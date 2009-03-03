package uk.ac.stand.export;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.table.TableModel;

public abstract class Export {
	
	protected File file;
	
	public abstract void export(TableModel data, boolean header) throws IOException;

	public File getFile() {
		String wd = System.getProperty("user.dir");
		JFileChooser fc = new JFileChooser(wd);
		int rc = fc.showDialog(null, "Save location");
		if (rc == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
		} else {
			return null; //Cancel clicked
		}
		
		return file;
	}
	
}
