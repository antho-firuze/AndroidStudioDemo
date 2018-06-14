package com.firuze.ahmad.demo

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectivityStatus {

    fun isConnected(context: Context) : Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun getInfo(context: Context) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isWiFi: Boolean = activeNetwork?.type == ConnectivityManager.TYPE_WIFI
    }
}

