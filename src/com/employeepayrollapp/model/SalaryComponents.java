package com.employeepayrollapp.model;

/**
 * SalaryComponents
 * Represents the breakdown of an employee's salary.
 * @author Developer
 * @version 1.0
 */
public class SalaryComponents {
    public double basicSalary;
    public double hra;
    public double da;
    public double allowances;
    public double pf;
    public double tax;
    public double netPay;

    /**
     * Initializes earnings before deductions.
     * @param basicSalary The basic pay amount
     * @param hra The house rent allowance
     * @param da The dearness allowance
     * @param allowances Other allowances
     */
    public SalaryComponents(double basicSalary, double hra, double da, double allowances) {
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.da = da;
        this.allowances = allowances;
    }
}