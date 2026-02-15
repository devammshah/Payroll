package com.payroll.ui.payroll

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.payroll.databinding.ActivityPayrollReportsBinding
import com.payroll.viewmodel.PayrollViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PayrollReportsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayrollReportsBinding
    private val viewModel: PayrollViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayrollReportsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PayrollAdapter()
        binding.recyclerPayrollReports.layoutManager = LinearLayoutManager(this)
        binding.recyclerPayrollReports.adapter = adapter

        fun load() {
            val month = binding.etFilterMonth.text.toString().toIntOrNull() ?: 0
            val year = binding.etFilterYear.text.toString().toIntOrNull() ?: 0
            val dept = binding.etFilterDept.text.toString().trim()
            viewModel.getFilteredPayrolls(month, year, dept).observe(this) { list ->
                adapter.submitList(list)
                val totalPayout = list.sumOf { it.payroll.netSalary }
                val totalTax = list.sumOf { it.payroll.deductions }
                binding.tvSummary.text = "Total Payout: ₹${"%.2f".format(totalPayout)} | Total Deductions: ₹${"%.2f".format(totalTax)}"
            }
        }

        binding.btnApplyFilters.setOnClickListener { load() }
        load()
    }
}
