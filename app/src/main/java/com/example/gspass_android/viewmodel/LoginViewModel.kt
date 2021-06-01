package com.example.gspass_android.viewmodel

import android.annotation.SuppressLint
import android.media.session.MediaSession
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gspass_android.BaseApi
import com.example.gspass_android.data.LoginInfoData
import com.example.gspass_android.data.TokenData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel(){

    val loginId = MutableLiveData<String>()
    val loginPassword = MutableLiveData<String>()

    private val baseApi = BaseApi.getInstance()


    @SuppressLint("CheckResult")
    fun loginClick(){
        val id =loginId.value
        val passward = loginPassword.value
        if(id != null && passward != null){
            baseApi.login(LoginInfoData(id,passward))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError{
                        println(it.message)
                    }
                    .subscribe { response ->
                        println(response.raw())
                        println("성공")
                    }
        }
        else{
            println("아이디 비번을 입력해주세요")
        }
    }
}