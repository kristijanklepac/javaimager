package com.chrisvzimager.chrisvzimager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chrisvzimager.chrisvzimager.utils.CreateDirectory;
import com.chrisvzimager.chrisvzimager.utils.CreateEmptyFile;
import com.chrisvzimager.chrisvzimager.utils.CropImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @author Kristijan Klepač
 * @email kristijan.klepac@gmail.com
 */
@RestController
public class HomeController {
	
	@Autowired
    private Environment environment;
	
	@RequestMapping("/api")
    public String index() {
		
		System.out.println(environment.getProperty("app.domain"));          // It will return properties value from application.properties file, If key will not available than it will return null
        System.out.println(environment.getProperty("app.subdomain"));
        System.out.println(environment.getProperty("app.rootsubdomain","Default Value")); // If value is null than return default value
        System.out.println("cors is working for domain2.com");
		return "bla";
	}
	
	@RequestMapping("/api/dir")
    public String examples() {
		
		CreateDirectory.createDirs();
		CreateEmptyFile.createEmptyFile("/Users/kristijanklepac/Desktop/springbootApps/chrisvzimager/Examples/todo.txt");
		return "examples";
	}
	
	@RequestMapping("/api/crop")
    public String crop() {
		
		int width = 963;    //width of the image
        int height = 640;   //height of the image
 
        // For storing image in RAM
        BufferedImage image = null;
 
        // READ IMAGE
        try
        {
            File input_file = new File("/Users/kristijanklepac/Desktop/springbootApps/demo/img/img1.jpg"); //image file path
 
            /* create an object of BufferedImage type and pass
               as parameter the width,  height and image int
               type.TYPE_INT_ARGB means that we are representing
               the Alpha, Red, Green and Blue component of the
               image pixel using 8 bit integer value. */
            image = new BufferedImage(width, height,
                                    BufferedImage.TYPE_INT_ARGB);
 
             // Reading input file
            image = ImageIO.read(input_file);
            
            //image = getFasterScaledInstance(image, 400, 600, true);
            
            image = CropImage.topLeft(image, 300, 500) ;
 
            System.out.println("Reading complete.");
        }
        catch(IOException e)
        {
            System.out.println("Error: "+e);
        }
 
        // WRITE IMAGE
        try
        {
            // Output file path
            File output_file = new File("/Users/kristijanklepac/Desktop/springbootApps/demo/img/Out.jpg");
 
            // Writing to file taking type and path as
            ImageIO.write(image, "jpg", output_file);
 
            System.out.println("Writing complete.");
        }
        catch(IOException e)
        {
            System.out.println("Error: "+e);
        }
    	
		return "examples";
	}

}
