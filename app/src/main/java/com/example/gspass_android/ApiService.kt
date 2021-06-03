package com.example.gspass_android

import com.example.gspass_android.data.LoginInfoData
import com.example.gspass_android.data.TokenData
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(
        @Body loginInfo: LoginInfoData
    ):Single<TokenData>

  
}