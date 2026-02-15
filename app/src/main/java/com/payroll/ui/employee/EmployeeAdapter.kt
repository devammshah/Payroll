package com.payroll.ui.employee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.payroll.data.entity.EmployeeEntity
import com.payroll.databinding.ItemEmployeeBinding

class EmployeeAdapter(
    private val onEdit: (EmployeeEntity) -> Unit,
    private val onDelete: (EmployeeEntity) -> Unit
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private val items = mutableListOf<EmployeeEntity>()

    fun submitList(data: List<EmployeeEntity>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) = holder.bind(items[position])

    inner class EmployeeViewHolder(private val binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EmployeeEntity) {
            binding.tvName.text = item.fullName
            binding.tvDetails.text = "${item.department} • ${item.designation}"
            binding.tvSalary.text = "Basic ₹${"%.2f".format(item.basicSalary)}"
            binding.btnEdit.setOnClickListener { onEdit(item) }
            binding.btnDelete.setOnClickListener { onDelete(item) }
        }
    }
}
