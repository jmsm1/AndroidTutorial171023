package com.example.a.p02_mediaplayer;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    MediaPlayerSevice mediaPlayerSevice;
    SeekBar seekBar;
    TextView textView;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MediaPlayerSevice.MediaBinder binder = (MediaPlayerSevice.MediaBinder) iBinder;
            mediaPlayerSevice = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mediaPlayerSevice = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        textView = (TextView)findViewById(R.id.textView);

        //미디어 권한요청
        //Build.VERSION_CODES.M 마시멜로우 버전을 의미한다. 마시멜로우 이상인 디바이스에서 실행
        if (Build.VERSION.SDK_INT >= M) {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permissions,0);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MediaPlayerSevice.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }

    public void btnStart(View v){
       mediaPlayerSevice.mediaPlayStart(seekBar);
    }

    public void btnStop(View v){
        mediaPlayerSevice.mediaPlayStop();
    }

    public void btnDeleteData(View v){
        mediaPlayerSevice.deleteData();
    }

    public void btnSelectData(View v){
        int aa = mediaPlayerSevice.selectPosition();
        textView.setText("포지션 : " + aa);
    }
}
