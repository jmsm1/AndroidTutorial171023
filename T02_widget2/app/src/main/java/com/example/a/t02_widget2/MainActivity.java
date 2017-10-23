package com.example.a.t02_widget2;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //에디터 텍스트 선언
        final EditText myEditText = (EditText)findViewById(R.id.myEditText);

        //텍스트가 입력되면 실행
        myEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("123","COUNT : " + count + "before : " + before + "start : "+start);
                if(start > 10){
                    Log.d("123","size over");
                 }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button btn = (Button) findViewById(R.id.textViewId1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //에디터 텍스트 값을 토스트로 보여줌
                String str = myEditText.getText().toString();
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });

        CheckBox myCheckBox = (CheckBox) findViewById(R.id.myCheckBox);
        //체크박스 변경 이벤트
        myCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(MainActivity.this, "hello checkbox", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "체크해제", Toast.LENGTH_SHORT).show();
                }
            }
        });




        RadioGroup myRadioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        //라디오 그룹 라디오 버튼 체크가 변경됬을때 이벤트 발생
        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int i) {
                switch (i){
                    case R.id.myRadio1 :
                        Toast.makeText(MainActivity.this, "라디오1", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.myRadio2 :
                        Toast.makeText(MainActivity.this, "라디오2", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.myRadio3 :
                        Toast.makeText(MainActivity.this, "라디오3", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
