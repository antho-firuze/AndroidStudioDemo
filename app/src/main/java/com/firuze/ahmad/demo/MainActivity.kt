package com.firuze.ahmad.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main)



        var btnWebview = findViewById<Button>(R.id.btnWebview)
        btnWebview.setOnClickListener {
            var i = Intent(this,WebviewActivity::class.java)
            startActivity(i)
        }

        var btnFragment = findViewById<Button>(R.id.btnFragment)
        btnFragment.setOnClickListener {

        }
    }
}
