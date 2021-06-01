package com.example.gspass_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gspass_android.R
import com.example.gspass_android.databinding.ActivityLoginBinding
import com.example.gspass_android.databinding.ActivityRegisterSchoolCodeBinding
import com.example.gspass_android.viewmodel.RegisterSchoolCodeViewModel

class RegisterSchoolCodeActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegisterSchoolCodeBinding
    lateinit var viewModel : RegisterSchoolCodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = RegisterSchoolCodeViewModel()
        binding = ActivityRegisterSchoolCodeBinding.inflate(layoutInflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
    }
}