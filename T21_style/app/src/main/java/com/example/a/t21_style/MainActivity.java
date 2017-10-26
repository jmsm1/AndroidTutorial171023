package com.example.a.t21_style;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView selectedTextView;
    TextView workingTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedTextView = (TextView) findViewById(R.id.selected_textView);
        workingTextView = (TextView) findViewById(R.id.working_textView);

       // Button btnZero = (Button) findViewById(R.id.btnZero);
        //Button btnOne = (Button) findViewById(R.id.btnOne);
        //Button btnEnter = (Button) findViewById(R.id.btnEnter);

        TableLayout tableLayout = (TableLayout)findViewById(R.id.remote_control_tablelayout);

        //숫자 버튼의 클릭 이벤트 공통으로 시용할수 있도록 재정의
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String working = workingTextView.getText().toString();
                if(working.equals("0")){
                    workingTextView.setText(btn.getText());
                }else{
                    workingTextView.setText(working + btn.getText());
                }
            }
        };

        int number = 1;
        for(int i = 2 ; i < tableLayout.getChildCount()-1 ; i ++){
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            for(int k = 0 ; k < row.getChildCount() ; k++){
                Button btn = (Button) row.getChildAt(k);
                btn.setText(""+number);
                btn.setOnClickListener(numberListener);
                number++;
            }
        }
        TableRow row = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount()-1);
        Button deleteButton = (Button) row.getChildAt(0);
        Button zeroButton = (Button) row.getChildAt(1);
        Button enterButton = (Button) row.getChildAt(2);
        deleteButton.setText("delete");
        zeroButton.setText("0");
        enterButton.setText("enter");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workingTextView.setText("0");
            }
        });

        zeroButton.setOnClickListener(numberListener);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTextView.setText(workingTextView.getText());
                workingTextView.setText("0");
            }
        });



    }
}
