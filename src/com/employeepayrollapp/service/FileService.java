package com.employeepayrollapp.service;

import com.employeepayrollapp.model.Payslip;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileService
 * Handles saving payslip data to external files and reading metrics.
 * @author Developer
 * @version 1.0
 */
public class FileService {
    
    // Static counter to guarantee strictly continuous file numbers
    private static int fileCounter = 1000;

    /**
     * Saves the payslip as a plain text file using a sequential ID.
     * @param payslip The payslip to save
     * @return The generated file name
     * @throws IOException if a file operation fails
     */
    public String savePayslipAsText(Payslip payslip) throws IOException {
        fileCounter++; // Increment counter for the text file
        String fileName = "data/Payslip_" + payslip.getEmployee().getEmpId() + "_" + fileCounter + ".txt";
        
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(payslip.toString());
        }
        return fileName;
    }

    /**
     * Saves the payslip as a mock PDF file using the next sequential ID.
     * @param payslip The payslip to save
     * @return The generated file name
     * @throws IOException if a file operation fails
     */
    public String savePayslipAsPdf(Payslip payslip) throws IOException {
        fileCounter++; // Increment counter again for the PDF file
        String fileName = "data/Payslip_" + payslip.getEmployee().getEmpId() + "_" + fileCounter + ".pdf";
        
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(payslip.toString());
        }
        return fileName;
    }

    /**
     * Retrieves the Employee ID associated with a given username from the registration file.
     * @param username The username to search for
     * @return The corresponding Employee ID, or null if not found
     */
    public String getEmpIdByUsername(String username) {
        File file = new File("data/employee_data.txt");
        if (!file.exists()) return null;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String currentEmpId = null;
            
            // Scan through the file line by line to map username to empId
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Employee ID : ")) {
                    currentEmpId = line.substring(14).trim();
                } else if (line.startsWith("Username    : ") && currentEmpId != null) {
                    String foundUsername = line.substring(14).trim();
                    if (foundUsername.equals(username)) {
                        return currentEmpId; // Match found
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading employee data.");
        }
        return null;
    }

    /**
     * Extracts Net Pay values from all saved text payslips in the data directory.
     * @param empIdFilter Optional Employee ID to filter by, or null for all
     * @return A list of extracted Net Pay amounts
     */
    public List<Double> getSavedNetPays(String empIdFilter) {
        List<Double> netPays = new ArrayList<>();
        File folder = new File("data");
        
        // Filter out everything except our saved .txt payslips
        File[] files = folder.listFiles((dir, name) -> name.startsWith("Payslip_") && name.endsWith(".txt"));
        
        if (files == null) return netPays;

        for (File file : files) {
            // If filtering by empId for a specific employee, skip non-matching files
            if (empIdFilter != null && !file.getName().contains(empIdFilter)) {
                continue;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Extract the double value dynamically from the formatting string
                    if (line.startsWith("Net Pay       : ")) {
                        try {
                            double netPay = Double.parseDouble(line.substring(16).trim());
                            netPays.add(netPay);
                        } catch (NumberFormatException ignored) {}
                    }
                }
            } catch (IOException ignored) {}
        }
        return netPays;
    }
}