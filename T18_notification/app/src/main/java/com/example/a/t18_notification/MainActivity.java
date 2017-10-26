package com.example.a.t18_notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void  onBtnClick(View v){
        //안드로이드 자체 시스템에서 가져온다
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("TITLE");
        builder.setContentText("TEXT");
        builder.setSubText("subText");
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        Bitmap bm = BitmapFactory.decodeResource(getResources() , R.drawable.penguins);
        builder.setLargeIcon(bm);

        Intent intent = new Intent(this , NotiActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pIntent = PendingIntent.getActivity(this,0,intent,0);

        builder.setContentIntent(pIntent);
        //자동 사라짐
        builder.setAutoCancel(true);
        Notification noti = builder.build();
        manager.notify(1234,noti);
    }
}
