package com.example.a.p01_weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MyData> myDataList = new ArrayList<>();
    MyAdapter adapter;
    class MyData{
        int day;
        int hour;
        float temp;
        String wfKor;
        int imgSrc;
    }
    class MyPullParse extends AsyncTask<String , Void , String> {
        String res;

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected String doInBackground(String... params) {
            XmlPullParserFactory factory = null;
            try {
                factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                InputStream input = new URL(params[0]).openStream();
                xpp.setInput(input,"utf-8");

                int eventType = xpp.getEventType();
                boolean bRead = false;
                MyData myData = new MyData();
                while (eventType != XmlPullParser.END_DOCUMENT){
                    String tag = xpp.getName();
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if (tag.equalsIgnoreCase("data")) {
                                myData = new MyData();
                            }
                            break;

                        case XmlPullParser.TEXT:
                            res = xpp.getText();
                            break;

                        case XmlPullParser.END_TAG:
                            if(tag.equals("data")){
                                myDataList.add(myData);
                            }else if (tag.equalsIgnoreCase("day")) {
                                myData.day = Integer.parseInt(res);
                            } else if (tag.equalsIgnoreCase("hour")) {
                                Log.d("hour",res);
                                myData.hour = Integer.parseInt(res);
                            } else if (tag.equalsIgnoreCase("temp")) {
                                myData.temp = Float.parseFloat(res);
                            } else if (tag.equalsIgnoreCase("wfKor")) {
                                myData.wfKor = res;
                                //아이콘 설정
                                if(res.equals("맑음")){
                                    myData.imgSrc = R.drawable.nb01;
                                }else if(res.equals("구름 조금")){
                                    myData.imgSrc = R.drawable.nb02;
                                }else if(res.equals("구름 많음")){
                                    myData.imgSrc = R.drawable.nb03;
                                }else{
                                    myData.imgSrc = R.drawable.nb04;
                                }
                            }
                            break;
                        default:
                            break;
                    }
                    eventType = xpp.next();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return myDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return myDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //listView.xml 지정된 형식을 가져와 사용한다.
            if (convertView == null) {
                LayoutInflater inf = getLayoutInflater();
                convertView = inf.inflate(R.layout.listview, null);
            }
            //array 리스트에 담겨진 내용을 가져온다.
            MyData myData = myDataList.get(position);

            TextView itemDay = (TextView) convertView.findViewById(R.id.itemDay);
            TextView itemHour = (TextView) convertView.findViewById(R.id.itemHour);
            TextView itemTemp = (TextView) convertView.findViewById(R.id.itemTemp);
            TextView itemWfKor = (TextView) convertView.findViewById(R.id.itemWfKor);
            ImageView itemIcon = convertView.findViewById(R.id.iconView);
            itemDay.setText(String.valueOf(myData.day));
            itemHour.setText(String.valueOf(myData.hour));
            itemTemp.setText(String.valueOf(myData.temp));
            itemWfKor.setText(myData.wfKor);
            itemIcon.setImageResource(myData.imgSrc);

            return convertView;
        }
    }

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);

        adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }

    public void onBtnClick(View v){
        MyPullParse task = new MyPullParse();
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153052000");
    }
}
