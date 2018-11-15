package com.example.wuqilong.sudoku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.preference.EditTextPreference;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Solution_operation_activity extends AppCompatActivity {
    final int ID=948700;
    //View editTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_operation_activity);

        //editTextView=findViewById(R.id.view);

        init();
    }
    void init(){
        createLabel();//創建textview
        loginListener();//登入textview事件

        Button returnbt=findViewById(R.id.soa_return_BT);
        returnbt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showReturnDialog();
            }
        });
    }

    private void showReturnDialog() {
        AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
        MyAlertDialog.setTitle("返回主畫面");
        MyAlertDialog.setMessage("確定要返回主畫面?全部內容將會被清空。");
        DialogInterface.OnClickListener OkClick = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case DialogInterface.BUTTON_NEUTRAL:
                        Intent intent=new Intent(Solution_operation_activity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };
        MyAlertDialog.setNeutralButton("返回主畫面",OkClick );
        MyAlertDialog.setNegativeButton("取消",OkClick );
        MyAlertDialog.show();
    }

    private void loginListener(){
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=v.getId()-ID;
                ((TextView)v).setText("A");
            }
        };
        for(int i=0;i<81;i++){
            TextView tv=findViewById(ID+i);
            tv.setOnClickListener(listener);
        }
    }
    private void createLabel(){
        FrameLayout layout = findViewById(R.id.layout);
        layout.setBackgroundColor(0xFF000000);
        for(int i=0;i<81;i++){
            int chunk_x=(i%9)/3;
            int chunk_y=(i/9)/3;
            TextView editText = new TextView(this);
            editText.setId(ID+i);
            //editText.setText("");
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    (layout.getLayoutParams().width-10)/9 - 10,
                    (layout.getLayoutParams().height-10)/9 - 10);

            if((chunk_x+chunk_y)%2==1) {
                editText.setBackgroundColor(SettingGlobal.getColor2());
            } else {
                editText.setBackgroundColor(SettingGlobal.getColor1());
            }


            //editText.;
            params.setMargins((layout.getLayoutParams().width/9)*(i%9) + 10, (layout.getLayoutParams().height/9)*(i/9) + 10, 0, 0);
            //editText.setInputType(InputType.TYPE_CLASS_NUMBER);//僅限數字
            editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);//置中
            editText.setTextSize(19);
            editText.setText(String.valueOf(i));
            editText.setLayoutParams(params);
            layout. addView ( editText );
        }
    }
}
