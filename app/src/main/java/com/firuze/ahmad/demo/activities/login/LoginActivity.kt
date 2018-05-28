package com.firuze.ahmad.demo.activities.login

import android.os.Bundle
import android.provider.Contacts
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.Patterns
import android.view.View
import com.firuze.ahmad.demo.MyConfig
import com.firuze.ahmad.demo.MyPreference
import com.firuze.ahmad.demo.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.a_login.*
import kotlinx.coroutines.experimental.launch
import org.json.JSONObject

/**
 * Created by antho.firuze@gmail.com on 2018-05-25.
 */

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "Login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_login)

//        btn_login.setOnClickListener(this)
        btn_login.setOnClickListener{
            Fuel.post(MyConfig.URL_LOGIN).authenticate(txt_username.text.toString(), txt_pass.text.toString()).responseJson { _, response, result ->

                result.success { json ->
                    Log.d(TAG, "Got response $json")
                    val code: String = json.obj().getString("code")
                    if (code == "200") {
//                        success = true
                    }
                }
                result.failure {
                    Log.d(TAG, "FAILURE : $result")
                }

//                val (data, error) = result
//                when (response.statusCode) {
//                    200 -> {
//                        Log.d(TAG, data.toString())
//                        txt_data.text = data.toString()
////                        val obj = json!!.obj()
////                        Log.d(TAG, obj.getString("username"))
////                        txt_data.text = obj.getString("username")
//                    }
//                    in 400..419 -> {
//                        Log.d(TAG, data.toString())
////                        val obj = json!!.obj()
////                        Log.d(TAG, response.statusCode.toString() + ": " + obj.getString("message"))
////                        txt_error.text = response.statusCode.toString() + ": " + obj.getString("message")
//                    }
//                    else -> {
//                        Log.d(TAG, response.statusCode.toString() + ": " + response.responseMessage)
////                        txt_error.text = response.statusCode.toString() + ": " + response.responseMessage
//                    }
//                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                processLoginCount()
//                var res = ajaxRequest("LOGIN", MyConfig.URL_LOGIN, arrayOf(txt_username.text.toString(), txt_pass.text.toString()))
//                var res: Map<String, Any> = ajaxRequest("LOGIN", MyConfig.URL_LOGIN, arrayOf(txt_username.text.toString(), txt_pass.text.toString()))
                launch() {
                    val token = executeCoroutine()
                    token.success { f -> txt_data.text = f.toString() }
                }
            }
        }
    }

    private suspend fun executeCoroutine() = getToken()

    private suspend fun getToken() = MyConfig.URL_LOGIN.httpGet().responseString().third

    fun ajaxRequest(method: String = "GET", url: String, params: Array<String>) : String{
        var j: String = ""
        when (method) {
            "LOGIN" -> {
                var json = JSONObject()
                json.put("username", params[0]).put("password", params[1])

//                val (_, response, result) = url.httpPost().authenticate(params[0], params[1]).responseString()
                val (_, response, result) = url.httpPost().body(json.toString()).responseString()
                val (data, error) = result
                when (response.statusCode) {
                    200 -> {
                        Log.d(TAG, data.toString())
                        j = data.toString()
//                            Log.d(TAG, json?.array().toString())
//                            j = json?.array().toString()
//                            obj = json!!.obj()
//                            Log.d(TAG, obj.getString("username"))
//                            txt_data.text = obj.get("username").toString()
//                            txt_data.text = obj.getString("username")
                    }
                    in 400..419 -> {
                        Log.d(TAG, data.toString())
                        j = data.toString()
//                            Log.d(TAG, json?.array().toString())
//                            j = json?.array().toString()
//                            obj = json!!.obj()
//                            Log.d(TAG, response.statusCode.toString() + ": " + obj.getString("message"))
//                            return mapOf(response.statusCode.toString())
                    }
                    else -> {
                        Log.d(TAG, response.statusCode.toString() + ": " + response.responseMessage)
                    }
                }
//                Fuel.post(url).authenticate(params[0], params[1]).responseString { _, response, result ->
////                    val (json, error) = result
//                    val (data, error) = result
//                    when (response.statusCode) {
//                        200 -> {
//                            Log.d(TAG, data.toString())
//                            data
////                            Log.d(TAG, json?.array().toString())
////                            j = json?.array().toString()
////                            obj = json!!.obj()
////                            Log.d(TAG, obj.getString("username"))
////                            txt_data.text = obj.get("username").toString()
////                            txt_data.text = obj.getString("username")
//                        }
//                        in 400..419 -> {
//                            Log.d(TAG, data.toString())
//                            j = data.toString()
////                            Log.d(TAG, json?.array().toString())
////                            j = json?.array().toString()
////                            obj = json!!.obj()
////                            Log.d(TAG, response.statusCode.toString() + ": " + obj.getString("message"))
////                            return mapOf(response.statusCode.toString())
//                        }
//                        else -> {
//                            Log.d(TAG, response.statusCode.toString() + ": " + response.responseMessage)
//                        }
//                    }
//                }
            }
        }
//        Log.d(TAG, j)
//        val gson = Gson()
//        return gson.fromJson(j, object : TypeToken<Map<String, Any>>() {}.type)
        return j
    }

//    private fun ajaxRequest(method: String = "GET", url: String, params: Array<String>) {
//        processLoginCount()
//
//        Fuel.post(MyConfig.URL_LOGIN).authenticate(txt_username.text.toString(), txt_pass.text.toString()).responseJson { request, response, result ->
//            val (json, error) = result
//            when (response.statusCode) {
//                200 -> {
//                    val obj = json!!.obj()
//                    Log.d(TAG, obj.getString("username"))
//                    txt_data.text = obj.getString("username")
//                }
//                in 400..419 -> {
//                    val obj = json!!.obj()
//                    txt_error.text = response.statusCode.toString()+ ": " + obj.getString("message")
//                }
//                else -> {
//                    txt_error.text = response.statusCode.toString() + ": " + response.responseMessage
//                }
//            }
//        }
//    }

    private fun isValidPhone(phone: String): Boolean {
        return Patterns.PHONE.matcher(phone).matches()
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidName(name: String): Boolean {
        val regex = "^[\\p{L} .'-]+$"
        return name.matches(regex.toRegex())
    }

    private fun processLoginCount(): Int {
        val myPreference = MyPreference(this)
        var loginCount = myPreference.getLoginCount()
        loginCount++
        myPreference.setLoginCount(loginCount)
        Log.i(TAG, "COUNT: $loginCount")
        return loginCount
    }

}

