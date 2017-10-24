package com.example.a.t03_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static int MY_EQ = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     }


    //버튼 이벤트 onClick 이벤트
    public void onBtnClick(View v){
        //다른 activity 화면으로 이동
        //this  , 시작시킬 activity 클래스명
        Intent intent = new Intent(this,MyActivity.class);
        //다음 화면에 파라메터 전달
        intent.putExtra("id","abcd");
        intent.putExtra("pw","efgh");
        intent.putExtra("level",10);
        //화면 이동시 reuslt데이터를 받을수있는 메소드
        startActivityForResult(intent,MY_EQ);
    }

    //전화면에 result  데이터를 받는다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MY_EQ){
            if(resultCode == RESULT_OK){
                Toast.makeText(this, data.getStringExtra("myResult"), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, data.getStringExtra("myValue"), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
