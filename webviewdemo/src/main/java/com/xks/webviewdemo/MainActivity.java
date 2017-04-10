package com.xks.webviewdemo;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private WebView mWebview;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebview = (WebView) findViewById(R.id.my_webview);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setBlockNetworkImage(false);
        mWebview.setWebViewClient(new WebViewClient() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

                Log.i("URL", request.getUrl().toString());

                if (request.getUrl().toString().endsWith("jpg||jpeg||ico")) {
                    return super.shouldInterceptRequest(view, request);
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    BufferedReader bufferedReader = null;
                    try {
                        URL url = new URL("http://www.importnew.com/");
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setConnectTimeout(10 * 1000);
                        httpURLConnection.setReadTimeout(40 * 1000);
                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        String line = "";
                        while ((line = bufferedReader.readLine()) != null)
                            stringBuilder.append(line);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (bufferedReader != null)
                            try {
                                bufferedReader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }


                    WebResourceResponse webResourceResponse = null;
                    webResourceResponse = new WebResourceResponse("text/html", "utf-8", new ByteArrayInputStream(stringBuilder.toString().getBytes()));
                    return webResourceResponse;
                }
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String urlStr) {

                Log.i("URL", urlStr);

                if (urlStr.endsWith("jpg||jpeg||ico"))
                    return super.shouldInterceptRequest(view, urlStr);
                else {
                    StringBuilder stringBuilder = new StringBuilder();
                    BufferedReader bufferedReader = null;
                    try {
                        URL url = new URL("http://www.importnew.com/");
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setConnectTimeout(10 * 1000);
                        httpURLConnection.setReadTimeout(40 * 1000);
                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        String line = "";
                        while ((line = bufferedReader.readLine()) != null)
                            stringBuilder.append(line);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (bufferedReader != null)
                            try {
                                bufferedReader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }


                    WebResourceResponse webResourceResponse = null;
                    webResourceResponse = new WebResourceResponse("text/html", "utf-8", new ByteArrayInputStream(stringBuilder.toString().getBytes()));
                    return webResourceResponse;
                }

            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        mWebview.loadUrl("http://www.baidu.com/");
    }
}
