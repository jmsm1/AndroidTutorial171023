package com.example.a.t05_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] myData = {"hello" , "world" , "android" , "oracle" , "java" ,
            "hello" , "world" , "android" , "oracle" , "java" ,
            "hello" , "world" , "android" , "oracle" , "java" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //android.R.layout.simple_list_item_1 안드로이드에 이미 지정되어있음
        ArrayAdapter adpater = new ArrayAdapter(this,android.R.layout.simple_list_item_1,myData);
        ListView listView = (ListView)findViewById(R.id.listview);
        //listview 아이디에 myData를 세팅해주준다.
        listView.setAdapter(adpater);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, myData[position], Toast.LENGTH_SHORT).show();
            }
        });

    }
}
