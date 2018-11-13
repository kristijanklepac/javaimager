/**
 * 
 */
package com.chrisvzimager.chrisvzimager.exceptions;

/**
 * @author Kristijan Klepač
 * @email kristijan.klepac@gmail.com
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
