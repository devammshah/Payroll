package com.payroll.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.payroll.data.entity.PayrollEntity
import com.payroll.data.entity.PayrollWithEmployee

@Dao
interface PayrollDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertPayroll(payroll: PayrollEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM payrolls WHERE employeeId = :employeeId AND month = :month AND year = :year)")
    suspend fun payrollExists(employeeId: Int, month: Int, year: Int): Boolean

    @Transaction
    @Query("SELECT * FROM payrolls ORDER BY generatedDate DESC")
    fun getAllPayrolls(): LiveData<List<PayrollWithEmployee>>

    @Transaction
    @Query(
        """
        SELECT * FROM payrolls
        WHERE (:month = 0 OR month = :month)
          AND (:year = 0 OR year = :year)
          AND (:department = '' OR employeeId IN (SELECT employeeId FROM employees WHERE department = :department))
        ORDER BY generatedDate DESC
        """
    )
    fun getFilteredPayrolls(month: Int, year: Int, department: String): LiveData<List<PayrollWithEmployee>>
}
