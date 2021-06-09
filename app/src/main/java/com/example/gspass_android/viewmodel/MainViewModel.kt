package com.example.gspass_android.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.gspass_android.BaseApi
import com.example.gspass_android.adapter.MealAdapter
import com.example.gspass_android.base.BaseViewModel
import com.example.gspass_android.base.SingleLiveEvent
import com.example.gspass_android.data.MealsData
import com.example.gspass_android.data.PassData
import com.example.gspass_android.data.PassNextTimeData
import com.example.gspass_android.pref.LocalStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(
    private val sharedPreferences: LocalStorage,
    api: BaseApi
) : BaseViewModel() {

    private val baseApi = api.getInstance()
    val passSuccessEvent = SingleLiveEvent<Unit>()
    val nextTineSuccessEvent = SingleLiveEvent<Unit>()
    val successEvent = SingleLiveEvent<Unit>()
    val failEvent = SingleLiveEvent<String>()

    val morningMenu = MutableLiveData<String>()
    val morningMenu2 = MutableLiveData<String>()
    val morningMenu3 = MutableLiveData<String>()
    val morningMenu4 = MutableLiveData<String>()
    val morningMenu5 = MutableLiveData<String>()

    val lunchMenu = MutableLiveData<String>()
    val lunchMenu2 = MutableLiveData<String>()
    val lunchMenu3 = MutableLiveData<String>()
    val lunchMenu4 = MutableLiveData<String>()
    val lunchMenu5 = MutableLiveData<String>()

    val dinnerMenu = MutableLiveData<String>()
    val dinnerMenu2 = MutableLiveData<String>()
    val dinnerMenu3 = MutableLiveData<String>()
    val dinnerMenu4 = MutableLiveData<String>()
    val dinnerMenu5 = MutableLiveData<String>()

    val today = MutableLiveData<String>()

    val passTime = MutableLiveData<String>()

    val accessToken = sharedPreferences.getAccessToken()
    private val mealAdapter = MealAdapter(this)

    fun nextPassTime(){
        val apiResult = baseApi.getPassNextTime(accessToken)
        val disposableSingleObserver = object : DisposableSingleObserver<PassNextTimeData>(){

            override fun onSuccess(t: PassNextTimeData) {
                nextTineSuccessEvent.setValue(Unit)
                passTime.value = "PASS 발급은${t.gsPassTime} 부터 시작됩니다."
            }

            override fun onError(e: Throwable) {
                when (e) {
                    is HttpException -> when (e.code()) {
                        404 -> failEvent.setValue("아이디와 비밀번호를 확인해 주세요")
                        500 -> failEvent.setValue("서버 오류가 발생하였습니다")
                        else -> failEvent.setValue("알 수 없는 에러가 발생하였습니다")
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

    fun pass(){
        val apiResult = baseApi.pass(accessToken)
        val disposableSingleObserver = object : DisposableSingleObserver<PassData>(){

            override fun onSuccess(t: PassData) {
                println("${t.previous_count} ${t.time_remaining.hour}")
                passSuccessEvent.setValue(Unit)
            }

            override fun onError(e: Throwable) {
                println("실패")
                when (e) {
                    is HttpException -> when (e.code()) {
                        404 -> failEvent.setValue("아이디와 비밀번호를 확인해 주세요")
                        500 -> failEvent.setValue("서버 오류가 발생하였습니다")
                        else -> failEvent.setValue("알 수 없는 에러가 발생하였습니다")
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun meals(day: Int) {
        val apiResult = baseApi.meals(accessToken, "${day}")

        val disposableSingleObserver = object : DisposableSingleObserver<MealsData>() {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onSuccess(t: MealsData) {
                today.value = getPlusDate(day)
                println("이것만 해결하면 끝ㅌㅌ$day")
                var checkDay = 0
                if (day == 0) {
                    today.value = "오늘의 급식"
                    morningMenu.value = getMealsString(t.breakfast)
                    lunchMenu.value = getMealsString(t.lunch)
                    dinnerMenu.value = getMealsString(t.dinner)
                } else if (day > 0) {
                    checkDay = day
                    when (checkDay % 5) {
                        0 -> {
                            morningMenu.value = getMealsString(t.breakfast)
                            lunchMenu.value = getMealsString(t.lunch)
                            dinnerMenu.value = getMealsString(t.dinner)
                        }
                        -1, 1 -> {
                            morningMenu2.value = getMealsString(t.breakfast)
                            lunchMenu2.value = getMealsString(t.lunch)
                            dinnerMenu2.value = getMealsString(t.dinner)
                        }
                        2, -2 -> {
                            morningMenu3.value = getMealsString(t.breakfast)
                            lunchMenu3.value = getMealsString(t.lunch)
                            dinnerMenu3.value = getMealsString(t.dinner)
                        }
                        3, -3 -> {
                            morningMenu4.value = getMealsString(t.breakfast)
                            lunchMenu4.value = getMealsString(t.lunch)
                            dinnerMenu4.value = getMealsString(t.dinner)
                        }
                        else -> {
                            morningMenu5.value = getMealsString(t.breakfast)
                            lunchMenu5.value = getMealsString(t.lunch)
                            dinnerMenu5.value = getMealsString(t.dinner)
                        }
                    }
                } else {
                    checkDay = day - day - day
                    when (checkDay % 5) {
                        0 -> {
                            morningMenu.value = getMealsString(t.breakfast)
                            lunchMenu.value = getMealsString(t.lunch)
                            dinnerMenu.value = getMealsString(t.dinner)
                        }
                        4 -> {
                            morningMenu2.value = getMealsString(t.breakfast)
                            lunchMenu2.value = getMealsString(t.lunch)
                            dinnerMenu2.value = getMealsString(t.dinner)
                        }
                        3 -> {
                            morningMenu3.value = getMealsString(t.breakfast)
                            lunchMenu3.value = getMealsString(t.lunch)
                            dinnerMenu3.value = getMealsString(t.dinner)
                        }
                        2 -> {
                            morningMenu4.value = getMealsString(t.breakfast)
                            lunchMenu4.value = getMealsString(t.lunch)
                            dinnerMenu4.value = getMealsString(t.dinner)
                        }
                        1 -> {
                            morningMenu5.value = getMealsString(t.breakfast)
                            lunchMenu5.value = getMealsString(t.lunch)
                            dinnerMenu5.value = getMealsString(t.dinner)
                        }
                    }
                }
                successEvent.setValue(Unit)
                mealAdapter.notifyDataSetChanged()
            }

            override fun onError(e: Throwable) {
                println("실패")
                when (e) {
                    is HttpException -> when (e.code()) {
                        500 -> failEvent.setValue("서버 오류가 발생하였습니다")
                        else -> failEvent.setValue("알 수 없는 에러가 발생하였습니다")
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

    @RequiresApi(Build.VERSION_CODES.O)

    fun getPlusDate(day: Int): String {
        val today: LocalDate = LocalDate.now()
        var date = 0
        var month = 0
        val changeDay = today.plusDays(day.toLong())
        date = changeDay.dayOfMonth
        month = changeDay.monthValue
        val reDay = "${month}월 ${date}일"
        return reDay
    }

    fun logout() {
        sharedPreferences.removeTokens()
    }

    fun getMealsString(meals: ArrayList<String>): String {
        val size = meals.size
        var mealString = ""
        if(meals.isEmpty()){
            mealString = "급식이 없습니다 ㅜㅜㅜ"
        }
        if (size > 1) {
            for (i in 0 until size) {
                mealString += meals[i]
                mealString += " "
            }
        }
        return mealString
    }
}