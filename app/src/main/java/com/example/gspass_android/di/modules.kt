package com.example.gspass_android.di

import com.example.gspass_android.BaseApi
import com.example.gspass_android.pref.LocalStorage
import com.example.gspass_android.pref.SharedPrefStorage
import com.example.gspass_android.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    single <LocalStorage>{ SharedPrefStorage(get()) }

    single { BaseApi() }

    viewModel { LoginViewModel(get(),get()) }
}