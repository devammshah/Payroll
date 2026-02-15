package com.payroll.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "payrolls",
    foreignKeys = [
        ForeignKey(
            entity = EmployeeEntity::class,
            parentColumns = ["employeeId"],
            childColumns = ["employeeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["employeeId", "month", "year"], unique = true)]
)
data class PayrollEntity(
    @PrimaryKey(autoGenerate = true) val payrollId: Int = 0,
    val employeeId: Int,
    val month: Int,
    val year: Int,
    val grossSalary: Double,
    val deductions: Double,
    val netSalary: Double,
    val generatedDate: Long
)
