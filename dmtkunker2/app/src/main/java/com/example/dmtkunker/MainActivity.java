package com.example.dmtkunker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView dmtkunker;
    WebSettings webSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dmtkunker = (WebView)findViewById(R.id.WebView1);

        webSettings = dmtkunker.getSettings();

        dmtkunker.setWebViewClient(new WebViewClient());
        dmtkunker.loadUrl("https://bprdhanamitra.000webhostapp.com");
    }
}