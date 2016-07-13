package com.afour.autobot.embedded;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CoordinatesReader {

	public HashMap<String, HashMap<String, HashMap<String, String>>> jsonCoordReader(String filepath)
	{    
   		HashMap<String, HashMap<String, HashMap<String, String>>> screens = new HashMap<String, HashMap<String, HashMap<String, String>>>();
		
   		HashMap<String, HashMap<String, String>> sections = null;	
   		
   		HashMap<String, String> sectionCoords = null;	
    	
   		try 
    	{
    		String screenName = null;
    		String sectionName = null;
    		String coordinate = null;
    		String coordinateValue = null;
    		
    		FileReader reader = new FileReader(filepath);
			JSONParser parser = new JSONParser();
			
			JSONObject jsonScreenObject = (JSONObject) parser.parse(reader);
			Set<String> screenKeySet =jsonScreenObject.keySet();
			for(String key : screenKeySet)
			{	
				screenName = key;
				
				JSONArray jsonArray = (JSONArray) jsonScreenObject.get(screenName);
				sections = new HashMap<String, HashMap<String, String>>();
				for(int i=0;i<jsonArray.size();i++)
				{
					JSONObject jsonSectionObject = (JSONObject) jsonArray.get(i);
					Set<String> sectionKeySet = jsonSectionObject.keySet();
					for(String sectionKey : sectionKeySet)
					{
						sectionName = sectionKey;
						
						JSONArray jsonInnerArray = (JSONArray) jsonSectionObject.get(sectionName);
						sectionCoords = new HashMap<String, String>();
						for(int j=0;j<jsonInnerArray.size();j++)
						{
							JSONObject jsonCoordsObject = (JSONObject) jsonInnerArray.get(j);
							Set<String> coordsKeySet = jsonCoordsObject.keySet();
							for(String coordKey : coordsKeySet)
							{
								coordinate = coordKey; 
								coordinateValue = (String) jsonCoordsObject.get(coordinate);
								sectionCoords.put(coordinate, coordinateValue);
							}
						}
						sections.put(sectionName, sectionCoords);	
					}	
				}	
				screens.put(screenName, sections);
			}	
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
   		return screens;
    }
}
