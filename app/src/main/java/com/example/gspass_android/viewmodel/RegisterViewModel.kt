package com.example.gspass_android.viewmodel

import com.example.gspass_android.BaseApi
import com.example.gspass_android.base.BaseViewModel
import com.example.gspass_android.base.SingleLiveEvent
import com.example.gspass_android.data.RegisterInfoData
import com.example.gspass_android.data.TokenData
import com.example.gspass_android.pref.LocalStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class RegisterViewModel(private val sharedPreferences : LocalStorage, api : BaseApi) : BaseViewModel() {

    private val baseApi = api.getInstance()
    val successEvent = SingleLiveEvent<Unit>()
    val failEvent = SingleLiveEvent<String>()

    fun register(id : String, password : String, gcn : String, entry_year : String, random_code :String){
        val apiResult = baseApi.register(RegisterInfoData(id,password,gcn,entry_year, random_code))
        val disposableSingleObserver = object : DisposableSingleObserver<TokenData>(){
            override fun onSuccess(t: TokenData) {
                sharedPreferences.saveAccessToken(t.access_token)
                sharedPreferences.saveRefreshToken(t.refresh_token)
                successEvent.setValue(Unit)
            }
            override fun onError(e: Throwable) {
                when(e){
                    is HttpException -> when(e.code()){
                        500 -> failEvent.setValue("서버 오류가 발생하였습니다")
                        404 -> failEvent.setValue("학교를 찾을 수 없습니다")
                        else ->failEvent.setValue("알 수 없는 오류가 발생하였습니다")
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