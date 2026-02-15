package com.payroll.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.payroll.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> = _loginState

    fun validateAndLogin(username: String, password: String) {
        val isSuccess = username == "admin" && password == "admin@123"
        if (isSuccess) {
            sessionManager.setLoggedIn(true)
        }
        _loginState.value = isSuccess
    }

    fun isLoggedIn(): Boolean = sessionManager.isLoggedIn()

    fun logout() = sessionManager.logout()
}
