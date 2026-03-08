package com.employeepayrollapp.menu;

import com.employeepayrollapp.model.DownloadToken;
import com.employeepayrollapp.model.Employee;
import com.employeepayrollapp.model.Payslip;
import com.employeepayrollapp.service.FileService;
import com.employeepayrollapp.service.PayrollService;

import java.io.IOException;
import java.util.Scanner;

/**
 * PayslipMenu
 * Handles the UI for collecting salary data and triggering payslip downloads.
 * @author Developer
 * @version 1.0
 */
public class PayslipMenu {

    /**
     * Collects salary details and triggers the generation of a payslip.
     * @param sc The shared scanner instance
     */
    public static void display(Scanner sc) {
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
            
            Payslip originalPayslip = payrollService.generatePayslip(emp, month, basic, hra, da, allowances);
            System.out.println(originalPayslip);

            System.out.print("Would you like to download this payslip? (Y/N): ");
            String downloadChoice = sc.nextLine();
            
            if (downloadChoice.equalsIgnoreCase("Y")) {
                handlePayslipDownload(originalPayslip);
            }

        } catch (NumberFormatException e) {
            System.out.println("\nInput Error: Please enter valid numeric values for salary components.");
        }
    }

    /**
     * Handles the safe cloning and file persistence of a generated payslip.
     * @param original The original generated payslip
     */
    private static void handlePayslipDownload(Payslip original) {
        System.out.println("\n=== PAYSLIP DOWNLOAD ===");
        
        DownloadToken token = new DownloadToken();
        if (token.isExpired()) {
            System.out.println("Download token expired. Please generate the payslip again.");
            return;
        }

        Payslip clonedPayslip = (Payslip) original.clone();
        
        System.out.println("Verified: Download copy is equal to original: " + clonedPayslip.equals(original));
        System.out.println("Original hashcode : " + original.hashCode());
        System.out.println("Cloned hashcode   : " + clonedPayslip.hashCode());
        
        try {
            FileService fileService = new FileService();
            String txtFile = fileService.savePayslipAsText(clonedPayslip);
            String pdfFile = fileService.savePayslipAsPdf(clonedPayslip);
            
            System.out.println("\nPayslip Download Successful.");
            System.out.println("Saved as text file: " + txtFile);
            System.out.println("Saved as PDF file : " + pdfFile);

            System.out.println("\n--- Printed payslip ---");
            System.out.println(clonedPayslip);

        } catch (IOException e) {
            System.out.println("Error saving payslip files: " + e.getMessage());
        }
    }
}