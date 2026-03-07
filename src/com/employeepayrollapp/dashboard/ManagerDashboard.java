package com.employeepayrollapp.dashboard;

import com.employeepayrollapp.model.Session;
import com.employeepayrollapp.service.FileService;

import java.util.Comparator;
import java.util.List;

/**
 * ManagerDashboard
 * Renders the manager-specific view and company-wide metrics dynamically.
 * @author Developer
 * @version 1.0
 */
public class ManagerDashboard implements Dashboard {

    @Override
    public void displayMenu(Session session) {
        System.out.println("\n======= " + session.getRole() + " DASHBOARD =======");
        displayMetrics();
        System.out.println("1. Approve Payroll");
        System.out.println("2. View Reports");
        System.out.println("3. Log Out");
    }

    /**
     * Calculates and displays Top 3 Payslips by reading actual saved files.
     */
    private void displayMetrics() {
        FileService fileService = new FileService();
        
        // Pass null to fetch every single payslip generated in the system
        List<Double> allNetPays = fileService.getSavedNetPays(null);
        
        System.out.println("--- Quick Metrics ---");
        System.out.print("Top 3 Highest Payslips: ");
        
        if (allNetPays.isEmpty()) {
            System.out.print("No payslips generated yet.");
        } else {
            // Stream the real data: Sort descending, limit to top 3, print safely
            allNetPays.stream()
                      .sorted(Comparator.reverseOrder())
                      .limit(3)
                      .forEach(pay -> System.out.print(pay + " | "));
        }
        System.out.println("\n---------------------");
    }
}