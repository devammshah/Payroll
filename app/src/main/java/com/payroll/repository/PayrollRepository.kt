package com.payroll.repository

import androidx.lifecycle.LiveData
import com.payroll.data.dao.PayrollDao
import com.payroll.data.entity.PayrollEntity
import com.payroll.data.entity.PayrollWithEmployee
import javax.inject.Inject

class PayrollRepository @Inject constructor(
    private val payrollDao: PayrollDao
) {
    fun getAllPayrolls(): LiveData<List<PayrollWithEmployee>> = payrollDao.getAllPayrolls()

    fun getFilteredPayrolls(month: Int, year: Int, department: String): LiveData<List<PayrollWithEmployee>> =
        payrollDao.getFilteredPayrolls(month, year, department)

    suspend fun payrollExists(employeeId: Int, month: Int, year: Int): Boolean =
        payrollDao.payrollExists(employeeId, month, year)

    suspend fun generatePayroll(payroll: PayrollEntity) = payrollDao.insertPayroll(payroll)
}
