package com.example.sec.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sec.myapplication.Db.PHPRequest;

import java.net.MalformedURLException;

public class Login extends AppCompatActivity {
    private EditText input_email, input_pw;


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
        input_email = (EditText)findViewById(R.id.input_email);
        input_pw = (EditText)findViewById(R.id.input_pw);

        login.setOnClickListener(new View.OnClickListener() { //login 버튼 눌렀을떄
            @Override
            public void onClick(View v) {
                try {
                    PHPRequest request = new PHPRequest("http://teama-iot.calit2.net/");
                    String result = request.PhPtest(String.valueOf(input_email.getText()),String.valueOf(input_pw.getText()));
                    if(result.equals("1")){

                        Toast.makeText(getApplication(),"들어감", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        Toast.makeText(getApplication(),"안 들어감",Toast.LENGTH_SHORT).show();
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }


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
