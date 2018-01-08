package org.usfirst.frc.team907.robot;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerData {
	private final static Logger logger = Logger.getLogger(LoggerData.class
		      .getName());
	private static FileHandler fh = null;
	
	public static void logData(String data) {
		
		try {  
	        fh = new FileHandler("/Users/dinoyan/Desktop/MyLogFile.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  

	    logger.info(data);
	    
	}

}
