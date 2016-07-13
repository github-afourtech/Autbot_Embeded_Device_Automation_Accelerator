package com.autobot.utilities;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import java.util.HashMap;
import org.json.simple.JSONObject;

import com.afour.autobot.embedded.CoordinatesReader;

public class CommonUtilities {
	
	public static Ini mapping=null;
	
	public static Ini config = null;
	
	public static Ini keySequence = null;
	
	public static JSONObject oCRSectionJson = null;
	
	public static HashMap<String, HashMap<String, HashMap<String, String>>> sectionMap = null;
	public static String rootPath = System.getProperty("user.dir");
	
	static {
		try {
			String path = new CommonUtilities().rootPath+"\\src\\";
			mapping = new Ini(new File(path+"\\EmbeddedKeyMapping.ini"));
			config = new Ini(new File(path+"\\config.ini"));
			keySequence = new Ini(new File(path+"\\EmbeddedKeySequenceMapping.ini"));
			
			// read the json file
			CoordinatesReader reader= new CoordinatesReader();
			sectionMap= reader.jsonCoordReader(path+"\\ScreenSections.json");
							
	} catch (InvalidFileFormatException e) {
	// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
	// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	public static void main(String[] args) throws IOException {
		System.out.println(new CommonUtilities().rootPath);
//		System.out.println(new CommonUtilities().path);
  }

}

