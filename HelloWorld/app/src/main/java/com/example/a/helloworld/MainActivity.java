package com.example.a.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //activity_main.xml 뷰로 사용
    }

    public void btnClick(View v){
        Toast.makeText(this, "hellow World", Toast.LENGTH_SHORT).show();
    }
}
