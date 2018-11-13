package com.chrisvzimager.chrisvzimager.utils;

import java.io.File;

public class DeleteFileOrDir {
	
	public static void delFile() {
	
		File file = new File("CodeJavaLogoSmall.png");
		 
		if (file.delete()) {
		    System.out.println("The file was deleted");
		} else {
		    System.out.println("Could not delete the file");
		}
	
	}
	
	public static void delDir() {
		
		File file = new File("CodeJavaLogoSmall");
		 
		if (file.delete()) {
		    System.out.println("The dir was deleted");
		} else {
		    System.out.println("Could not delete the file");
		}
	
	}

}
