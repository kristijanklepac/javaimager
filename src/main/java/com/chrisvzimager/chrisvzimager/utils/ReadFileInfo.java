package com.chrisvzimager.chrisvzimager.utils;

import java.io.File;
import java.util.Date;

public class ReadFileInfo {
	
	public static void readFileInfo() {
		
		File file = new File("CodeJavaLogoSmall2.png"); // TODO .. only example here
		boolean existed = file.exists();
		String absolutePath = file.getAbsolutePath();
		String name = file.getName();
		String parent = file.getParent();
		File parentFile = file.getParentFile();
		boolean isDirectory = file.isDirectory();
		boolean isFile = file.isFile();
		boolean isHidden = file.isHidden();
		Date lastModified = new Date(file.lastModified());
		long size = file.length();
		
		// TODO .. only example here
		System.out.println("Existed? " + existed);
		System.out.println("Absolute Path = " + absolutePath);
		System.out.println("Name = " + name);
		System.out.println("Parent = " + parent);
		System.out.println("Parent File = " + parentFile);
		System.out.println("Is Directory = " + isDirectory);
		System.out.println("Is File = " + isFile);
		System.out.println("Is Hidden = " + isHidden);
		System.out.println("Last Modified = " + lastModified);
		System.out.println("Size = " + size);
	}

}
