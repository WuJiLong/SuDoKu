package com.example.wuqilong.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.InputStream;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        /*
        Sudoku_topic d=new Sudoku_topic(this,"Sudoku_topic_1");
        if(d.isReasonable()){
            Toast.makeText(this, "GOOD", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Bad", Toast.LENGTH_SHORT).show();
        }//*/
    }
    void init(){
        SettingGlobal.loadSetting(this);//載入設定檔案

        Button OSBT=findViewById(R.id.operational_solution_BT);
        Button SettingBT=findViewById(R.id.setting_BT);
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.operational_solution_BT:
                        Intent intent=new Intent(MainActivity.this,Solution_operation_activity.class);
                        startActivity(intent);
                        break;
                    case R.id.setting_BT:
                        Intent intent2=new Intent(MainActivity.this,setting.class);
                        startActivity(intent2);
                        break;
                }
            }
        };
        OSBT.setOnClickListener(listener);
        SettingBT.setOnClickListener(listener);

    }



}
