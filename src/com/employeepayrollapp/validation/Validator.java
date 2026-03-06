package com.employeepayrollapp.validation;

import java.util.regex.Pattern;

import com.employeepayrollapp.exception.ValidationException;

/**
 * Validator
 * Helper class for data validation.
 * @author Developer
 * @version 1.0
 */
public class Validator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "^[6-9]\\d{9}$";
    private static final String EMP_ID_REGEX = "^EMP-\\d{4}$";

    /**
     * Validates email format.
     * @param email Input email
     * @throws ValidationException If invalid format
     */
    public static void validateEmail(String email) throws ValidationException {
        if (email == null || !Pattern.matches(EMAIL_REGEX, email)) {
            throw new ValidationException("Invalid Email Format. Please enter a valid email.");
        }
    }

    /**
     * Validates Indian phone number format (10 digits starting with 6-9).
     * @param phone Input phone number
     * @throws ValidationException If invalid format
     */
    public static void validatePhone(String phone) throws ValidationException {
        if (phone == null || !Pattern.matches(PHONE_REGEX, phone)) {
            throw new ValidationException("Invalid Phone Number. Must be 10 digits starting with 6, 7, 8, or 9.");
        }
    }

    /**
     * Validates Employee ID format (EMP-XXXX).
     * @param empId Input Employee ID
     * @throws ValidationException If invalid format
     */
    public static void validateEmpId(String empId) throws ValidationException {
        if (empId == null || !Pattern.matches(EMP_ID_REGEX, empId)) {
            throw new ValidationException("Invalid Employee ID. Must follow EMP-XXXX format.");
        }
    }
}