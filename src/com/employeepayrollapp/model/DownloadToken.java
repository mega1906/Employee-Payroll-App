package com.employeepayrollapp.model;

/**
 * DownloadToken
 * Simulates time-based validation for secure downloads.
 * @author Developer
 * @version 1.0
 */
public class DownloadToken {
    private long createdTime;
    private long expiryMillis;

    /**
     * Initializes the token with a 1-minute expiration timer.
     */
    public DownloadToken() {
        this.createdTime = System.currentTimeMillis();
        this.expiryMillis = 60 * 1000;
    }

    /**
     * Checks if the download token is still valid.
     * @return true if the token is expired, false otherwise
     */
    public boolean isExpired() {
        long now = System.currentTimeMillis();
        return (now - createdTime) > expiryMillis;
    }
}