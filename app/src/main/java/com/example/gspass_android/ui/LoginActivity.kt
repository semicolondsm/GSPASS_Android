package com.example.gspass_android.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
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

        val joinPass : ImageButton = findViewById(R.id.join_pass)
        joinPass.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}