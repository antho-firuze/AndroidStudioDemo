package com.firuze.ahmad.demo

import android.app.Application
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayInputStream
import java.io.InputStreamReader

/**
 * Created by antho.firuze@gmail.com on 2018-05-26.
 */

fun ajaxRequest(method: String = "GET", url: String, params: Map<String, Any>): Map<String, Any> {
    val jsonParams = Gson().toJson(params)

    when (method) {
        "POST" -> {
            val (req, resp, res) =
                    Fuel.post(url).body(jsonParams.toString()).responseString()

            when (resp.statusCode) {
                200 -> {
                    val (dataRes, _) = res
                    val data: Map<String, Any> = Gson().fromJson(dataRes, object : TypeToken<Map<String, Any>>() {}.type)
                    Log.d("AJAX", "${resp.statusCode} ${resp.responseMessage}: $data")
                    return mapOf("status" to true, "result" to data)
                }
                in 400..419 -> {
                    val input = InputStreamReader(ByteArrayInputStream(resp.data))
                    val error: Map<String, Any> = Gson().fromJson(input, object : TypeToken<Map<String, Any>>() {}.type)
                    Log.d("AJAX", "${resp.statusCode} ${resp.responseMessage}: $error")
                    return mapOf("status" to false, "message" to "${resp.statusCode} ${resp.responseMessage}: $error")
                }
                else -> {
                    Log.d("AJAX", "${resp.statusCode} ${resp.responseMessage}")
                    return mapOf("status" to false, "message" to "${resp.statusCode} ${resp.responseMessage}")
                }
            }
        }
    }
    return mapOf("status" to false, "message" to "Request Failed !")
}

class MainApplication : Application() {

    companion object {
        lateinit var instance: MainApplication private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}