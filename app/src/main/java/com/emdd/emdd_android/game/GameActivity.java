package com.emdd.emdd_android.game;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.emdd.emdd_android.R;

public class GameActivity extends Activity {


    WebView webView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        webView  = findViewById(R.id.webview);


        start ();
    }

    private void start ()  {
        String url  = "http://172.16.14.108:7456/?aaa=1001";
//        url ="http://www.baidu.com";
        WebSettings webSettings  = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//设置支持JavaScript脚本
//        webSettings.setSupportZoom(false);//设置支持缩放
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);

        webView.loadUrl(url);

    }
}
