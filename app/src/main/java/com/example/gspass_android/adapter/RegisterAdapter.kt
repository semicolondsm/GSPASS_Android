package com.example.gspass_android.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gspass_android.ui.fragment.RegisterIdFragment
import com.example.gspass_android.ui.fragment.RegisterPasswordFragment
import com.example.gspass_android.ui.fragment.RegisterSchoolCodeFragment
import com.example.gspass_android.ui.fragment.RegisterUserInfoFragment

class RegisterAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    val registerSchoolCodeFragment = RegisterSchoolCodeFragment()
    val registerUserInfoFragment = RegisterUserInfoFragment()
    val registerIdFragment = RegisterIdFragment()
    val registerPasswordFragment = RegisterPasswordFragment()

    companion object {
        // 페이지 개수를 정적 변수로 선언
        private const val NUM_PAGES = 4
    }

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        if(position == 0) {
            return registerSchoolCodeFragment
        }else if(position == 1) {
            return registerUserInfoFragment
        }else if(position==2){
            return registerIdFragment
        }else{
            return registerPasswordFragment
        }
    }

}