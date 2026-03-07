package com.employeepayrollapp.model;

import com.employeepayrollapp.util.PasswordUtil;

/**
 * Manager
 * Represents a manager user.
 * @author Developer
 * @version 1.0
 */
public class Manager extends User {

    /**
     * Constructs a new Manager from raw input.
     * @param username The login username
     * @param password The raw password
     */
    public Manager(String username, String password) {
        super(username, password, "MANAGER");
    }

    /**
     * Constructs a Manager from persistent file data.
     * @param username The login username
     * @param passwordHash The previously hashed password
     * @param isHashed Flag to indicate data is from persistence
     */
    public Manager(String username, String passwordHash, boolean isHashed) {
        super(username, passwordHash, "MANAGER", isHashed);
    }

    @Override
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.passwordHash.equals(PasswordUtil.hash(password));
    }
}