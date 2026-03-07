package com.employeepayrollapp.service;

import com.employeepayrollapp.model.Employee;
import com.employeepayrollapp.model.Payslip;
import com.employeepayrollapp.model.SalaryComponents;

/**
 * PayrollService
 * Handles business rules and calculations for employee salaries.
 * @author Developer
 * @version 1.0
 */
public class PayrollService {

    /**
     * Calculates deductions and generates a final payslip.
     * @param employee The target employee
     * @param month The payroll month
     * @param basic The basic salary
     * @param hra The house rent allowance
     * @param da The dearness allowance
     * @param allowances Additional allowances
     * @return A fully generated payslip
     */
    public Payslip generatePayslip(Employee employee, String month, double basic, double hra, double da, double allowances) {
        SalaryComponents sc = new SalaryComponents(basic, hra, da, allowances);
        
        double gross = basic + hra + da + allowances;
        sc.pf = basic * 0.12;
        sc.tax = gross * 0.10;
        sc.netPay = gross - (sc.pf + sc.tax);
        
        return new Payslip(employee, sc, month);
    }
}