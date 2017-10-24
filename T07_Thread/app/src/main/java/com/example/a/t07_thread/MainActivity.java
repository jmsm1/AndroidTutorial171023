package com.example.a.t07_thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {
    private static final  int MY_VALUE = 100;
    class MyThread extends Thread{
        @Override
        public void run() {
            for(int i = 0 ; i < 100000 ; i++){
                Message msg = new Message();
                msg.what = MY_VALUE;
                msg.arg1 = i;
                handle.sendMessage(msg);
            }
        }
    }

    Handler handle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           //myButton.setText("count : " + msg.arg1);
            myProgressBar.setProgress(msg.arg1);
        }
    };

    ProgressBar myProgressBar;
    Button myButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = (Button) findViewById(R.id.myButton);
        myProgressBar = (ProgressBar) findViewById(R.id.myProgressBar);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread th = new MyThread();
                th.start();
            }
        });
    }
}
