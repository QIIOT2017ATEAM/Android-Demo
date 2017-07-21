package com.example.sec.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Spinner;

public class Findid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findid);

        // Spinner
        Spinner question = (Spinner) findViewById(R.id.txt_question_type);
        ArrayAdapter questionA = ArrayAdapter.createFromResource(this, R.array.question, android.R.layout.simple_spinner_item);
        questionA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        question.setAdapter(questionA);

        Button idsubmit = (Button)findViewById(R.id.button2);

        idsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idsubmit = new Intent(Findid.this, Login.class); //요고는 새로운 인텐트 생성시키는거 ㅇㅋㅇㅋ 이 엑티비티에서 딴데로 넘어간다고 설정
                startActivity(idsubmit); // 얘가 액티비티 실행시키는거 있어야함꼭
            }
        });

    }
}