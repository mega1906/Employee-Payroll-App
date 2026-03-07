package com.employeepayrollapp.model;

/**
 * Payslip
 * Represents a monthly salary statement combining employee and salary data.
 * @author Developer
 * @version 2.0
 */
public final class Payslip implements Cloneable {
    private Employee employee;
    private SalaryComponents components;
    private String month;

    /**
     * Constructs a payslip with aggregated and composed data.
     * @param employee The employee receiving the payslip
     * @param components The calculated salary components
     * @param month The month of the payslip
     */
    public Payslip(Employee employee, SalaryComponents components, String month) {
        this.employee = employee;
        this.components = components;
        this.month = month;
    }

    /**
     * Retrieves the associated employee.
     * @return The employee object
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Retrieves the payslip month.
     * @return The month string
     */
    public String getMonth() {
        return month;
    }

    /**
     * Checks equality based on the month and employee ID.
     * @param o The object to compare
     * @return true if both objects refer to the same payslip
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payslip payslip = (Payslip) o;
        return month.equals(payslip.month) && employee.getEmpId().equals(payslip.employee.getEmpId());
    }

    /**
     * Generates a hash code consistent with equals logic.
     * @return The integer hash code
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + employee.getEmpId().hashCode();
        result = 31 * result + month.hashCode();
        return result;
    }

    /**
     * Creates a safe copy of the payslip for downloads.
     * @return A cloned Payslip object
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning failed", e);
        }
    }

    /**
     * Formats the payslip as a structured string.
     * @return The formatted payslip details
     */
    @Override
    public String toString() {
        // Simple string builder for clear output
        return "\n=========== PAYSLIP ===========\n"
             + "Month         : " + month + "\n"
             + "Employee ID   : " + employee.getEmpId() + "\n"
             + "Employee Name : " + employee.getName() + "\n\n"
             + "Earnings ----\n"
             + "Basic Salary  : " + components.basicSalary + "\n"
             + "HRA           : " + components.hra + "\n"
             + "DA            : " + components.da + "\n"
             + "Allowances    : " + components.allowances + "\n\n"
             + "Deductions ----\n"
             + "PF            : " + components.pf + "\n"
             + "Tax           : " + components.tax + "\n\n"
             + "Net Pay       : " + components.netPay + "\n"
             + "===============================\n";
    }
}