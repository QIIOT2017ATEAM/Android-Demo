package com.example.sec.myapplication.Db;

/**
 * Created by sec on 2017-08-02.
 */
import android.annotation.SuppressLint;
import android.os.StrictMode;

//안드로이드 및 자바에서 DB에 접속하여 데이터를 입력 및 수정 그리고 받아오기 위한 php 파일에 접속하는 클레스입니다.

public class NetworkUtil {

    @SuppressLint("NewApi")

    static public void setNetworkPolicy() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
    }
}
