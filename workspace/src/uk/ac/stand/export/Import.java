package uk.ac.stand.export;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Speaker;
import uk.ac.stand.impl.Team;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

public class Import {
	
	public static Object importObject(String file) throws Exception {
		File f = new File(file);
		if(f!=null) return importObject(f);
		return null;
	}
	
	public static Object importObject() throws Exception {
		File f = Export.getFile("Load file");
		if(f!=null) return importObject(f);
		return null;
	}
	
	public static Object importObject(File file) throws Exception {
		if(!file.exists()) return null;
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Object o = ois.readObject();
		
		ois.close();
		fis.close();
		
		return o;
	}
	
	public static boolean importCompetition() throws Exception {
		Competition o = (Competition)importObject();
		if(o==null) return false;
		if(o != null) {
			Competition.setInstance(o);
			return true;
		} else {
			throw new Exception("File did not contain a Competition");
		}
	}
	
	public static Pair<List<ITeam>, List<ISpeaker>> loadTeams() throws Exception {
		File f = Export.getFile("Load file");
		if(f==null) return null; //Cancelled get file dialogue box
		
		List<ITeam> lt = new LinkedList<ITeam>();
		List<ISpeaker> ls = new LinkedList<ISpeaker>();
		
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		
		String l = br.readLine();
		
		if(l.indexOf("TeamName")!=-1) l = br.readLine(); //Skip header line if exists
		while(l!=null) {
			String[] split = l.split(",");
			if(split.length!=4) throw new Exception("Unexpected number of items in line: " + l);
			
			ITeam t = new Team();
			t.setFlagValue(Team.getFlagsStatic().getFlagFromString("TeamName"), split[0]);
			t.setFlagValue(Team.getFlagsStatic().getFlagFromString("Institution"), split[1]);
			
			lt.add(t);
			
			ISpeaker s1 = new Speaker(t), s2  = new Speaker(t);			
			s1.setFlagValue(Speaker.getFlagsStatic().getFlagFromString("SpeakerName"), split[2]);
			s2.setFlagValue(Speaker.getFlagsStatic().getFlagFromString("SpeakerName"), split[3]); 
			
			ls.add(s1);
			ls.add(s2);
			
			l = br.readLine();
		}
		
		
		Pair<List<ITeam>, List<ISpeaker>> p = new Pair<List<ITeam>, List<ISpeaker>>(lt, ls);
		
		return p;
	}
	

}
