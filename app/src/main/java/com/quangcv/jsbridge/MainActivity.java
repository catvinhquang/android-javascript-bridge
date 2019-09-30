package com.quangcv.jsbridge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Quang Cat on 01-Oct-2019
 **/

public class MainActivity extends Activity {

    private static final String html = "<html><head><script type=\"text/javascript\">function add(){a=Math.round(Math.random()*10);b=Math.round(Math.random()*10);var result=Android.add(a,b);document.getElementById(\"content\").innerText=\"Touch me!\\n\\n[Monitor]: \"+a+\" + \"+b+\" = \"+result;} function subtract(a,b){document.getElementById(\"content\").innerText=\"[Calculator]\";return a-b;}</script></head><body style=\"text-align: center;\" onclick=\"add()\"><div id=\"content\" style=\"position: absolute; top: 50%; left: 50%; transform: translateX(-50%) translateY(-50%);\">Touch me!</div></body></html>";
    private static final Random random = new Random();

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView webView = findViewById(R.id.web_view);
        final TextView textView = findViewById(R.id.text_view);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JSInterface(textView), "Android");
        webView.loadData(html, "text/html", "UTF-8");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int a = random.nextInt(11);
                final int b = random.nextInt(11);
                webView.evaluateJavascript("javascript: subtract(" + a + ", " + b + ")", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        textView.setText("Touch me!\n\n[Monitor]: " + a + " - " + b + " = " + value);
                    }
                });
            }
        });
    }

}