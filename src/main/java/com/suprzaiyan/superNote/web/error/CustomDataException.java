package com.suprzaiyan.superNote.web.error;

/**
 * CustomDataException - a custom Exception class
 *
 * @author vignesh
 * @version 04/10/19
 * @created 04/10/19
 */
public class CustomDataException extends RuntimeException {

    public CustomDataException(String message) {
        super(message);
    }
}
