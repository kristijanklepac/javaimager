package com.chrisvzimager.chrisvzimager.utils;

import java.io.File;

public class RenameFileOrDir {
	
	public static void renameFile() {
		File oldName = new File("CodeJavaLogoBig.png"); // example
		File newName = new File("CodeJavaLogoSmall.png"); // example
		 
		boolean renamed = oldName.renameTo(newName);
		 
		if (renamed) {
		    System.out.println("The file was renamed");
		} else {
		    System.out.println("Could not rename");
		}
	}
	
	public static void renameDir() {
		File oldName = new File("CodeJavaLogoBigDir"); // example
		File newName = new File("CodeJavaLogoSmallDir"); // example
		 
		boolean renamed = oldName.renameTo(newName);
		 
		if (renamed) {
		    System.out.println("The dir was renamed");
		} else {
		    System.out.println("Could not rename");
		}
	}

}
