package com.afour.autobot.embedded;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

import org.ini4j.Ini;

import net.sourceforge.tess4j.*;
import net.sourceforge.tess4j.util.ImageHelper;

public class OCRHelper {

	public static void main(String[] args) throws IOException {
		//        new OCRHelper().verifyTextInImage("Jed", "ContactScreen", "FirstContact");
		//    	new OCRHelper().captureImage();
		//    	new OCRHelper().copyImageFromRaspberry();
	}

	public void configureMotionCamStreaming(Ini config, Boolean status) throws Exception{
		try{
			SSHCommandExecutor execute = new SSHCommandExecutor(config);
			String command=null;
			if (status){
				command="sudo service motion restart";
			}else{
				command="sudo service motion stop";	
			}			
			execute.executeButtonPress(command);

			Thread.sleep(10000);

		}catch(Exception e){

			throw new Exception("Could not configure motion cam streaming on Pi as "+ e.getMessage());
		}
	}

	public void captureImage(Ini config){
		SSHCommandExecutor execute = new SSHCommandExecutor(config);
		String command="sudo service motion stop";
		execute.executeButtonPress(command);
		command ="bash //home//pi//imageCapture.sh";
		execute.executeButtonPress(command);
	}

	public void copyImageFromRaspberry(Ini config, String rootPath){
		Runtime rt= Runtime.getRuntime();
		try {
			String server=config.get("All", "ip_raspberry_pi");
			String user= config.get("All", "raspberry_pi_user");
			String password= config.get("All", "raspberry_pi_password");
			String command="pscp -pw "+password+" "+user+"@"+server+"://home//pi//OCRSample.png "+rootPath+"//src//";
			Process pr = rt.exec(command);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getOCRText(String screenName,String sectionName, Ini config,String rootPath,HashMap<String, HashMap<String, HashMap<String, String>>> sectionMap) throws IOException {

		OCRHelper ocr= new OCRHelper();
		ocr.captureImage(config);
		ocr.copyImageFromRaspberry(config,rootPath);

		HashMap<String, Integer> sections=  new OCRHelper().getOCRSectionCoordinates(screenName,sectionName, sectionMap);
		ImageIO.write(
				ocr.cropImage(
						ocr.getImageFile("OCRSample",rootPath), sections.get("x"), sections.get("y"), sections.get("width"), sections.get("height"))
				, "png", new File(rootPath+"//src//Croped.png"
						)
				);  	
		return (ocr.getImageText(new File(rootPath+"//src//Croped.png"),rootPath));
	}

	public javaxt.io.Image getImageFile(String name,String rootPath){
		return new javaxt.io.Image(rootPath+"//src//"+name+".png");
	}

	public HashMap<String, Integer> getOCRSectionCoordinates(String screenName,String sectionName, HashMap<String, HashMap<String, HashMap<String, String>>> sectionMap){

		int x=Integer.parseInt(sectionMap.get(screenName).get(sectionName).get("x1"));
		int y=Integer.parseInt(sectionMap.get(screenName).get(sectionName).get("y1"));
		int width=Integer.parseInt(sectionMap.get(screenName).get(sectionName).get("width"));
		int height=Integer.parseInt(sectionMap.get(screenName).get(sectionName).get("height"));

		HashMap<String, Integer> sectionDetails= new HashMap<String, Integer>();
		sectionDetails.put("x", x);
		sectionDetails.put("y", y);
		sectionDetails.put("width", width);
		sectionDetails.put("height", height);

		return sectionDetails;
	}   

	public BufferedImage cropImage(javaxt.io.Image image,int x,int y,int width,int height){

		return ImageHelper.cloneImage(image.getBufferedImage()).getSubimage(x, y, width, height);

	}

	public boolean verifyTextInImage(String textToVerify, String screenName,String sectionName, Ini config, String rootPath,HashMap<String, HashMap<String, HashMap<String, String>>> sectionMap) throws IOException{
		String textAvailable=new OCRHelper().getOCRText(screenName, sectionName,config, rootPath,sectionMap);
		System.out.println("Text on Screen is : "+ textAvailable);
		if (textAvailable.contains(textToVerify)){
			System.out.println("Text "+ textToVerify+" is available on screen");
			return true;
		}
		else{
			System.out.println("Text "+ textToVerify+" is not available on screen");
			return false;
		}
	}

	public String getImageText(File imageFile, String rootPath){

		ITesseract instance = new Tesseract();
		instance.setDatapath(rootPath+"//Tess4J//tessdata");

		try {
			String result = instance.doOCR(imageFile);
			return result;
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}    	
		return null;
	}


}