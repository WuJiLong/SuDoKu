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

import java.util.ArrayList;
import java.util.List;

public class Solution_operation_activity extends AppCompatActivity {
    final int ID=948700;
    //View editTextView;

    private int hold_block=40;
    private int hold_num=1;
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
        setNumButton();

        Button returnbt=findViewById(R.id.soa_return_BT);
        returnbt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showReturnDialog();
            }
        });
    }
    private void setNumButton(){
        List<Button> BT=new ArrayList<>();

        BT.add((Button)findViewById(R.id.num1_BT));
        BT.add((Button)findViewById(R.id.num2_BT));
        BT.add((Button)findViewById(R.id.num3_BT));
        BT.add((Button)findViewById(R.id.num4_BT));
        BT.add((Button)findViewById(R.id.num5_BT));
        BT.add((Button)findViewById(R.id.num6_BT));
        BT.add((Button)findViewById(R.id.num7_BT));
        BT.add((Button)findViewById(R.id.num8_BT));
        BT.add((Button)findViewById(R.id.num9_BT));

        View.OnClickListener listener=new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int key_num=1;
                switch (v.getId()){
                    case R.id.num1_BT:key_num=1;break;
                    case R.id.num2_BT:key_num=2;break;
                    case R.id.num3_BT:key_num=3;break;
                    case R.id.num4_BT:key_num=4;break;
                    case R.id.num5_BT:key_num=5;break;
                    case R.id.num6_BT:key_num=6;break;
                    case R.id.num7_BT:key_num=7;break;
                    case R.id.num8_BT:key_num=8;break;
                    case R.id.num9_BT:key_num=9;break;
                }
                if(SettingGlobal.getSelectMod()==SettingGlobal.SELECTMOD_BLOCK){
                    TextView view= findViewById(ID+hold_block);
                    view.setText(String.valueOf(key_num));
                }else{
                    hold_num=key_num;
                }
            }
        };
        for(Button b:BT)
            b.setOnClickListener(listener);
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
                if(SettingGlobal.getSelectMod()==SettingGlobal.SELECTMOD_BLOCK)
                    hold_block=id;
                else
                    ((TextView)v).setText(String.valueOf(hold_num));
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
            //editText.setText(String.valueOf(i));
            editText.setLayoutParams(params);
            layout. addView ( editText );
        }
    }
}
