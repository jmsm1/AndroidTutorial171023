package com.example.a.t17_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                Toast.makeText(context, "batt : " + level, Toast.LENGTH_SHORT).show();
            }

            if(action.equals(Intent.ACTION_BATTERY_LOW)){
                Toast.makeText(context, "rowBattery", Toast.LENGTH_SHORT).show();
            }

            if(action.equals("CCCC")){
                Toast.makeText(context, "CCCCCC", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        //베터리 체인지 필터 추가
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction("CCCC");
        //리시버 등록
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //리시버 해지
        unregisterReceiver(receiver);
    }

    public void onBtnClick(View v){
        Intent intent = new Intent("CCCC");
        sendBroadcast(intent);
    }


}
