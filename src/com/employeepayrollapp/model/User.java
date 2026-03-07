package com.employeepayrollapp.model;

import com.employeepayrollapp.util.PasswordUtil;

/**
 * User
 * An abstract base class representing any authenticated user in the system.
 * @author Developer
 * @version 1.0
 */
public abstract class User {
    
    protected String username;
    protected String passwordHash;
    protected String role;

    /**
     * Standard constructor for new users. Hashes the raw password.
     * @param username The unique login name
     * @param password The raw password to be hashed
     * @param role The system role
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.passwordHash = PasswordUtil.hash(password);
        this.role = role;
    }

    /**
     * Overloaded constructor used exclusively by FileHandler to load existing users.
     * Bypasses the hashing mechanism since the hash is already generated.
     * @param username The unique login name
     * @param passwordHash The existing SHA-256 hash from the file
     * @param role The system role
     * @param isHashed Flag to differentiate constructors
     */
    public User(String username, String passwordHash, String role, boolean isHashed) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    /**
     * Abstract authentication method.
     * @param username The inputted username
     * @param password The inputted raw password
     * @return true if credentials match, false otherwise
     */
    public abstract boolean authenticate(String username, String password);

    /**
     * Retrieves the username.
     * @return The username string
     */
    public String getUsername() { return username; }

    /**
     * Retrieves the stored password hash.
     * @return The SHA-256 hash string
     */
    public String getPasswordHash() { return passwordHash; }

    /**
     * Retrieves the user role.
     * @return The role string
     */
    public String getRole() { return role; }
}