package com.example.gspass_android

import com.example.gspass_android.data.*
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
    @POST("password")
    fun changePassword(
        @Header("Authorization") accessToken: String,
        @Body changePassword : ChangePasswordData
    ):Single<Unit>

    @POST("./")
    fun pass(
        @Header("Authorization") accessToken: String,
        ):Single<PassData>

    @GET("gspass/time")
    fun getPassNextTime(
        @Header("Authorization") accessToken: String
        ):Single<PassNextTimeData>
}