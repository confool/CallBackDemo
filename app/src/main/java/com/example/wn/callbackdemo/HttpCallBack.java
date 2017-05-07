package com.example.wn.callbackdemo;

/**
 * Created by wn on 2017/5/7.
 */

public interface HttpCallBack {

    //成功
    void onSuccess(String response) ;

    //失败
    void onError(Exception e);

}
