package com.example.gspass_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterSchoolCodeViewModel : ViewModel() {

    val schoolCode = MutableLiveData<String>()

    fun onClickNextButton(){
        println(schoolCode.value)
    }
}