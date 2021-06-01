package com.example.gspass_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gspass_android.R
import com.example.gspass_android.databinding.ActivityLoginBinding
import com.example.gspass_android.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var viewModel : LoginViewModel
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = LoginViewModel()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
    }
}