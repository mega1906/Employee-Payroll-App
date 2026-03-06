package com.employeepayrollapp.model;

/**
 * UserAccount
 * Represents user authentication credentials.
 * @author Developer
 * @version 1.0
 */
public class UserAccount {

    private String username;
    private String password;

    /**
     * Constructs a UserAccount with credentials.
     * @param username Unique username
     * @param password Account password
     */
    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username.
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     * @return The password
     */
    public String getPassword() {
        return password;
    }
}