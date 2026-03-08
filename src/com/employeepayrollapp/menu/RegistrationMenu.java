package com.employeepayrollapp.menu;

import com.employeepayrollapp.exception.ValidationException;
import com.employeepayrollapp.model.Employee;
import com.employeepayrollapp.model.Manager;
import com.employeepayrollapp.model.RegularEmployee;
import com.employeepayrollapp.model.UserAccount;
import com.employeepayrollapp.service.AuthenticationService;
import com.employeepayrollapp.validation.ValidationService;

import java.io.IOException;
import java.util.Scanner;

/**
 * RegistrationMenu
 * Handles the console UI and input collection for registering new users.
 * @author Developer
 * @version 1.0
 */
public class RegistrationMenu {

    /**
     * Displays the registration form and processes the input safely.
     * @param sc The shared scanner instance
     * @param authService The authentication service
     */
    public static void display(Scanner sc, AuthenticationService authService) {
        System.out.println("\n=== REGISTRATION ===");
        try {
            System.out.print("Enter Employee ID (EMP-XXXX): ");
            String empId = sc.nextLine();
            ValidationService.validateEmpId(empId);

            System.out.print("Enter Full Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            ValidationService.validateEmail(email);

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();
            ValidationService.validatePhone(phone);

            System.out.print("Create Username: ");
            String username = sc.nextLine();

            System.out.print("Create Password (1 Uppercase, 1 Special, 8+ chars): ");
            String password = sc.nextLine();
            ValidationService.validatePassword(password);

            System.out.println("\nSelect Account Role:");
            System.out.println("1. Regular Employee");
            System.out.println("2. Manager");
            System.out.print("Enter choice (1/2): ");
            String roleChoice = sc.nextLine();

            UserAccount account = new UserAccount(username, password);
            Employee employee = new Employee(empId, name, email, phone, account);
            
            employee.persist();

            switch (roleChoice) {
                case "2" -> authService.registerUser(new Manager(username, password));
                default -> authService.registerUser(new RegularEmployee(username, password));
            }

            System.out.println("\nAccount Registered Successfully.");

        } catch (ValidationException e) {
            System.out.println("\nValidation Failed: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("\nError saving employee data: " + e.getMessage());
        }
    }
}