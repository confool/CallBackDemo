package com.example.wn.callbackdemo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wn on 2017/5/7.
 */

public class HttpUtil {

    public static void sendHttpRequest(final String url ,final HttpCallBack httpCallBack)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null ;
                try {
                    URL Url = new URL(url) ;
                    try {
                        httpURLConnection = (HttpURLConnection) Url.openConnection();
                        httpURLConnection.setRequestMethod("GET");//设置请求方式
                        httpURLConnection.setConnectTimeout(8000);//设置链接超时时间
                        httpURLConnection.setReadTimeout(8000);//设置读取超时时间
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setDoOutput(true);
                        InputStream in = httpURLConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        //where achieve this method ,哪里实现了该方法，就调用哪里的函数，在MainActivity里面实现了
                        httpCallBack.onSuccess(response.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("网络连接打开失败");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    //where achieve this method ,哪里实现了该方法，就调用哪里的函数，在MainActivity里面实现了
                    httpCallBack.onError(e);
                }
            }
        }).start();
    }
}
