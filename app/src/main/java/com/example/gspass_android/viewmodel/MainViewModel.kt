package com.example.gspass_android.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.gspass_android.BaseApi
import com.example.gspass_android.base.BaseViewModel
import com.example.gspass_android.base.SingleLiveEvent
import com.example.gspass_android.data.MealsData
import com.example.gspass_android.pref.LocalStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.time.LocalDate

class MainViewModel(
    private val sharedPreferences: LocalStorage,
    api: BaseApi
) : BaseViewModel() {

    private val baseApi = api.getInstance()
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

    private val accessToken = sharedPreferences.getAccessToken()

    @RequiresApi(Build.VERSION_CODES.O)
    fun meals(day: Int) {
        today.value = getDate(day)
        println("$accessToken asdfadfad")
        val apiResult = baseApi.meals(accessToken, "$day")

        val disposableSingleObserver = object : DisposableSingleObserver<MealsData>() {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onSuccess(t: MealsData) {
                getDate(0)
                println("데이 ${day}")
                when(day % 5){
                    0->{
                        morningMenu.value = getMealsString(t.breakfast)
                        lunchMenu.value = getMealsString(t.lunch)
                        dinnerMenu.value = getMealsString(t.dinner)
                        println("111111111111")
                    }
                    -1,1 ->{
                        morningMenu2.value = getMealsString(t.breakfast)
                        lunchMenu2.value = getMealsString(t.lunch)
                        dinnerMenu2.value = getMealsString(t.dinner)
                        println("22222222222222")
                    }
                    2,-2->{
                        morningMenu3.value = getMealsString(t.breakfast)
                        lunchMenu3.value = getMealsString(t.lunch)
                        dinnerMenu3.value = getMealsString(t.dinner)
                        println("33333333333333")
                    }
                    3,-3->{
                        morningMenu4.value = getMealsString(t.breakfast)
                        lunchMenu4.value = getMealsString(t.lunch)
                        dinnerMenu4.value = getMealsString(t.dinner)
                        println("444444444444444")
                    }
                    else ->{
                        morningMenu5.value = getMealsString(t.breakfast)
                        lunchMenu5.value = getMealsString(t.lunch)
                        dinnerMenu5.value = getMealsString(t.dinner)
                        println("5555555555555555")
                    }
                }
                successEvent.setValue(Unit)
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
    fun getDate(day : Int): String {
        val today : LocalDate = LocalDate.now()
        println("${today.dayOfMonth} ${today.monthValue} 가나다라")
        val date =  today.dayOfMonth
        val month = today.monthValue
        val reDay = "$month 월$date 일"
        return reDay
    }

    fun logout(){
        sharedPreferences.removeTokens()
    }

    fun getMealsString(meals: ArrayList<String>): String {
        val size = meals.size
        var mealString = ""
        if(size > 1){
            for (i in 0 until size) {
                mealString += meals[i]
                mealString += " "
            }
        }
        else{
            mealString = "급식이 없습니다 ㅜ"
        }
        return mealString
    }
}