package com.firuze.ahmad.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import kotlinx.android.synthetic.main.a_login.*

/**
 * Created by antho.firuze@gmail.com on 2018-05-25.
 */

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_login)

        btn_login.setOnClickListener {
            val params = listOf( "username" to txt_username.toString(), "password" to txt_pass.toString() )

            Fuel.post("http://45.64.1.183/~k1843178/api/servicessimpi/login", params).response { request, response, result ->

                Log.d("log", result.toString())
                Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show()

            }
//            Toast.makeText(this, params, Toast.LENGTH_LONG).show()
        }
    }
}
