package com.zxd.blackt.blackt.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.zxd.blackt.blackt.R;

public class ShowActivity extends AppCompatActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        showNews();
    }

    private void showNews() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);
    }

    private void initView() {
        wv = (WebView) findViewById(R.id.wv_show);
    }
}
