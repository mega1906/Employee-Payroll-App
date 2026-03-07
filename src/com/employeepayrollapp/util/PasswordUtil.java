package com.employeepayrollapp.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * PasswordUtil
 * A utility class that handles secure password hashing using the SHA-256 algorithm.
 * @author Developer
 * @version 1.0
 */
public class PasswordUtil {

    /**
     * Converts a plain-text password into a secure SHA-256 hashed value.
     * @param password The plain-text password
     * @return A 64-character hex string representing the hashed password
     * @throws RuntimeException if the SHA-256 algorithm is missing from the environment
     */
    public static String hash(String password) {
        if (password == null) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Critical Error: SHA-256 algorithm not found.", e);
        }
    }
}