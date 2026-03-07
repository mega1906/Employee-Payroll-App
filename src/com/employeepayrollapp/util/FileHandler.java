package com.employeepayrollapp.util;

import com.employeepayrollapp.model.Manager;
import com.employeepayrollapp.model.RegularEmployee;
import com.employeepayrollapp.model.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * FileHandler
 * Handles persistent file I/O operations for user authentication data.
 * @author Developer
 * @version 1.0
 */
public class FileHandler {

    private static final String AUTH_FILE = "data/users.txt";

    /**
     * Rewrites the entire users map to the text file.
     * @param users The map of active users in the system
     */
    public static void saveUsers(Map<String, User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AUTH_FILE))) {
            for (User user : users.values()) {
                bw.write(user.getUsername() + "," + user.getPasswordHash() + "," + user.getRole());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not save user data to file.");
        }
    }

    /**
     * Loads user data from the text file into a Map during initialization.
     * @return A populated Map of users, or an empty Map if the file does not exist
     */
    public static Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();
        File file = new File(AUTH_FILE);
        
        if (!file.exists()) {
            return users;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0];
                    String hash = parts[1];
                    String role = parts[2];

                    switch (role) {
                        case "MANAGER" -> users.put(username, new Manager(username, hash, true));
                        default -> users.put(username, new RegularEmployee(username, hash, true));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not load user data from file.");
        }
        return users;
    }
}