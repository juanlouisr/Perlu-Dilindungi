package com.example.perludilindungi.ui.berita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.perludilindungi.R
import kotlinx.android.synthetic.main.activity_detail_berita.*

class DetailBeritaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)

        val bundle : Bundle? = intent.extras
        var url = bundle!!.getString("newsUrl")

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webview_detail_berita.webViewClient = WebViewClient()

        // this will load the url of the website
        webview_detail_berita.loadUrl(url!!)

        // this will enable the javascript settings
        webview_detail_berita.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        webview_detail_berita.settings.setSupportZoom(true)


    }

    // if you press Back button this code will work
    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (webview_detail_berita.canGoBack())
            webview_detail_berita.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }

}