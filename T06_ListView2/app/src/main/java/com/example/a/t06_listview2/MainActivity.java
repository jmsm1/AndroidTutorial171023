package com.example.a.t06_listview2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    class MyData{
        int itemIcon;
        String title;
        String desc;
    }

    ArrayList<MyData> myList = new ArrayList<>();

    private void initData(){
        for(int i = 0 ; i < 50 ; i++){
            MyData myData = new MyData();
            myData.title = "title"+i;
            myData.desc = "desc"+i;
            myData.itemIcon = R.mipmap.ic_launcher;
            myList.add(myData);
        }
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return myList.size();
        }

        @Override
        public Object getItem(int position) {
            return myList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //listView.xml 지정된 형식을 가져와 사용한다.
            if(convertView == null){
                LayoutInflater inf = getLayoutInflater();
                convertView = inf.inflate(R.layout.itemview , null);
            }
            //array 리스트에 담겨진 내용을 가져온다.
            MyData myData = myList.get(position);

            TextView tvTile = (TextView) convertView.findViewById(R.id.itemTitle);
            TextView tvDesc = (TextView) convertView.findViewById(R.id.itemDesc);
            ImageView itemIcon = convertView.findViewById(R.id.iconView);
            tvTile.setText(myData.title);
            tvDesc.setText(myData.desc);
            itemIcon.setImageResource(R.mipmap.ic_launcher);

            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        ListView listView = (ListView)findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }
}
