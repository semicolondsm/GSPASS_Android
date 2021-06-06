package com.example.gspass_android.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.gspass_android.R
import com.example.gspass_android.adapter.MealAdapter
import com.example.gspass_android.databinding.ActivityMainBinding
import com.example.gspass_android.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.time.days

class MainActivity : AppCompatActivity() {

    private var currentPosition = Int.MAX_VALUE / 2

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        val mealAdapter = MealAdapter(viewModel)
        mealAdapter.pos.value?.let { viewModel.meals(0) }

        mealAdapter.pos.observe(this, {
            println("이게 문제가 맞나요???????")
            mealAdapter.pos.value?.let { viewModel.meals(it) }
            println("$it 날짜 날짜")
        })

        viewPager2 = findViewById(R.id.meal_viewpager)

        viewPager2.adapter = mealAdapter
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager2.setCurrentItem(currentPosition, false)

        viewModel.successEvent.observe(this, {
            println("성공성공성공${viewModel.morningMenu.value}")
        })
    }
}