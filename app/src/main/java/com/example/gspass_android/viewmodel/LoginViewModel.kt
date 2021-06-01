package com.example.gspass_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
    val loginId = MutableLiveData<String>()
    val loginPassword = MutableLiveData<String>()



    fun loginClick(){
        println(loginId.value)
        println(loginPassword.value)
    }
}