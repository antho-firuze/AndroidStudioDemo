package com.firuze.ahmad.demo.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.firuze.ahmad.demo.MyPreference
import com.firuze.ahmad.demo.R
import com.firuze.ahmad.demo.fragments.FragmentTest
import kotlinx.android.synthetic.main.main.*
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        btn_fragment.setOnClickListener(this)
        btn_webview.setOnClickListener(this)
        btn_recycleview.setOnClickListener(this)

    }

    override fun onClick(v : View) {
        when (v.id) {
            R.id.btn_fragment -> {
                supportFragmentManager.beginTransaction().replace(android.R.id.content, FragmentTest()).commit()
            }
            R.id.btn_webview -> {
                startActivity(Intent(this, WebviewActivity::class.java))
            }
            R.id.btn_recycleview -> {
                startActivity(Intent(this, RecycleView::class.java))
            }
        }
    }

    override fun onBackPressed() {
        val myPref = MyPreference(this)
        val i = myPref.getCount(myPref.PREF_BACKPRESSED_COUNT)+1

        if (i < 2){
            myPref.setCount(myPref.PREF_BACKPRESSED_COUNT, i)
            longToast("Tekan sekali lagi untuk keluar !")
        } else {
            myPref.setCount(myPref.PREF_BACKPRESSED_COUNT, 0)
            super.onBackPressed()
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        val myPref = MyPreference(this)
        myPref.setCount(myPref.PREF_BACKPRESSED_COUNT, 0)
    }
}
