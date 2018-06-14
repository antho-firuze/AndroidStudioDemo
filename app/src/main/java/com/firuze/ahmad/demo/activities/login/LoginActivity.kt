package com.firuze.ahmad.demo.activities.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.widget.toast
import com.firuze.ahmad.demo.MainActivity
import com.firuze.ahmad.demo.MyConfig
import com.firuze.ahmad.demo.MyPreference
import com.firuze.ahmad.demo.R
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.a_login.*
import org.jetbrains.anko.longToast
import org.json.JSONObject
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.util.regex.Pattern

/**
 * Created by antho.firuze@gmail.com on 2018-05-25.
 */

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "Login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_login)

//        FuelManager.instance.baseHeaders =
//                mapOf(
//                        "Content-Type" to "application/json",
//                        "X-Mesosfer-AppId" to "gnO39OL0LE",
//                        "X-Mesosfer-AppKey" to "FadYXLDpDjFrOZ22Od30kd46QTL2W702"
//                )

        btn_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                if (!isEmail(txt_username.text.toString())) {
                    longToast("Error: Email not recognize !")
                    return
                }

//                val res : Map<String, Any> = emptyMap()
//                Executors.newSingleThreadExecutor().execute({
//                    val params = mapOf("username" to txt_username.text.toString(), "password" to txt_pass.text.toString())
//                    res = ajaxRequest(params)
//                })

                val jsonParams = JSONObject()
                jsonParams.put("username", txt_username.text.toString()).put("password", txt_pass.text.toString())

                Fuel.post(MyConfig.URL_LOGIN).body(jsonParams.toString()).responseString { req, resp, res ->
                    Log.d(TAG, req.cUrlString())
                    when (resp.statusCode) {
                        200 -> {
                            val (dataRes, _) = res
                            val data: Map<String, Any> = Gson().fromJson(dataRes, object : TypeToken<Map<String, Any>>() {}.type)
                            Log.d(TAG, "${resp.statusCode} ${resp.responseMessage}: $data")

                            if (!data["status"].toString().toBoolean()) {
                                toast(data["message"].toString())
                                return@responseString
                            }

                            startActivity(Intent(this, MainActivity::class.java))
                            processLoginCount()
                            toast("Login Success !",Toast.LENGTH_SHORT)
                            finish()
                        }
                        in 400..419 -> {
                            val input = InputStreamReader(ByteArrayInputStream(resp.data))
                            val error: Map<String, Any> = Gson().fromJson(input, object : TypeToken<Map<String, Any>>() {}.type)
                            Log.d(TAG, "${resp.statusCode} ${resp.responseMessage}: $error")
                            longToast("Login Failed : $error")
                        }
                        else -> {
                            Log.d(TAG, "${resp.statusCode} ${resp.responseMessage}")
                            longToast("${resp.statusCode} ${resp.responseMessage}")
                        }
                    }
                }
            }
        }
    }

    private fun processLoginCount() {
        val myPreference = MyPreference(this)
        var loginCount = myPreference.getCount(myPreference.PREF_LOGIN_COUNT)+1
        myPreference.setCount(myPreference.PREF_LOGIN_COUNT, loginCount)
        Log.i(TAG, "COUNT: $loginCount")
    }

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

    private fun isEmail(email: String?): Boolean {
        if (email == null || email == "") {
            return false
        }
        val regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}"
        val pattern = Pattern.compile(regex)
        return pattern.matcher(email).matches()
    }
}

