package com.employeepayrollapp.model;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Employee
 * Represents an employee entity with encapsulated private data.
 * @author Developer
 * @version 1.0
 */
public class Employee {

    private String empId;
    private String name;
    private String email;
    private String phone;
    private UserAccount account;

    /**
     * Constructs a fully initialized Employee object.
     * @param empId   Unique identifier for the employee
     * @param name    Full name of the employee
     * @param email   Validated email address
     * @param phone   Validated phone number
     * @param account UserAccount associated with this employee
     */
    public Employee(String empId, String name, String email, String phone, UserAccount account) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.account = account;
    }

    /**
     * Formats employee details as a string.
     * @return Formatted string containing employee details
     */
    @Override
    public String toString() {
        return "Employee ID : " + empId + "\n" +
               "Name        : " + name + "\n" +
               "Email       : " + email + "\n" +
               "Phone       : " + phone + "\n" +
               "Username    : " + account.getUsername();
    }

    /**
     * Saves employee data to a file.
     * @throws IOException If file writing fails
     */
    public void persist() throws IOException {
        try (FileWriter fw = new FileWriter("data/employee_data.txt", true)) {
            fw.write(this.toString() + "\n=================================\n");
        }
    }
}