/**
 * 
 */
package com.afour.autobot.embedded;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

/**
 * @author kailas.p
 *
 */
public class EmbeddedInterface {
		
	public int getGPIOPin(String buttonName, Ini mapping) throws InvalidFileFormatException, IOException{
		int pin=0;
		
		pin= Integer.parseInt(mapping.get("All",buttonName));
		return pin;		
	}

	public int getCharacterSequence(String buttonName, Ini mapping) throws InvalidFileFormatException, IOException{
		int pin=0;
		
		pin= Integer.parseInt(mapping.get("sequence",buttonName));
		return pin;
	}
	
}
