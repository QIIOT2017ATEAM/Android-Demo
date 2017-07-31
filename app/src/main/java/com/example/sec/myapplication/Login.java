package com.example.sec.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //액션바 설정하는거//
        getSupportActionBar().setTitle("Login");//액션바 타이틀 변경하는거
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffd777"))); //액션바 배경색 변경 0xFF339999

        Button login = (Button)findViewById(R.id.btn_login);
        Button register = (Button)findViewById(R.id.btn_register);
        Button find = (Button)findViewById(R.id.btn_find);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //로그인 한번 성공하면 끝
                Intent login = new Intent(Login.this, MainActivity.class); //요고는 새로운 인텐트 생성시키는거 ㅇㅋㅇㅋ 이 엑티비티에서 딴데로 넘어간다고 설정
                startActivity(login);
                // 얘가 액티비티 실행시키는거 있어야함꼭
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(Login.this, RegisterID.class);
                startActivity(register);
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent find = new Intent(Login.this, Findpasswd.class);
                startActivity(find);
            }
        });

    }
}
