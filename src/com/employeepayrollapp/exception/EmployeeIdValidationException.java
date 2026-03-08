package com.employeepayrollapp.exception;

/**
 * EmployeeIdValidationException
 * Thrown when an Employee ID does not follow the EMP-XXXX corporate format.
 * @author Developer
 * @version 1.0
 */
public class EmployeeIdValidationException extends ValidationException {
    
    /**
     * Constructs the exception with a specific error message.
     * @param message The detailed error message
     */
    public EmployeeIdValidationException(String message) {
        super(message);
    }
}