package com.example.wuqilong.sudoku;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class SettingGlobal {
    //define
    final  static String SELECTMOD_KEY="SELECTMOD";//設定檔案中的KEY值
    public final static int SELECTMOD_BLOCK  =0x4145B;//選擇區塊再按數字
    public final static int SELECTMOD_NUMBER =0x4145A;//選擇數字再按區塊

    final  static String COLOR1_KEY="COLOR1";//設定檔案中的KEY值
    final  static String COLOR2_KEY="COLOR2";//設定檔案中的KEY值


    static final String KEY = "com.example.wuqilong.sudoku";

    //set 參數
    private static int selectMod=0;
    private static int noSaveSelectMod=0;

    private static int color1=0xffffff00;
    private static int noSaveColor1=0xffffff00;
    private static int color2=0xffffffff;
    private static int noSaveColor2=0xffffffff;

    //動作
    public static void loadSetting(AppCompatActivity activity){//載入設定資料
        SharedPreferences spref = activity.getApplication()
                .getSharedPreferences(KEY, Context.MODE_PRIVATE);

        selectMod = spref.getInt(SELECTMOD_KEY,/*default value*/SELECTMOD_BLOCK);//取出數值
        noSaveSelectMod=selectMod;
        color1 = spref.getInt(COLOR1_KEY,/*default value*/0xffffff00);//取出數值
        noSaveColor1=color1;
        color2 = spref.getInt(COLOR2_KEY,/*default value*/0xffffffff);//取出數值
        noSaveColor2=color2;
    }

    public static boolean isChangSetting(){//檢查設定是否被變動
        if(selectMod != noSaveSelectMod) return true;
        if(color1 != noSaveColor1) return true;
        if(color2 != noSaveColor2) return true;
        return false;
    }
    public static void resetSetting(){//將未儲存的變更還原
        noSaveSelectMod=selectMod;
        noSaveColor1=color1;
        noSaveColor2=color2;
    }
    public static void saveSetting(AppCompatActivity activity){//儲存設定檔案

        SharedPreferences spref = activity.getApplication()
                .getSharedPreferences(KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spref.edit();
        editor.clear();//清除所有設定

        selectMod=noSaveSelectMod;//複製儲存
        editor.putInt(SELECTMOD_KEY,selectMod);//儲存作答模式設定

        color1=noSaveColor1;//複製儲存
        editor.putInt(COLOR1_KEY,color1);//儲存作答模式設定

        color2=noSaveColor2;//複製儲存
        editor.putInt(COLOR2_KEY,color2);//儲存作答模式設定

        editor.commit();//儲存設定
    }

    //設定參數
    public static void setSelectMod(int mod){//調整設定(未生效)
        noSaveSelectMod=mod;
    }
    public static void setColor1(int color){//調整設定(未生效)
        noSaveColor1=color;
    }
    public static void setColor2(int color){//調整設定(未生效)
        noSaveColor2=color;
    }

    //取得設定參數
    public static int getSelectMod(){
        return selectMod;
    }
    public static int getNoSaveSelectMod(){
        return  noSaveSelectMod;
    }
    public static int getColor1(){
        return color1;
    }
    public static int getNoSaveColor1(){
        return noSaveColor1;
    }
    public static int getColor2(){
        return color2;
    }
    public static int getNoSaveColor2(){
        return noSaveColor2;
    }

}
