package com.example.gspass_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gspass_android.BaseApi

class RegisterViewModel(val api : BaseApi) : ViewModel() {
    private val baseApi = api.getInstance()

}