package com.example.gspass_android

import android.content.pm.SigningInfo
import com.example.gspass_android.data.LoginInfoData
import com.example.gspass_android.data.TokenData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(
        @Body loginInfo: LoginInfoData
    ):Single<Response<TokenData>>
}