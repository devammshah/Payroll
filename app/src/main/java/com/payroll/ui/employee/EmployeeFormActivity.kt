package com.payroll.ui.employee

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.payroll.data.entity.EmployeeEntity
import com.payroll.databinding.ActivityEmployeeFormBinding
import com.payroll.utils.Validators
import com.payroll.viewmodel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeFormBinding
    private val viewModel: EmployeeViewModel by viewModels()
    private var editEmployeeId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editEmployeeId = intent.getIntExtra("employee_id", -1)
        if (editEmployeeId != -1) {
            viewModel.getEmployee(editEmployeeId) { employee ->
                employee?.let { populate(it) }
            }
        }

        binding.btnSaveEmployee.setOnClickListener {
            if (!validate()) return@setOnClickListener
            val employee = collectEmployee()
            if (editEmployeeId == -1) viewModel.addEmployee(employee) else viewModel.updateEmployee(employee.copy(employeeId = editEmployeeId))
            Snackbar.make(binding.root, "Employee saved", Snackbar.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun populate(employee: EmployeeEntity) = with(binding) {
        etFullName.setText(employee.fullName)
        etEmail.setText(employee.email)
        etPhone.setText(employee.phoneNumber)
        etDepartment.setText(employee.department)
        etDesignation.setText(employee.designation)
        etBasicSalary.setText(employee.basicSalary.toString())
        etHraPercent.setText(employee.hraPercent.toString())
        etDaPercent.setText(employee.daPercent.toString())
        etAllowances.setText(employee.otherAllowances.toString())
        etPfPercent.setText(employee.pfPercent.toString())
        etTaxPercent.setText(employee.taxPercent.toString())
        etBankAccount.setText(employee.bankAccountNumber)
        etIfsc.setText(employee.ifscCode)
    }

    private fun validate(): Boolean {
        val email = binding.etEmail.text.toString().trim()
        val valid = Validators.isNotBlank(binding.etFullName.text.toString()) && Validators.isValidEmail(email)
        if (!valid) Snackbar.make(binding.root, "Enter valid name and email", Snackbar.LENGTH_SHORT).show()
        return valid
    }

    private fun collectEmployee() = with(binding) {
        EmployeeEntity(
            fullName = etFullName.text.toString().trim(),
            email = etEmail.text.toString().trim(),
            phoneNumber = etPhone.text.toString().trim(),
            department = etDepartment.text.toString().trim(),
            designation = etDesignation.text.toString().trim(),
            basicSalary = etBasicSalary.text.toString().toDoubleOrNull() ?: 0.0,
            hraPercent = etHraPercent.text.toString().toDoubleOrNull() ?: 0.0,
            daPercent = etDaPercent.text.toString().toDoubleOrNull() ?: 0.0,
            otherAllowances = etAllowances.text.toString().toDoubleOrNull() ?: 0.0,
            pfPercent = etPfPercent.text.toString().toDoubleOrNull() ?: 0.0,
            taxPercent = etTaxPercent.text.toString().toDoubleOrNull() ?: 0.0,
            bankAccountNumber = etBankAccount.text.toString().trim(),
            ifscCode = etIfsc.text.toString().trim()
        )
    }
}
