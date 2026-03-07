package com.employeepayrollapp.service;

import com.employeepayrollapp.model.Session;
import com.employeepayrollapp.model.User;
import com.employeepayrollapp.util.FileHandler;

import java.util.Map;
import java.util.Scanner;

/**
 * AuthenticationService
 * Centralizes login rules, credential verification, and limits.
 * Synchronized directly with file persistence upon initialization and updates.
 *
 * @author Developer
 * @version 1.0
 */
public class AuthenticationService {

    private Map<String, User> users;
    private int maxAttempts = 3;

    /**
     * Constructor initializes the users map by loading data from the persistent text file.
     */
    public AuthenticationService() {
        this.users = FileHandler.loadUsers();
    }

    /**
     * Registers a new user into the system and immediately rewrites the file.
     *
     * @param user The user object to store
     */
    public void registerUser(User user) {
        users.put(user.getUsername(), user);
        FileHandler.saveUsers(users);
    }

    /**
     * Handles the complete login flow.
     *
     * @param sc The scanner instance to read input
     * @return A valid Session object if login succeeds, null if it fails
     */
    public Session login(Scanner sc) {
        int attempts = 0;
        System.out.println("\n=== LOGIN ===");

        while (attempts < maxAttempts) {
            System.out.print("Enter Username: ");
            String username = sc.nextLine();
            
            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            User user = users.get(username);

            if (user != null && user.authenticate(username, password)) {
                System.out.println("\nLogin Successful!");
                return new Session(username, user.getRole());
            } else {
                attempts++;
                System.out.println("Invalid credentials. Attempts remaining: " + (maxAttempts - attempts) + "\n");
            }
        }
        
        System.out.println("Maximum login attempts exceeded. Account locked temporarily.");
        return null;
    }
}