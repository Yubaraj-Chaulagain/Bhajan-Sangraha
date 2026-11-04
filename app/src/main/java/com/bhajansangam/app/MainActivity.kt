package com.bhajansangam.app

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.ValueCallback

class MainActivity : Activity() {

    private var fileCallback: ValueCallback<Array<Uri>>? = null

    companion object {
        private const val FILE_REQUEST = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val web = WebView(this)

        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.settings.allowFileAccess = true

        web.webViewClient = WebViewClient()

        web.webChromeClient = object : WebChromeClient() {

            override fun onShowFileChooser(
                webView: WebView?,
                callback: ValueCallback<Array<Uri>>?,
                params: FileChooserParams?
            ): Boolean {

                fileCallback?.onReceiveValue(null)
                fileCallback = callback

                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "audio/*"
                intent.addCategory(Intent.CATEGORY_OPENABLE)

                startActivityForResult(
                    intent,
                    FILE_REQUEST
                )

                return true
            }
        }

        web.loadUrl("file:///android_asset/index.html")

        setContentView(web)
    }

    @Deprecated("Deprecated in Android API")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FILE_REQUEST) {
            val result = if (
                resultCode == Activity.RESULT_OK &&
                data?.data != null
            ) {
                arrayOf(data.data!!)
            } else {
                null
            }

            fileCallback?.onReceiveValue(result)
            fileCallback = null
        }
    }
}
