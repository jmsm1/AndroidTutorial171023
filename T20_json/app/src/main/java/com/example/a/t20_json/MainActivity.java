package com.example.a.t20_json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    String str = "[{'name':'111' ,'tel':'010-7170-3144' , 'age':20 }," +
                 "{'name':'222' ,'tel':'010-2222-3333' , 'age':30 }," +
                 "{'name':'333' ,'tel':'010-2222-4444' , 'age':40 }]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            JSONArray jsonArray = new JSONArray(str);
            for(int i = 0 ; i < jsonArray.length() ; i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                String name = obj.getString("name");
                String tel = obj.getString("tel");
                int age = obj.getInt("age");
                Log.d("info::","name : " + name + " tel : " + tel + " age : "+ age);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onBtnClick(View v){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://api.androidhive.info/contacts/", new JsonHttpResponseHandler(   ) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("obj",response.toString());
            }
        });
    }
}
