package com.employeepayrollapp.model;

import com.employeepayrollapp.util.PasswordUtil;

/**
 * RegularEmployee
 * Represents a standard employee user.
 * @author Developer
 * @version 1.0
 */
public class RegularEmployee extends User {

    /**
     * Constructs a new RegularEmployee from raw input.
     * @param username The login username
     * @param password The raw password
     */
    public RegularEmployee(String username, String password) {
        super(username, password, "EMPLOYEE");
    }

    /**
     * Constructs a RegularEmployee from persistent file data.
     * @param username The login username
     * @param passwordHash The previously hashed password
     * @param isHashed Flag to indicate data is from persistence
     */
    public RegularEmployee(String username, String passwordHash, boolean isHashed) {
        super(username, passwordHash, "EMPLOYEE", isHashed);
    }

    @Override
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.passwordHash.equals(PasswordUtil.hash(password));
    }
}