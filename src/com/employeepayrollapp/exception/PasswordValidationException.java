package com.employeepayrollapp.exception;

/**
 * PasswordValidationException
 * Thrown when a password fails to meet minimum security requirements.
 * @author Developer
 * @version 1.0
 */
public class PasswordValidationException extends ValidationException {
    
    /**
     * Constructs the exception with a specific error message.
     * @param message The detailed error message
     */
    public PasswordValidationException(String message) {
        super(message);
    }
}