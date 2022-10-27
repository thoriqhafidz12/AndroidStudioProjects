package com.example.dmt_kunker;

import androidx.appcompat.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.Bundle;

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
        dmtkunker.loadUrl("https://bprdhanamitratama.com");
    }
}