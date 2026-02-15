package com.payroll.ui.payroll

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.payroll.data.entity.PayrollWithEmployee
import com.payroll.databinding.ItemPayrollBinding

class PayrollAdapter : RecyclerView.Adapter<PayrollAdapter.PayrollViewHolder>() {
    private val items = mutableListOf<PayrollWithEmployee>()

    fun submitList(data: List<PayrollWithEmployee>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayrollViewHolder {
        val binding = ItemPayrollBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PayrollViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PayrollViewHolder, position: Int) = holder.bind(items[position])

    inner class PayrollViewHolder(private val binding: ItemPayrollBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PayrollWithEmployee) {
            binding.tvPayrollTitle.text = "${item.employee.fullName} - ${item.payroll.month}/${item.payroll.year}"
            binding.tvPayrollSubtitle.text = "Dept: ${item.employee.department} | Net: ₹${"%.2f".format(item.payroll.netSalary)}"
        }
    }
}
