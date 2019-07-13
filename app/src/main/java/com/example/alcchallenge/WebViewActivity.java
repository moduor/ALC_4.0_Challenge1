package com.example.alcchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = findViewById(R.id.view_web);
        mProgressBar = findViewById(R.id.progressBar);

        webView.setWebViewClient(new myWebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Set for this activity's exercise only

        webView.loadUrl("https://andela.com/alc/");
    }

    public class myWebViewClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressBar.setVisibility(View.GONE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String request) {
            mProgressBar.setVisibility(View.VISIBLE);

            if(request.contains("andela.com")) return false;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(request));
            startActivity(intent);
            return false;
        }
    }
}
