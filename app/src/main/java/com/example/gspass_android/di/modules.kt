package com.example.gspass_android.di

import com.example.gspass_android.BaseApi
import com.example.gspass_android.adapter.MealAdapter
import com.example.gspass_android.pref.LocalStorage
import com.example.gspass_android.pref.SharedPrefStorage
import com.example.gspass_android.viewmodel.ChangePasswordViewModel
import com.example.gspass_android.viewmodel.LoginViewModel
import com.example.gspass_android.viewmodel.MainViewModel
import com.example.gspass_android.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    single <LocalStorage>{ SharedPrefStorage(get()) }

    single { BaseApi() }

    single { MealAdapter(get()) }

    viewModel { ChangePasswordViewModel(get(),get()) }
    viewModel { MainViewModel(get(),get()) }
    viewModel { RegisterViewModel(get(),get()) }
    viewModel { LoginViewModel(get(),get()) }
}