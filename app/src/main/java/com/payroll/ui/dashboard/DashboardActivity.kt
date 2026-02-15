package com.payroll.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.payroll.databinding.ActivityDashboardBinding
import com.payroll.ui.employee.EmployeeFormActivity
import com.payroll.ui.employee.EmployeeListActivity
import com.payroll.ui.login.LoginActivity
import com.payroll.ui.payroll.GeneratePayrollActivity
import com.payroll.ui.payroll.PayrollReportsActivity
import com.payroll.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardAddEmployee.setOnClickListener { startActivity(Intent(this, EmployeeFormActivity::class.java)) }
        binding.cardViewEmployees.setOnClickListener { startActivity(Intent(this, EmployeeListActivity::class.java)) }
        binding.cardGeneratePayroll.setOnClickListener { startActivity(Intent(this, GeneratePayrollActivity::class.java)) }
        binding.cardPayrollReports.setOnClickListener { startActivity(Intent(this, PayrollReportsActivity::class.java)) }
        binding.cardLogout.setOnClickListener {
            loginViewModel.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }
    }
}
