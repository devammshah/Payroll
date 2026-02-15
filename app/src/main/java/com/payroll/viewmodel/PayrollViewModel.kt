package com.payroll.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payroll.data.entity.EmployeeEntity
import com.payroll.data.entity.PayrollEntity
import com.payroll.data.entity.PayrollWithEmployee
import com.payroll.repository.PayrollRepository
import com.payroll.utils.SalaryCalculator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayrollViewModel @Inject constructor(
    private val repository: PayrollRepository
) : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun calculate(employee: EmployeeEntity): SalaryCalculator.Result = SalaryCalculator.calculate(employee)

    fun generatePayroll(employee: EmployeeEntity, month: Int, year: Int, onGenerated: (PayrollEntity?) -> Unit) {
        viewModelScope.launch {
            if (repository.payrollExists(employee.employeeId, month, year)) {
                _message.value = "Payroll already generated for selected month/year."
                onGenerated(null)
                return@launch
            }

            val calc = SalaryCalculator.calculate(employee)
            val payroll = PayrollEntity(
                employeeId = employee.employeeId,
                month = month,
                year = year,
                grossSalary = calc.gross,
                deductions = calc.deductions,
                netSalary = calc.net,
                generatedDate = System.currentTimeMillis()
            )
            repository.generatePayroll(payroll)
            _message.value = "Payroll generated successfully"
            onGenerated(payroll)
        }
    }

    fun getAllPayrolls(): LiveData<List<PayrollWithEmployee>> = repository.getAllPayrolls()

    fun getFilteredPayrolls(month: Int, year: Int, department: String): LiveData<List<PayrollWithEmployee>> =
        repository.getFilteredPayrolls(month, year, department)
}
