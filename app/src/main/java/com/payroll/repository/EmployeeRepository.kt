package com.payroll.repository

import androidx.lifecycle.LiveData
import com.payroll.data.dao.EmployeeDao
import com.payroll.data.entity.EmployeeEntity
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val employeeDao: EmployeeDao
) {
    fun getAllEmployees(): LiveData<List<EmployeeEntity>> = employeeDao.getAllEmployees()

    fun searchEmployees(query: String): LiveData<List<EmployeeEntity>> = employeeDao.searchEmployees(query)

    suspend fun getEmployee(employeeId: Int): EmployeeEntity? = employeeDao.getEmployeeById(employeeId)

    suspend fun addEmployee(employee: EmployeeEntity) = employeeDao.insertEmployee(employee)

    suspend fun updateEmployee(employee: EmployeeEntity) = employeeDao.updateEmployee(employee)

    suspend fun deleteEmployee(employee: EmployeeEntity) = employeeDao.deleteEmployee(employee)
}
