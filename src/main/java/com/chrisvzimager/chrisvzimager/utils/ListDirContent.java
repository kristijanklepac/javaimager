package com.chrisvzimager.chrisvzimager.utils;

import java.io.File;
import java.io.FilenameFilter;

public class ListDirContent {
	
	public static String[] listContent() {
	
		File userHomeDir = new File(System.getProperty("user.home")); // only example
		 
		String[] content = userHomeDir.list();
		 
		for (String entry : content) {
		    System.out.println(entry);
		}
		
		return content;
	
	}
	
	public static String[] listContentWithExt(String ext) {
		
		File currentDir = new File(".");
		 
		FilenameFilter filter = new FilenameFilter() {
		    public boolean accept(File file, String name) {
		        return name.endsWith(".java"); // only example...
		    }
		};
		 
		String[] content = currentDir.list(filter);
		 
		for (String entry : content) {
		    System.out.println(entry);
		}
		
		return content;
	}

}
