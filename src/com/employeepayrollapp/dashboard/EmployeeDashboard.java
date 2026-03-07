package com.employeepayrollapp.dashboard;

import com.employeepayrollapp.model.Session;
import com.employeepayrollapp.service.FileService;

import java.util.List;

/**
 * EmployeeDashboard
 * Renders the employee-specific view and personal metrics dynamically.
 * @author Developer
 * @version 1.0
 */
public class EmployeeDashboard implements Dashboard {

    @Override
    public void displayMenu(Session session) {
        System.out.println("\n======= " + session.getRole() + " DASHBOARD =======");
        // Pass the username to fetch metrics specific to this logged-in session
        displayMetrics(session.getUsername());
        System.out.println("1. Generate & Download Payslip");
        System.out.println("2. Update Profile");
        System.out.println("3. Log Out");
    }

    /**
     * Calculates and displays Year-To-Date earnings from actual saved files.
     * @param username The active username to filter metrics
     */
    private void displayMetrics(String username) {
        FileService fileService = new FileService();
        
        // Reverse-lookup the Employee ID using the Session's username
        String empId = fileService.getEmpIdByUsername(username);
        
        // Pass the resolved Employee ID to filter out other people's payslips
        List<Double> personalNetPays = fileService.getSavedNetPays(empId);
        
        // Calculate the sum total of all their extracted payslips
        double ytdEarnings = personalNetPays.stream()
                                            .mapToDouble(Double::doubleValue)
                                            .sum();
                                                  
        System.out.println("--- Quick Metrics ---");
        System.out.println("Year-To-Date Earnings : " + ytdEarnings);
        System.out.println("---------------------");
    }
}