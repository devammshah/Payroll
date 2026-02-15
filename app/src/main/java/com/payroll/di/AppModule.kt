package com.payroll.di

import android.content.Context
import androidx.room.Room
import com.payroll.data.dao.EmployeeDao
import com.payroll.data.dao.PayrollDao
import com.payroll.data.database.PayrollDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePayrollDatabase(@ApplicationContext context: Context): PayrollDatabase {
        return Room.databaseBuilder(context, PayrollDatabase::class.java, "payroll_db").build()
    }

    @Provides
    fun provideEmployeeDao(database: PayrollDatabase): EmployeeDao = database.employeeDao()

    @Provides
    fun providePayrollDao(database: PayrollDatabase): PayrollDao = database.payrollDao()
}
