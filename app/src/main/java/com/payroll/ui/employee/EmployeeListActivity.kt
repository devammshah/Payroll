package com.payroll.ui.employee

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.payroll.databinding.ActivityEmployeeListBinding
import com.payroll.viewmodel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeListBinding
    private val viewModel: EmployeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = EmployeeAdapter(
            onEdit = {
                startActivity(Intent(this, EmployeeFormActivity::class.java).putExtra("employee_id", it.employeeId))
            },
            onDelete = {
                viewModel.deleteEmployee(it)
                Snackbar.make(binding.root, "Employee deleted", Snackbar.LENGTH_SHORT).show()
            }
        )

        binding.recyclerEmployees.layoutManager = LinearLayoutManager(this)
        binding.recyclerEmployees.adapter = adapter

        viewModel.employees.observe(this) {
            adapter.submitList(it)
            binding.emptyState.root.visibility = if (it.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }

        binding.etSearch.doAfterTextChanged {
            val query = it.toString()
            val source = if (query.isBlank()) viewModel.employees else viewModel.search(query)
            source.observe(this) { list -> adapter.submitList(list) }
        }

        binding.fabAdd.setOnClickListener { startActivity(Intent(this, EmployeeFormActivity::class.java)) }
    }
}
