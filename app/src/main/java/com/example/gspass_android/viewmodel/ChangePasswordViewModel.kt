package com.example.gspass_android.viewmodel

import com.example.gspass_android.BaseApi
import com.example.gspass_android.base.BaseViewModel
import com.example.gspass_android.base.SingleLiveEvent
import com.example.gspass_android.data.ChangePasswordData
import com.example.gspass_android.data.LoginInfoData
import com.example.gspass_android.data.TokenData
import com.example.gspass_android.pref.LocalStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class ChangePasswordViewModel(
    private val sharedPreferences: LocalStorage,
    api: BaseApi
) : BaseViewModel() {
    private val baseApi = api.getInstance()
    val successEvent = SingleLiveEvent<Unit>()
    val failEvent = SingleLiveEvent<String>()

    private val accessToken = sharedPreferences.getAccessToken()
    fun changePassword(oldPassword: String, newPassword: String) {
        val apiResult = baseApi.changePassword(accessToken,
            ChangePasswordData(oldPassword, newPassword)
        )
        val disposableSingleObserver = object : DisposableSingleObserver<Unit>() {

            override fun onSuccess(t: Unit) {
                println("성공")
                successEvent.setValue(Unit)
            }

            override fun onError(e: Throwable) {
                println("실패")
                when (e) {
                    is HttpException -> when (e.code()) {
                        204 ->successEvent.setValue(Unit).apply {
                            println("성공")
                        }
                        404 -> failEvent.setValue("아이디와 비밀번호를 확인해 주세요")
                        500 -> failEvent.setValue("서버 오류가 발생하였습니다")
                        else ->failEvent.setValue("알 수 없는 에러가 발생하였습니다")
                    }
                    else -> {
                        sharedPreferences.removeTokens()
                        successEvent.setValue(Unit)
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