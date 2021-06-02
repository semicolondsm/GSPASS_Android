package com.example.gspass_android.ui

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.viewpager2.widget.ViewPager2
import com.example.gspass_android.MainActivity
import com.example.gspass_android.R
import com.example.gspass_android.adapter.RegisterAdapter
import com.example.gspass_android.databinding.ActivityRegisterBinding
import com.example.gspass_android.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegisterBinding
    lateinit var viewModel : RegisterViewModel
    lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = RegisterViewModel()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)


        val nextButton : ImageButton = findViewById(R.id.nextBtn)
        val backButton : ImageButton = findViewById(R.id.backBtn)
        viewPager2 = findViewById(R.id.viewPager2)

        viewPager2.adapter = RegisterAdapter(this)

        nextButton.setOnClickListener {
            println(viewModel.schoolCode.value)
            if(viewPager2.currentItem ==3){
                if(viewModel.checkPassword == viewModel.password){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }else{
                when(viewPager2.currentItem){
                    0 -> if(viewModel.schoolCode.value != null) viewPager2.currentItem = viewPager2.currentItem +1
                }
            }
        }

        backButton.setOnClickListener {
            if(viewPager2.currentItem == 0) {
                super.onBackPressed()
            }else {
                viewPager2.currentItem = viewPager2.currentItem - 1
            }
        }
    }

    override fun onBackPressed() {
        if(viewPager2.currentItem == 0) {
            super.onBackPressed()
        }else {
            viewPager2.currentItem = viewPager2.currentItem - 1
        }
    }


}