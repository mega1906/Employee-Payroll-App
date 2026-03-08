package com.employeepayrollapp.main;

import com.employeepayrollapp.menu.MainMenu;

import java.io.File;

/**
 * EmployeePayrollApp
 * Main entry point for the application. Delegates entirely to the Menu system.
 * @author Developer
 * @version 6.0
 */
public class EmployeePayrollApp {

    /**
     * Bootstraps the environment and launches the main menu.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        // Ensure data directory exists for persistence
        new File("data").mkdirs();
        
        // Launch the application interface
        MainMenu menu = new MainMenu();
        menu.start();
    }
}