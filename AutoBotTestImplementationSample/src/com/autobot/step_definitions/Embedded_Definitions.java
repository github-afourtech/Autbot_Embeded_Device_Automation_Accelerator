package com.autobot.step_definitions;

import com.afour.autobot.embedded.*;
import com.autobot.utilities.CommonUtilities;
import static org.junit.Assert.*;
import java.io.File;
import com.jcraft.jsch.Session;

import cucumber.api.java.en.Given;

public class Embedded_Definitions {
	
	static Session ssh_session=null;
	public static SSHCommandExecutor ssh_class=null;
	public static EmbeddedInterface embedded_handler=null;
	public static String command=null;

	static
	{		
		ssh_class=new SSHCommandExecutor(new CommonUtilities().config);
		embedded_handler=new EmbeddedInterface();
		ssh_session=SSHCommandExecutor.connectSSH(new CommonUtilities().config);
		command="bash keypressTrigger.sh";
	}
	
	@Given("^I delete temparary OCR file$")
	public void deleteCropedPng(){
		File f = new File("src/Croped.png");
         
         // tries to delete a non-existing file
         Boolean bool = f.delete();
         
         // prints
        System.out.println("File deleted: "+bool);
	}

	@Given("^press key \"([^\"]*)\" for \"([^\"]*)\" seconds$")
	public void keyPressEmbedded(String arg1,String arg2) throws Throwable{
		int pin=embedded_handler.getGPIOPin(arg1,new CommonUtilities().mapping);
//		System.out.println("pin name: "+ Integer.toString(pin));
		String execute_command=command+" "+Integer.toString(pin)+" "+arg2+" 1";
//		System.out.println("Command: "+ execute_command);
		ssh_class.executeButtonPress(execute_command);
	}
	
	@Given("^Press key \"([^\"]*)\" for \"([^\"]*)\" seconds and \"([^\"]*)\" times$")
	public void keyPressmultipleEmbedded(String arg1,String arg2,String count) throws Throwable{

		int pin=embedded_handler.getGPIOPin(arg1,new CommonUtilities().mapping);
//		System.out.println("pin name: "+ Integer.toString(pin));
		String execute_command=command+" "+Integer.toString(pin)+" "+arg2+" "+count;
//		System.out.println("Command: "+ execute_command);
		ssh_class.executeButtonPress(execute_command);
	}

	@Given("^Write text \"([^\"]*)\" on screen$")
	public void writeTextEmbedded(String text) throws Throwable{
		int wordCount= text.length();
		int press_count=0;
		char word='c';
		while(wordCount>0)
		{
			word=text.charAt(text.length() - wordCount);
			System.out.println("Word is:"+ word);
			String character = new StringBuilder().append("").append(word).toString();
			press_count=embedded_handler.getCharacterSequence("alpha_"+character,new CommonUtilities().mapping);
			System.out.println("pressing key :alpha_"+character+" for: "+ press_count+" times");
			keyPressmultipleEmbedded("alpha_"+character, "0.1", String.valueOf(press_count));
			Thread.sleep(3000);
			wordCount--;
		}
	}

	@Given("^double press key \"([^\"]*)\"$")
	public void doubletpressEmbedded(String key) throws Throwable{
		
		int countI = 2;
		while(countI>0){
			keyPressEmbedded(key, "0.5");
			Thread.sleep((long) 0.5);
			countI--;
		}
		
	}
	
	@Given("^verify text \"([^\"]*)\" on screen \"([^\"]*)\" with section \"([^\"]*)\"$")
	public void verify_text_on_screen_with_section(String arg1, String arg2, String arg3) throws Throwable {		
		assertTrue(new OCRHelper().verifyTextInImage(arg1, arg2, arg3,new CommonUtilities().config,new CommonUtilities().rootPath,new CommonUtilities().sectionMap));
		
	}
		
	@Given("capture and save live image$")
	public void captureImage() throws InterruptedException{
		OCRHelper ocrObj= new OCRHelper();
		ocrObj.captureImage(new CommonUtilities().config);
		ocrObj.copyImageFromRaspberry(new CommonUtilities().config,new CommonUtilities().rootPath);
		Thread.sleep(5000);
	}
	
	@Given("wait for \"([^\"]*)\" seconds$")
	public void waitForSec(String duration) throws NumberFormatException, InterruptedException{
		Thread.sleep(Long.parseLong(duration)*1000);
	}
	
}
