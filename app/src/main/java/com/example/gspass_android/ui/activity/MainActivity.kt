package com.example.gspass_android.ui.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.gspass_android.R
import com.example.gspass_android.adapter.MealAdapter
import com.example.gspass_android.databinding.ActivityMainBinding
import com.example.gspass_android.ui.dialog.MyPageDialog
import com.example.gspass_android.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var currentPosition = Int.MAX_VALUE / 2

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager2: ViewPager2


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        val myPage: ImageButton = findViewById(R.id.my_page)
        myPage.setOnClickListener {
            openMyPage()
        }

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


    private fun openMyPage() {
        MyPageDialog(
            this, object : MyPageDialog.OnMemoItemMenuDialogButtonListener {

                override fun onChangePasswordClick() {
                    println("1번 눌름")
                }

                override fun onLogoutClick() {
                    viewModel.logout()
                    val intent = Intent(this@MainActivity,LoginActivity::class.java)
                    startActivity(intent)
                }

                override fun onCancelClick() {
                    println("3번 눌름")
                }
            }).callDialog()
    }
}