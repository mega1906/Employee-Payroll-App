package com.employeepayrollapp.model;

/**
 * Session
 * Manages the state and lifetime of a user's login session.
 *
 * @author Developer
 * @version 1.0
 */
public class Session {
    
    private String username;
    private String role;
    private long loginTime;
    private long timeoutMillis;

    /**
     * Constructs a new Session for the authenticated user.
     *
     * @param username The username of the logged-in user
     * @param role     The role of the logged-in user
     */
    public Session(String username, String role) {
        this.username = username;
        this.role = role;
        this.loginTime = System.currentTimeMillis();
        this.timeoutMillis = 5 * 60 * 1000; // 5 minutes timeout
    }

    /**
     * Checks whether the session is still valid based on the timeout threshold.
     *
     * @return true if the session has expired, false otherwise
     */
    public boolean isExpired() {
        return (System.currentTimeMillis() - loginTime) > timeoutMillis;
    }

    /**
     * Retrieves the username associated with this session.
     *
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the role associated with this session.
     *
     * @return The user role
     */
    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Session active for user: " + username + " (" + role + ")";
    }
}