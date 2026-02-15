package com.payroll.utils

import com.payroll.data.entity.EmployeeEntity

object SalaryCalculator {

    data class Result(
        val hra: Double,
        val da: Double,
        val gross: Double,
        val pf: Double,
        val tax: Double,
        val deductions: Double,
        val net: Double
    )

    fun calculate(employee: EmployeeEntity): Result {
        val hra = employee.basicSalary * employee.hraPercent / 100
        val da = employee.basicSalary * employee.daPercent / 100
        val gross = employee.basicSalary + hra + da + employee.otherAllowances
        val pf = employee.basicSalary * employee.pfPercent / 100
        val tax = gross * employee.taxPercent / 100
        val deductions = pf + tax
        val net = gross - deductions
        return Result(hra, da, gross, pf, tax, deductions, net)
    }
}
