package com.employeepayrollapp.model;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Employee
 * Represents an Employee entity demonstrating encapsulation and controlled state.
 * @author Developer
 * @version 2.0
 */
public class Employee {
    private String empId;
    private String name;
    private String email;
    private String phone;
    private UserAccount account;

    /**
     * Initializes a complete employee object.
     * @param empId The unique identifier
     * @param name The full name
     * @param email The validated email
     * @param phone The validated phone number
     * @param account The associated user account
     */
    public Employee(String empId, String name, String email, String phone, UserAccount account) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.account = account;
    }

    /**
     * Initializes a basic employee profile for payroll processing.
     * @param empId The unique identifier
     * @param name The full name
     */
    public Employee(String empId, String name) {
        this.empId = empId;
        this.name = name;
    }

    /**
     * Retrieves the employee ID.
     * @return The employee ID string
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * Retrieves the employee name.
     * @return The employee name string
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee ID : " + empId + "\n" +
               "Name        : " + name + "\n" +
               "Email       : " + email + "\n" +
               "Phone       : " + phone + "\n" +
               "Username    : " + (account != null ? account.getUsername() : "N/A");
    }

    /**
     * Saves employee data into a file.
     * @throws IOException if an error occurs during file operations
     */
    public void persist() throws IOException {
        try (FileWriter fw = new FileWriter("data/employee_data.txt", true)) {
            fw.write(this.toString() + "\n=================================\n");
        }
    }
}