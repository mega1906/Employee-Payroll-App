package com.employeepayrollapp.validation;

import com.employeepayrollapp.exception.EmailValidationException;
import com.employeepayrollapp.exception.EmployeeIdValidationException;
import com.employeepayrollapp.exception.PasswordValidationException;
import com.employeepayrollapp.exception.PhoneValidationException;

import java.util.regex.Pattern;

/**
 * ValidationService
 * Centralizes all Regex and input sanitization logic into a single reliable service.
 * @author Developer
 * @version 1.0
 */
public class ValidationService {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "^[6-9]\\d{9}$";
    private static final String EMP_ID_REGEX = "^EMP-\\d{4}$";
    // Enforces at least 8 chars, 1 uppercase, and 1 special character
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";

    /**
     * Validates the corporate email format.
     * @param email The email to validate
     * @throws EmailValidationException if the format is invalid
     */
    public static void validateEmail(String email) throws EmailValidationException {
        if (email == null || !Pattern.matches(EMAIL_REGEX, email)) {
            throw new EmailValidationException("Invalid Email: Must follow standard formats (e.g., user@domain.com).");
        }
    }

    /**
     * Validates an Indian phone number format.
     * @param phone The phone string to validate
     * @throws PhoneValidationException if length or prefix is incorrect
     */
    public static void validatePhone(String phone) throws PhoneValidationException {
        if (phone == null || !Pattern.matches(PHONE_REGEX, phone)) {
            throw new PhoneValidationException("Invalid Phone: Must be exactly 10 digits starting with 6, 7, 8, or 9.");
        }
    }

    /**
     * Validates the standard corporate ID format.
     * @param empId The ID string to validate
     * @throws EmployeeIdValidationException if it breaks format rules
     */
    public static void validateEmpId(String empId) throws EmployeeIdValidationException {
        if (empId == null || !Pattern.matches(EMP_ID_REGEX, empId)) {
            throw new EmployeeIdValidationException("Invalid ID: Must follow the 'EMP-XXXX' format.");
        }
    }

    /**
     * Enforces strict password security standards.
     * @param password The password string to validate
     * @throws PasswordValidationException if it lacks required characters
     */
    public static void validatePassword(String password) throws PasswordValidationException {
        if (password == null || !Pattern.matches(PASSWORD_REGEX, password)) {
            throw new PasswordValidationException("Weak Password: Must be 8+ characters, contain 1 uppercase, and 1 special character.");
        }
    }
}