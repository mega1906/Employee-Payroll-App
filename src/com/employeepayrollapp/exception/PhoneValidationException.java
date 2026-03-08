package com.employeepayrollapp.exception;

/**
 * PhoneValidationException
 * Thrown when a phone number violates length or prefix constraints.
 * @author Developer
 * @version 1.0
 */
public class PhoneValidationException extends ValidationException {
    
    /**
     * Constructs the exception with a specific error message.
     * @param message The detailed error message
     */
    public PhoneValidationException(String message) {
        super(message);
    }
}