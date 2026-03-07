package com.employeepayrollapp.dashboard;

import com.employeepayrollapp.model.Session;

/**
 * Dashboard
 * Interface defining the contract for role-based dashboard rendering.
 * @author Developer
 * @version 1.0
 */
public interface Dashboard {
    
    /**
     * Renders the specific dashboard menu and metrics for the user.
     * @param session The active user session
     */
    void displayMenu(Session session);
}