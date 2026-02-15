package com.payroll.utils

import android.util.Patterns

object Validators {
    fun isValidEmail(value: String) = Patterns.EMAIL_ADDRESS.matcher(value).matches()
    fun isNotBlank(value: String) = value.trim().isNotEmpty()
}
