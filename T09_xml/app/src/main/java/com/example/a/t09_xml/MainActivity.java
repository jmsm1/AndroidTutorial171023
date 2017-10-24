package com.example.a.t09_xml;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    class MyPullParse extends AsyncTask<String , Void , Void>{

        @Override
        protected Void doInBackground(String... params) {
            XmlPullParserFactory factory = null;
            try {
                factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                InputStream input = new URL(params[0]).openStream();
                xpp.setInput(input,"UTF-8");

                int eventType = xpp.getEventType();
                boolean bRead = false;
                while (eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        String tag = xpp.getName();
                        if(tag.equals("hour")  || tag.equals("day") || tag.equals("temp") || tag.equals("wfKor")){
                            bRead = true;
                        }
                    }else if(eventType == XmlPullParser.TEXT){
                        if(bRead){
                            Log.d("weather" , xpp.getText());
                            bRead = false;
                        }
                    }

                    eventType = xpp.next();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick(View v){
       MyPullParse task = new MyPullParse();
       task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153052000");
    }
}
