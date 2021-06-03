package com.example.gspass_android.viewmodel

import android.annotation.SuppressLint
import android.content.Intent

import com.example.gspass_android.BaseApi
import com.example.gspass_android.MainActivity
import com.example.gspass_android.base.BaseViewModel
import com.example.gspass_android.base.SingleLiveEvent
import com.example.gspass_android.data.LoginInfoData
import com.example.gspass_android.data.TokenData
import com.example.gspass_android.pref.LocalStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class LoginViewModel(
    private val sharedPreferences: LocalStorage,
    api: BaseApi
) : BaseViewModel() {

    private val baseApi = api.getInstance()
    val successEvent = SingleLiveEvent<Unit>()
    val failEvent = SingleLiveEvent<String>()

    fun login(id: String, password: String) {
        val apiResult = baseApi.login(LoginInfoData(id, password))
        val disposableSingleObserver = object : DisposableSingleObserver<TokenData>() {

            override fun onSuccess(t: TokenData) {
                sharedPreferences.saveAccessToken(t.access_token)
                sharedPreferences.saveRefreshToken(t.refresh_token)
                successEvent.setValue(Unit)
            }

            override fun onError(e: Throwable) {
                println("실패")
                when (e) {
                    is HttpException -> when (e.code()) {
                        404 -> failEvent.setValue("아이디와 비밀번호를 확인해 주세요")
                        500 -> failEvent.setValue("서버 오류가 발생하였습니다")
                        else ->failEvent.setValue("알 수 없는 에러가 발생하였습니다")
                    }
                }
            }
        }
        val observer = apiResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(disposableSingleObserver)

        addDisposable(observer)
    }

}
