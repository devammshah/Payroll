package com.payroll.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.payroll.databinding.ActivityLoginBinding
import com.payroll.ui.dashboard.DashboardActivity
import com.payroll.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (viewModel.isLoggedIn()) {
            openDashboard()
            return
        }

        binding.btnLogin.setOnClickListener {
            viewModel.validateAndLogin(
                binding.etUsername.text.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
        }

        viewModel.loginState.observe(this) {
            if (it) openDashboard() else Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}
