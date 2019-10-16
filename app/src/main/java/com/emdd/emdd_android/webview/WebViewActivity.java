package com.emdd.emdd_android.webview;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.emdd.emdd_android.R;
import com.vondear.rxtool.RxWebViewTool;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {
    @BindView(R.id.pb_web_base)
    ProgressBar pbWebBase;
    @BindView(R.id.web_base)
    WebView webBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_web_view);
        ButterKnife.bind(this);
        RxWebViewTool.initWebView(this, webBase);
        webBase.loadUrl("");
    }
}
