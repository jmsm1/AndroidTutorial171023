package com.example.a.t04_menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.textView);
        //화면에 들어왓을때 textView 에 onCreateContextMenu 추가
        registerForContextMenu(textView);
    }

    //아이템을 길게 눌렀을떄 나오는 메뉴
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,100,0,"menu1");
        menu.add(0,101,0,"menu2");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == 100){
            Toast.makeText(this, "menu1 select", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "menu2 select", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    //menu_main.xml 메뉴파일은 불러온다
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflater xml파일을 메모리에 올린다.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    //옵션메뉴가 선택되었을때 실행
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.myItem1){
            Toast.makeText(this, "item1 select", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "item2 select", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
