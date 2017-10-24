package com.example.a.t08_asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    //params 초기 데이터 progress 진행 될때 result 쓰레드가 종료될때 데이터 리턴
    //제너릭 - 데이터 타입을 외부에서 지정해준다.
    //alt + insert 키 오버라이드할 수 있는 메소드 리스트가 나온다.
    class MyTask extends AsyncTask<Integer,Float,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Float... values) {
            super.onProgressUpdate(values);
            textView.setText("count"+values[0]);
        }

        @Override
        protected String doInBackground(Integer... params) {
            //첫 파라메터값을 지정
            int value = params[0];
            for(int i = value ; i < 100000 ; i++){
                publishProgress((float)i);
            }
            return "doInBackground Done!";
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textview);
        MyTask myTask = new MyTask();
        //asyncTask 실행
        myTask.execute(1000);
    }
}
