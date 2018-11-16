package com.example.wuqilong.sudoku.SuDoKu;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import java.util.Random;
import java.io.InputStream;

public class Sudoku_obj {

    AppCompatActivity main;
    Sudoku_topic topic;
    public int ans[][]={
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}};

    final static String topicFileName[]={"Sudoku_topic_1","Sudoku_topic_2","Sudoku_topic_3"};

    public Sudoku_obj(AppCompatActivity main){
        this.main=main;
        topic=new Sudoku_topic();
        loadTopic();
    }

    private void loadTopic(){
        if(topicFileName.length==0){
            Toast.makeText(main, "無法正常打開題目，因為沒有題庫。", Toast.LENGTH_SHORT).show();
            return;
        }
        Random rand=new Random(System.currentTimeMillis());
        String filename=topicFileName[rand.nextInt(topicFileName.length)];//隨機挑選檔案
        readTopicForFile(filename);//載入檔案

        if(!topic.isReasonable()){//檢查檔案正確性
            Toast.makeText(main, "題庫檔案不存在或是內容錯誤，無法正常打開題目。", Toast.LENGTH_SHORT).show();
            for(int i=0;i<81;i++){
                ans[i/9][i%9]=0;
            }
        }else{
            topic.randomAns();
            resetAns();
        }
    }

    private void resetAns(){
        for(int i=0;i<81;i++){
            if(topic.show[i/9][i%9])
                ans[i/9][i%9]=topic.ans[i/9][i%9];
            else
                ans[i/9][i%9]=0;
        }
    }
    private void readTopicForFile(String path){
        InputStream in;
        try {
            in = main.getBaseContext().getAssets().open(path);
            int i;
            char c;
            int count=0;
            int endless=0;
            while((i=in.read())!=-1) {
                c=(char)i;
                if(count<81){
                    if(c>='1'&&c<='9'){
                        topic.ans[count/9][count%9]=c-0x30;
                        count++;
                    }
                }else if(count<162){
                    if(c=='T'|| c=='F' || c=='t' || c=='f'){
                        topic.show[(count-81)/9][count%9] = ((c=='T')||(c=='t'));
                        count++;
                    }
                }else if(endless>300){
                    break;
                }
                endless++;
            }
            in.close();
        }catch(Exception e) {
            Toast.makeText(main, "檔案開啟過程錯誤："+path, Toast.LENGTH_SHORT).show();
        }
    }
}
