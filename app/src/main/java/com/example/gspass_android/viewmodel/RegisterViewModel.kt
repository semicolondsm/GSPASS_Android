package com.example.gspass_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    val schoolCode = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val checkPassword = MutableLiveData<String>()
    val schoolGcn = MutableLiveData<String>()
    val schoolYear = MutableLiveData<String>()


}