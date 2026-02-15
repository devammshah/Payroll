package com.payroll.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PayrollWithEmployee(
    @Embedded val payroll: PayrollEntity,
    @Relation(parentColumn = "employeeId", entityColumn = "employeeId")
    val employee: EmployeeEntity
)
