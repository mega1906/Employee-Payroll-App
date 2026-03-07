package com.employeepayrollapp.service;

import com.employeepayrollapp.model.Payslip;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileService
 * Handles saving payslip data to external files.
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
        
        // Write the structured string format into the text file
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
        
        // Write the structured string format into the mock PDF
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(payslip.toString());
        }
        return fileName;
    }
}