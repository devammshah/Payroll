package com.payroll.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.payroll.data.entity.EmployeeEntity

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: EmployeeEntity): Long

    @Update
    suspend fun updateEmployee(employee: EmployeeEntity)

    @Delete
    suspend fun deleteEmployee(employee: EmployeeEntity)

    @Query("SELECT * FROM employees ORDER BY fullName ASC")
    fun getAllEmployees(): LiveData<List<EmployeeEntity>>

    @Query("SELECT * FROM employees WHERE employeeId = :employeeId")
    suspend fun getEmployeeById(employeeId: Int): EmployeeEntity?

    @Query("SELECT * FROM employees WHERE fullName LIKE '%' || :query || '%' ORDER BY fullName ASC")
    fun searchEmployees(query: String): LiveData<List<EmployeeEntity>>
}
