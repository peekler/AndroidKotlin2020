package hu.ecity.webviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.settings.builtInZoomControls = true
        webView.settings.javaScriptEnabled = true

        btnGo.setOnClickListener {
            progressBarWebLoad.visibility = View.VISIBLE
            progressBarWebLoad.progress = 0
            //webView.loadUrl(etUrl.text.toString())
        }

        webView.webViewClient = object :WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,request: WebResourceRequest): Boolean {

                progressBarWebLoad.visibility = View.VISIBLE
                progressBarWebLoad.progress = 0
                webView.loadUrl(request.url.toString())
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBarWebLoad.progress = 100
                progressBarWebLoad.visibility = View.GONE
            }
        }

        progressBarWebLoad.max = 100

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progressBarWebLoad.progress = newProgress
            }
        }


    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }


}
