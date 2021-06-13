package com.example.gspass_android.ui.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.gspass_android.R
import com.example.gspass_android.adapter.MealAdapter
import com.example.gspass_android.databinding.ActivityMainBinding
import com.example.gspass_android.ui.dialog.MyPageDialog
import com.example.gspass_android.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.coroutines.delay
import okio.blackholeSink
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

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

        val qrCodeImage : ImageView = findViewById(R.id.qrcode)

        val multiFormaWriter = MultiFormatWriter()

        viewModel.nextPassTime()

        try {
            val bitMatrix = multiFormaWriter.encode(viewModel.accessToken, BarcodeFormat.QR_CODE,300,300,)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            qrCodeImage.setImageBitmap(bitmap)
            println("비트맵 실행")
        }catch (e : Exception){
            println(e.message)
        }
        val sheet = findViewById<FrameLayout>(R.id.sheet)
        BottomSheetBehavior.from(sheet).apply {
            peekHeight = 100
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val passButton : ImageButton = findViewById(R.id.pass_button)
        passButton.setOnClickListener {
            viewModel.pass()
        }

        val myPage: ImageButton = findViewById(R.id.my_page)
        myPage.setOnClickListener {
            openMyPage()
        }

        viewModel.passSuccessEvent.observe(this,{
            println("성공했습니다다")
            viewModel.passInfo()
       })

        val mealAdapter = MealAdapter(viewModel)

        viewPager2 = findViewById(R.id.meal_viewpager)

        viewPager2.adapter = mealAdapter
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager2.setCurrentItem(currentPosition, false)
        mealAdapter.pos.observe(this, {
            mealAdapter.pos.value?.let { viewModel.meals(it-1) }
            println("$it 날짜 날짜")
        })

        var firstCheck = 0
        viewModel.successEvent.observe(this, {
            println("성공성공성공${viewModel.morningMenu.value}")
            if(firstCheck ==0){
                mealAdapter.pos.value?.let { viewModel.meals(-1) }
                mealAdapter.pos.value?.let { viewModel.meals(0) }
                mealAdapter.pos.value?.let { viewModel.meals(1) }
                firstCheck++
                mealAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun openMyPage() {
        MyPageDialog(
            this, object : MyPageDialog.OnMemoItemMenuDialogButtonListener {
                override fun onChangePasswordClick() {
                    val intent = Intent(this@MainActivity,ChangePasswordActivity::class.java)
                    startActivity(intent)
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