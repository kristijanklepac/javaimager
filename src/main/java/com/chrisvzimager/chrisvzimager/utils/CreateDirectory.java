package com.chrisvzimager.chrisvzimager.utils;

import java.io.File;
import java.io.FileOutputStream;

public class CreateDirectory {
	
		public static void createDir(String fullPath) {
			
			File newDir = new File(fullPath); // only for example
			 
			if (newDir.mkdir()) {
			    System.out.println("Directory created");
			} else {
			    System.out.println("Directory not created");
			}
		}
	
		public static void createDirs() {
				
				File newDir = new File("Examples"); // only for example
				 
				if (newDir.mkdirs()) {
				    System.out.println("Directories created");
				} else {
				    System.out.println("Directories not created");
				}
			}

}
