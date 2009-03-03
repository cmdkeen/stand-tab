package uk.ac.stand.export;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.table.TableModel;

public class CSVExport extends Export {
	
	

	@Override
	public void export(TableModel data, boolean header) throws IOException {
		
		FileOutputStream fos = new FileOutputStream(super.getFile());
		DataOutputStream dos = new DataOutputStream(fos);
		
		//dos.writeChars(text);
		
		if(header) {
			for (int i = 0; i < data.getColumnCount(); i++) {
				dos.writeChars(data.getColumnName(i)+",");
			}
			dos.writeChars("\n");
		}
		
		for(int j = 0; j < data.getRowCount(); j++) {
			for(int i = 0; i < data.getColumnCount(); i++) {
				dos.writeChars(data.getValueAt(j, i)+ ",");
			}
			dos.writeChars("\n");
		}
		
		dos.close();
		fos.close();

	}

}
