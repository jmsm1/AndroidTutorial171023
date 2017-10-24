package com.example.a.t03_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //전화면 정보를 intent 가져옴
        Intent intent = getIntent();
        //파라메터의 형식을 정해서 가져옴
        String id = intent.getStringExtra("id");
        String pw = intent.getStringExtra("pw");
        //int형은 defalut 값을 설정해줘야 한다.
        int level = intent.getIntExtra("level",0);
        Toast.makeText(this, "id:"+id+"   pw:"+pw+"  level"+level, Toast.LENGTH_SHORT).show();
    }

    //화면을 종료시키면서 파라메터 전달
    public void onFinishClick(View v){
        Intent intent = new Intent();
        intent.putExtra("myResult","adsfadsf");
        intent.putExtra("myValue","2134");
        setResult(RESULT_OK , intent);
        //현재 activity 종료
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "화면종료", Toast.LENGTH_SHORT).show();
    }
}
