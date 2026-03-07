package com.employeepayrollapp.dashboard;

/**
 * DashboardFactory
 * Implements the Abstract Factory pattern to instantiate dashboards dynamically.
 * @author Developer
 * @version 1.0
 */
public class DashboardFactory {

    /**
     * Generates the appropriate dashboard view based on the user's role.
     * @param role The assigned role of the logged-in user
     * @return A specific implementation of the Dashboard interface
     * @throws IllegalArgumentException if an unknown role is provided
     */
    public static Dashboard getDashboard(String role) {
        return switch (role) {
            case "MANAGER" -> new ManagerDashboard();
            case "EMPLOYEE" -> new EmployeeDashboard();
            default -> throw new IllegalArgumentException("Invalid role mapping detected.");
        };
    }
}