package com.example.sec.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;

public class Find extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        Button btn_submit = (Button)findViewById(R.id.btn_submit);
        Button btn_send = (Button)findViewById(R.id.btn_send);

        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1);
        tabHost1.setup();

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.ID);
        ts1.setIndicator("ID");
        tabHost1.addTab(ts1);

        Spinner question = (Spinner) findViewById(R.id.txt_question_type);
        ArrayAdapter questionA = ArrayAdapter.createFromResource(this, R.array.question, android.R.layout.simple_spinner_item);
        questionA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        question.setAdapter(questionA);

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.PASSWORD) ;
        ts2.setIndicator("PASSWORD") ;
        tabHost1.addTab(ts2) ;



        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idfind = new Intent(Find.this, Login.class); //요고는 새로운 인텐트 생성시키는거 ㅇㅋㅇㅋ 이 엑티비티에서 딴데로 넘어간다고 설정
                startActivity(idfind); // 얘가 액티비티 실행시키는거 있어야함꼭
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pwfind = new Intent(Find.this, Login.class); //요고는 새로운 인텐트 생성시키는거 ㅇㅋㅇㅋ 이 엑티비티에서 딴데로 넘어간다고 설정
                startActivity(pwfind); // 얘가 액티비티 실행시키는거 있어야함꼭
            }
        });
    }
}
