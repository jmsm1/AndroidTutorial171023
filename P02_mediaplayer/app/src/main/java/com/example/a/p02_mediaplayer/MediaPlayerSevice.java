package com.example.a.p02_mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.SeekBar;

import java.io.IOException;

public class MediaPlayerSevice extends Service {
    public MediaPlayerSevice() {
    }

    MediaPlayer mp = null;
    final MediaSQLiteHandler dbHandler = new MediaSQLiteHandler(this);
    public class MediaBinder extends Binder {
        public MediaPlayerSevice getService(){
            return MediaPlayerSevice.this;
        }
    }

    MediaBinder binder = new MediaBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    //플레이어 시작
    public void mediaPlayStart(final SeekBar seekBar){
        final String path = Environment.getExternalStorageDirectory() + "/Download/Kalimba.mp3";
        Log.d("path=====>",path);
        final int curPosiotn = dbHandler.selectPosition(path , "Kalimba.mp3", 0 );

        //String path = http://file.ssenhosting.com/data1/programmer/e04tobyspring5.mp3
        mp = new MediaPlayer();
        try {

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    //사용자의에서 변경되었을때
                    if(fromUser){
                        if(mp != null) {
                            mp.seekTo(progress);
                        }
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            mp.setDataSource(path);
            mp.prepare();
            mp.seekTo(curPosiotn);
            mp.start();
            //seekBar 맥스값을 플레이시간으로 세팅
            seekBar.setMax(mp.getDuration());
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (mp != null){
                        try{
                            seekBar.setProgress(mp.getCurrentPosition());
                            if(mp.getCurrentPosition() % 3 == 0){
                                dbHandler.update(path , "Kalimba.mp3" , mp.getCurrentPosition());
                            }
                        }catch (Exception e){
                            seekBar.setProgress(0);
                        }
                    }
                }
            });
            th.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //플레이어 종료
    public void mediaPlayStop(){
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    public void deleteData(){
        String beFor = dbHandler.selectAll();
        Log.d("====","이전:::::"+beFor);
        dbHandler.delete("Kalimba.mp3");
        String after = dbHandler.selectAll();
        Log.d("====","이후:::::"+after);
    }
    public int selectPosition(){
        int position = dbHandler.selectPosition("","Kalimba.mp3",0);
        return position;
    }
}
