package com.verimi.testcommon.framework.utils.exceptions;

/**
 * Exception thrown when new Class instance creation is not possible
 */
public class InstanceCreationException extends RuntimeException {

    /**
     * Exception thrown when new Class instance creation is not possible with custom message and exception
     *
     * @param message The message of the Exception
     * @param e       The exception to manipulate
     */
    public InstanceCreationException(String message, Exception e) {
        super(message, e);
    }
}
