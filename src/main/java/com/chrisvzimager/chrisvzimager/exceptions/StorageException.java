/**
 * 
 */
package com.chrisvzimager.chrisvzimager.exceptions;

/**
 * @author Kristijan Klepač
 * @email kristijan.klepac@gmail.com
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
