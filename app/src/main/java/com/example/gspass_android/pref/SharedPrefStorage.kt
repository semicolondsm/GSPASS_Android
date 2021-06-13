
package com.example.gspass_android.pref

import android.content.Context
import android.content.SharedPreferences


class SharedPrefStorage(val context: Context) : LocalStorage {

     private val accessTokenKey = "AccessToken"
     private val refreshTokenKey = "RefreshToken"
     private val passState ="State"

     override fun saveAccessToken(token: String) =
             getPref(context).edit().let {
                  it.putString(accessTokenKey, token)
                  it.apply()
             }

     override fun saveRefreshToken(token: String) =
             getPref(context).edit().let {
                  it.putString(accessTokenKey, token)
                  it.apply()
             }

     override fun getAccessToken(): String =
             "Bearer " + getPref(context).getString(accessTokenKey, "")

     override fun getRefreshToken(): String =
             "Bearer " + getPref(context).getString(refreshTokenKey, "")

     override fun removeTokens() =
             getPref(context).edit().let {
                  it.remove(accessTokenKey)
                  it.remove(refreshTokenKey)
                  it.apply()
             }

    override fun changePassState(state: Boolean) =
        getPref(context).edit().let {
            it.putBoolean(passState,state)
            it.apply()
        }

    override fun getPassState() : Boolean =
        getPref(context).getBoolean(passState,true)

    private fun getPref(context: Context): SharedPreferences =
             context.getSharedPreferences("pref", Context.MODE_PRIVATE)
}