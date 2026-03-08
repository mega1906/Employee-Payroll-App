package com.employeepayrollapp.exception;

/**
 * EmailValidationException
 * Thrown when an email address does not meet the required Regex format.
 * @author Developer
 * @version 1.0
 */
public class EmailValidationException extends ValidationException {
    
    /**
     * Constructs the exception with a specific error message.
     * @param message The detailed error message
     */
    public EmailValidationException(String message) {
        super(message);
    }
}