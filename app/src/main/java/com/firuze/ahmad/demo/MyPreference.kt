package com.firuze.ahmad.demo

import android.content.Context

/**
 * Created by antho.firuze@gmail.com on 2018-05-26.
 */
class MyPreference(context: Context){

    val PREF_NAME = "SharedPreferenceDemo"
//    val PREF_ID = "ID"
//    val PREF_TITLE = "Title"
    val PREF_LOGIN_COUNT = "LoginCount"
    val PREF_BACKPRESSED_COUNT = "BackPressedCount"
    val preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getCount(KEY: String) : Int {
        return preference.getInt(KEY, 0)
    }

    fun setCount(KEY: String, count: Int) {
        preference.edit().putInt(KEY, count).apply()
    }

//    fun getLoginCount() : Int {
//        return preference.getInt(PREF_LOGIN_COUNT, 0)
//    }
//
//    fun setLoginCount(count: Int) {
//        preference.edit().putInt(PREF_LOGIN_COUNT, count).apply()
//    }
}