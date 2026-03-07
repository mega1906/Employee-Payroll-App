package com.employeepayrollapp.main;

import com.employeepayrollapp.exception.ValidationException;
import com.employeepayrollapp.model.Employee;
import com.employeepayrollapp.model.Payslip;
import com.employeepayrollapp.model.RegularEmployee;
import com.employeepayrollapp.model.Session;
import com.employeepayrollapp.model.UserAccount;
import com.employeepayrollapp.service.AuthenticationService;
import com.employeepayrollapp.service.PayrollService;
import com.employeepayrollapp.validation.Validator;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * EmployeePayrollApp
 * Main runner class for the application.
 * Features an interactive loop and advanced switch routing for menus.
 * @author Developer
 * @version 3.0
 */
public class EmployeePayrollApp {

    /**
     * The main entry point for the application.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new File("data").mkdirs();
        
        AuthenticationService authService = new AuthenticationService();
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== EMPLOYEE PAYROLL SYSTEM ===");
            System.out.println("1. Register New Employee");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            
            String choice = sc.nextLine();
            
            switch (choice) {
                case "1" -> handleRegistration(sc, authService);
                case "2" -> handleLogin(sc, authService);
                case "3" -> {
                    System.out.println("Exiting System...");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    /**
     * Handles the Employee Registration logic.
     * @param sc The scanner instance
     * @param authService The authentication service
     */
    private static void handleRegistration(Scanner sc, AuthenticationService authService) {
        System.out.println("\n=== EMPLOYEE REGISTRATION ===");
        try {
            System.out.print("Enter Employee ID (EMP-XXXX): ");
            String empId = sc.nextLine();
            Validator.validateEmpId(empId);

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            Validator.validateEmail(email);

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();
            Validator.validatePhone(phone);

            System.out.print("Create Username: ");
            String username = sc.nextLine();

            System.out.print("Create Password: ");
            String password = sc.nextLine();

            UserAccount account = new UserAccount(username, password);
            Employee employee = new Employee(empId, name, email, phone, account);
            employee.persist();

            authService.registerUser(new RegularEmployee(username, password));

            System.out.println("\nEmployee Registered Successfully.");

        } catch (ValidationException e) {
            System.out.println("\nValidation Failed: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("\nError saving employee data: " + e.getMessage());
        }
    }

    /**
     * Handles the Employee Login logic and displays an interactive dashboard menu.
     * @param sc The scanner instance
     * @param authService The authentication service
     */
    private static void handleLogin(Scanner sc, AuthenticationService authService) {
        Session session = authService.login(sc);
        
        if (session == null) {
            return;
        }

        boolean loggedIn = true;
        
        while (loggedIn && !session.isExpired()) {
            System.out.println("\n======= " + session.getRole() + " DASHBOARD =======");
            
            switch (session.getRole()) {
                case "MANAGER" -> {
                    System.out.println("1. Approve Payroll");
                    System.out.println("2. View Reports");
                    System.out.println("3. Log Out");
                }
                case "EMPLOYEE" -> {
                    System.out.println("1. Generate Payslip");
                    System.out.println("2. Update Profile");
                    System.out.println("3. Log Out");
                }
            }
            
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
                        case "1" -> handlePayslipGeneration(sc);
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

    /**
     * Collects salary details and triggers the generation of a payslip.
     * @param sc The scanner instance
     */
    private static void handlePayslipGeneration(Scanner sc) {
        System.out.println("\n=== PAYSLIP GENERATION ===");
        try {
            System.out.print("Enter Employee ID: ");
            String empId = sc.nextLine();
            
            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();
            
            System.out.print("Enter Month (e.g., January 2026): ");
            String month = sc.nextLine();
            
            System.out.print("Enter Basic Salary: ");
            double basic = Double.parseDouble(sc.nextLine());
            
            System.out.print("Enter HRA: ");
            double hra = Double.parseDouble(sc.nextLine());
            
            System.out.print("Enter DA: ");
            double da = Double.parseDouble(sc.nextLine());
            
            System.out.print("Enter Allowances: ");
            double allowances = Double.parseDouble(sc.nextLine());

            Employee emp = new Employee(empId, name);
            PayrollService payrollService = new PayrollService();
            
            Payslip payslip = payrollService.generatePayslip(emp, month, basic, hra, da, allowances);
            System.out.println(payslip);

        } catch (NumberFormatException e) {
            System.out.println("\nInput Error: Please enter valid numeric values for salary components.");
        }
    }
}