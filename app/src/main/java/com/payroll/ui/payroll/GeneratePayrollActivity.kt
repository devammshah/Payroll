package com.payroll.ui.payroll

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.payroll.data.entity.EmployeeEntity
import com.payroll.databinding.ActivityGeneratePayrollBinding
import com.payroll.ui.employee.EmployeeAdapter
import com.payroll.utils.PdfGenerator
import com.payroll.viewmodel.EmployeeViewModel
import com.payroll.viewmodel.PayrollViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneratePayrollActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGeneratePayrollBinding
    private val employeeViewModel: EmployeeViewModel by viewModels()
    private val payrollViewModel: PayrollViewModel by viewModels()
    private var selectedEmployee: EmployeeEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeneratePayrollBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val employeeAdapter = EmployeeAdapter(
            onEdit = {
                selectedEmployee = it
                Snackbar.make(binding.root, "Selected ${it.fullName}", Snackbar.LENGTH_SHORT).show()
            },
            onDelete = {}
        )

        binding.recyclerSelectEmployee.layoutManager = LinearLayoutManager(this)
        binding.recyclerSelectEmployee.adapter = employeeAdapter
        employeeViewModel.employees.observe(this) { employeeAdapter.submitList(it) }

        payrollViewModel.message.observe(this) { Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show() }

        binding.btnGenerate.setOnClickListener {
            val employee = selectedEmployee ?: return@setOnClickListener
            val month = binding.etMonth.text.toString().toIntOrNull() ?: return@setOnClickListener
            val year = binding.etYear.text.toString().toIntOrNull() ?: return@setOnClickListener

            payrollViewModel.generatePayroll(employee, month, year) { payroll ->
                payroll ?: return@generatePayroll
                val calc = payrollViewModel.calculate(employee)
                val file = PdfGenerator.generatePayslip(this, employee, payroll, calc)
                val uri = FileProvider.getUriForFile(this, "$packageName.provider", file)
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "application/pdf"
                    putExtra(Intent.EXTRA_STREAM, uri)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                startActivity(Intent.createChooser(intent, "Share Payslip"))
            }
        }
    }
}
