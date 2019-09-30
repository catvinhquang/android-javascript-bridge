package com.quangcv.jsbridge;

import android.webkit.JavascriptInterface;
import android.widget.TextView;

/**
 * Created by Quang Cat on 01-Oct-2019
 **/

public class JSInterface {

    private TextView v;

    public JSInterface(TextView v) {
        this.v = v;
    }

    @JavascriptInterface
    public int add(int a, int b) {
        v.setText("[Calculator]");
        return a + b;
    }

}