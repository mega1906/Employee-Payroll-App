package com.employeepayrollapp.menu;

import com.employeepayrollapp.service.AuthenticationService;

import java.util.Scanner;

/**
 * MainMenu
 * The root interactive menu for the entire application loop.
 * @author Developer
 * @version 1.0
 */
public class MainMenu {

    private final AuthenticationService authService;
    private final Scanner sc;

    /**
     * Initializes the main menu with necessary services.
     */
    public MainMenu() {
        this.authService = new AuthenticationService();
        this.sc = new Scanner(System.in);
    }

    /**
     * Starts the infinite main menu loop.
     */
    public void start() {
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== EMPLOYEE PAYROLL SYSTEM ===");
            System.out.println("1. Register New Employee/Manager");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            
            String choice = sc.nextLine();
            
            switch (choice) {
                case "1" -> RegistrationMenu.display(sc, authService);
                case "2" -> LoginMenu.display(sc, authService);
                case "3" -> {
                    System.out.println("Exiting System...");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}