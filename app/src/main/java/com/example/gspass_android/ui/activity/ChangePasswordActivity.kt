package com.example.gspass_android.ui.activity

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.gspass_android.R
import com.example.gspass_android.databinding.ActivityChangePasswordBinding
import com.example.gspass_android.viewmodel.ChangePasswordViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordActivity : AppCompatActivity() {

    private val viewModel : ChangePasswordViewModel by viewModel()
    lateinit var binding : ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_change_password)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        val oldPassword : EditText = findViewById(R.id.old_password)
        val newPassword : EditText = findViewById(R.id.new_password)
        val checkPassword : EditText = findViewById(R.id.check_password)

        val nextButton : ImageButton = findViewById(R.id.nextBtn)
        nextButton.setOnClickListener {
            if(newPassword.text.toString() != checkPassword.text.toString()){
                Toast.makeText(this,"비밀번호와 비밀번호 확인이 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.changePassword(oldPassword.text.toString(),newPassword.text.toString())
            }
        }
        viewModel.successEvent.observe(this,{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"비밀번호가 변경되었습니다", Toast.LENGTH_SHORT).show()
        })
    }
}