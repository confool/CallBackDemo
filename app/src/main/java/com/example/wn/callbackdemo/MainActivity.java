package com.example.wn.callbackdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int SHOW=0;
    private Button mButton;
    private TextView mTextView;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW:
                    String response=(String)msg.obj;
                    mTextView.setText(response);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton=(Button)findViewById(R.id.button);
        mTextView=(TextView)findViewById(R.id.textView);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });

    }

    private void sendRequest(){
        HttpUtil.sendHttpRequest("https://www.baidu.com/", new HttpCallBack() {
            @Override
            public void onSuccess(String response) {
                Message message=new Message();
                message.what=SHOW;
                message.obj=response;
                mHandler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                Message message=new Message();
                message.what=SHOW;
                message.obj="获取失败";
                mHandler.sendMessage(message);
            }
        });
    }
}
