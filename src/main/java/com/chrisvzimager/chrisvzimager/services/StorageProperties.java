/**
 * 
 */
package com.chrisvzimager.chrisvzimager.services;

/**
 * @author Kristijan Klepač
 * @email kristijan.klepac@gmail.com
 */
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}