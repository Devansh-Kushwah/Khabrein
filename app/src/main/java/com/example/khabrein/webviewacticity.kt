package com.example.khabrein

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.widget.Toolbar
import com.example.khabrein.R
import com.example.khabrein.databinding.ActivityWebviewacticityBinding
import kotlinx.android.synthetic.main.activity_webviewacticity.*

class webviewacticity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setContentView(R.layout.activity_webviewacticity)

        setSupportActionBar(toolbarweb)
        toolbarweb?.setNavigationOnClickListener {
            finish()
        }



        val ss:String = intent.getStringExtra("value").toString()


        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.loadUrl(ss)

    }


}