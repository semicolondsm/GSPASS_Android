package com.example.gspass_android

import com.example.gspass_android.data.LoginInfoData
import com.example.gspass_android.data.MealsData
import com.example.gspass_android.data.RegisterInfoData
import com.example.gspass_android.data.TokenData
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {
    @POST("login")
    fun login(
        @Body loginInfo: LoginInfoData
    ):Single<TokenData>

    @POST("register")
    fun register(
        @Body registerInfo : RegisterInfoData
    ):Single<TokenData>

    @GET("meals")
    fun meals(
        @Header("Authorization") accessToken: String,
        @Query("day") day: String,
    ):Single<MealsData>
}