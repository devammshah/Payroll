package com.payroll.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.payroll.data.dao.EmployeeDao
import com.payroll.data.dao.PayrollDao
import com.payroll.data.entity.EmployeeEntity
import com.payroll.data.entity.PayrollEntity

@Database(entities = [EmployeeEntity::class, PayrollEntity::class], version = 1, exportSchema = false)
abstract class PayrollDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
    abstract fun payrollDao(): PayrollDao
}
