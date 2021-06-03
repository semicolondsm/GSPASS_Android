package com.example.gspass_android.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.media.session.MediaSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.gspass_android.MainActivity
import com.example.gspass_android.R
import com.example.gspass_android.data.TokenData
import com.example.gspass_android.databinding.ActivityLoginBinding
import com.example.gspass_android.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    val viewModel : LoginViewModel by viewModel()
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        val loginId : EditText=  findViewById(R.id.id)
        val loginPassword : EditText = findViewById(R.id.passward)

        val loginButton : ImageButton = findViewById(R.id.login_button)


        loginButton.setOnClickListener {
            if(loginId.toString() !="" && loginPassword.toString() !="" ){
                viewModel.login(loginId.toString(),loginPassword.toString())
            }
            else{
                println("아이디 비번을 입력하세요")
            }
        }
        viewModel.successEvent.observe(this,{
            Toast.makeText(this,"로그인에 성공하였습니다",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
        viewModel.failEvent.observe(this,{
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })

        val joinPass : ImageButton = findViewById(R.id.join_pass)
        joinPass.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}