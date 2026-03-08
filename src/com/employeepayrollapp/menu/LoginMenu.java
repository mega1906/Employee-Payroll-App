package com.employeepayrollapp.menu;

import com.employeepayrollapp.dashboard.Dashboard;
import com.employeepayrollapp.dashboard.DashboardFactory;
import com.employeepayrollapp.model.Session;
import com.employeepayrollapp.service.AuthenticationService;

import java.util.Scanner;

/**
 * LoginMenu
 * Handles user authentication and role-based dashboard routing.
 * @author Developer
 * @version 1.0
 */
public class LoginMenu {

    /**
     * Processes login credentials and displays the appropriate interactive dashboard.
     * @param sc The shared scanner instance
     * @param authService The authentication service
     */
    public static void display(Scanner sc, AuthenticationService authService) {
        Session session = authService.login(sc);
        
        if (session == null) {
            return; 
        }

        boolean loggedIn = true;
        Dashboard dashboard = DashboardFactory.getDashboard(session.getRole());
        
        while (loggedIn && !session.isExpired()) {
            
            dashboard.displayMenu(session);
            
            System.out.print("Select an option: ");
            String choice = sc.nextLine();
            
            switch (session.getRole()) {
                case "MANAGER" -> {
                    switch (choice) {
                        case "1", "2" -> System.out.println("\nFeature coming soon...");
                        case "3" -> {
                            System.out.println("\nLogged out successfully.");
                            loggedIn = false;
                        }
                        default -> System.out.println("\nInvalid choice. Please try again.");
                    }
                }
                case "EMPLOYEE" -> {
                    switch (choice) {
                        case "1" -> PayslipMenu.display(sc);
                        case "2" -> System.out.println("\nFeature coming soon...");
                        case "3" -> {
                            System.out.println("\nLogged out successfully.");
                            loggedIn = false;
                        }
                        default -> System.out.println("\nInvalid choice. Please try again.");
                    }
                }
            }
        }
        
        if (session.isExpired()) {
            System.out.println("\nSession has expired. Please log in again.");
        }
    }
}