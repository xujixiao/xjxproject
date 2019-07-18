package com.ancient.dimension.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.ancient.dimension.R;

import java.net.URL;

public class WebActivity extends Activity {
    private WebView mWebView;

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra("url", url);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);
        mWebView = (WebView) findViewById(R.id.url_webview);
        if (getIntent() != null) {
            String url = getIntent().getStringExtra("url");
            mWebView.loadUrl(url);
        }

    }
}
