package com.chrisvzimager.chrisvzimager.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author kristijanklepac
 *
 */
public class CreateEmptyFile {
	
	public static void createEmptyFile(String fullPath) {
		String MessageCreateFile = "";
		try {
			 
		    File file = new File(fullPath);
		    boolean created = file.createNewFile();
		 
		    if (created) {
		        MessageCreateFile = "The file has been created";
		    }
		 
		} catch (IOException ex) {
			MessageCreateFile = "The file has not been created";
			System.err.println(ex.getMessage());
		}
		
		// log in temp file
		try {
		    File tempFile = File.createTempFile("TODO_random_string_that_explain_file", ".log", new File("/Users/kristijanklepac/Desktop/springbootApps/chrisvzimager/Examples"));
		    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		    writer.write(fullPath);
		    writer.write(MessageCreateFile);
		    writer.close();
		 
		} catch (IOException ex) {
		    System.err.println(ex.getMessage());
		}
	}

}
