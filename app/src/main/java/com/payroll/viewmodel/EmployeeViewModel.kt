package com.payroll.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payroll.data.entity.EmployeeEntity
import com.payroll.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    val employees: LiveData<List<EmployeeEntity>> = repository.getAllEmployees()

    fun search(query: String): LiveData<List<EmployeeEntity>> = repository.searchEmployees(query)

    fun getEmployee(employeeId: Int, callback: (EmployeeEntity?) -> Unit) {
        viewModelScope.launch { callback(repository.getEmployee(employeeId)) }
    }

    fun addEmployee(employee: EmployeeEntity) {
        viewModelScope.launch { repository.addEmployee(employee) }
    }

    fun updateEmployee(employee: EmployeeEntity) {
        viewModelScope.launch { repository.updateEmployee(employee) }
    }

    fun deleteEmployee(employee: EmployeeEntity) {
        viewModelScope.launch { repository.deleteEmployee(employee) }
    }
}
