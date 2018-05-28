package com.firuze.ahmad.demo

import android.app.Application
import android.util.Log
import java.net.InetAddress
import java.net.UnknownHostException

/**
 * Created by antho.firuze@gmail.com on 2018-05-26.
 */

fun isInternetAvailable(): Boolean {
    try {
        val address: InetAddress = InetAddress.getByName("www.google.com")
        return true
    } catch (e: UnknownHostException) {
        // Log error
        Log.d("MainApplication", e.message)
    }

    return false
}


class MainApplication : Application() {

    internal val TAG = "MainApplication"

    override fun onCreate() {
        super.onCreate()
    }
}