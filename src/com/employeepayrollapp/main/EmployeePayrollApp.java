package com.employeepayrollapp.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.employeepayrollapp.exception.ValidationException;
import com.employeepayrollapp.model.Employee;
import com.employeepayrollapp.model.UserAccount;
import com.employeepayrollapp.validation.Validator;

/**
 * EmployeePayrollApp
 * Main runner class that manages input, validation, object creation, and data persistence.
 * @author Developer
 * @version 1.0
 */
public class EmployeePayrollApp {

    /**
     * Main entry point for the application.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Ensure data directory exists
        new File("data").mkdirs();

        System.out.println("=== EMPLOYEE REGISTRATION ===");

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

            // Create objects after successful validation
            UserAccount account = new UserAccount(username, password);
            Employee employee = new Employee(empId, name, email, phone, account);

            // Persist data
            employee.persist();

            // Display confirmation
            System.out.println("\nEmployee Registered Successfully:");
            System.out.println(employee);
            System.out.println("Data persisted in file: employee_data.txt");

        } catch (ValidationException e) {
            System.out.println("\nValidation Failed: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("\nError saving employee data: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}