package com.example.a.t19_camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int TAKE_PHOTO = 100;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void onBtnClick(View v){
        //카메라 관련 어플 목록 선택할 수 있게 나온다.
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent , TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == TAKE_PHOTO){
            if(resultCode == RESULT_OK){
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
