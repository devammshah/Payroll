package com.payroll.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class EmployeeEntity(
    @PrimaryKey(autoGenerate = true) val employeeId: Int = 0,
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val department: String,
    val designation: String,
    val basicSalary: Double,
    val hraPercent: Double,
    val daPercent: Double,
    val otherAllowances: Double,
    val pfPercent: Double,
    val taxPercent: Double,
    val bankAccountNumber: String,
    val ifscCode: String
)
