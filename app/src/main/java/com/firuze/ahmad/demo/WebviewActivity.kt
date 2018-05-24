package com.firuze.ahmad.demo

import android.net.http.SslError
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.a_webview.*

class WebviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_webview)

        val context = this
        webview.webViewClient = MyWebViewClient()

        btnGo.setOnClickListener({
            webview.loadUrl("https://" + edtUrl.text.toString())
        })

        btnGoBack.setOnClickListener({
            if(webview.canGoBack())
                webview.goBack()
            else
                Toast.makeText(context, "No History Available", Toast.LENGTH_SHORT).show()
        })

        btnGoForward.setOnClickListener({
            if(webview.canGoForward())
                webview.goForward()
            else
                Toast.makeText(context, "No History Available", Toast.LENGTH_SHORT).show()
        })
    }

    class MyWebViewClient : WebViewClient(){

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            handler?.proceed()
        }
    }
}
