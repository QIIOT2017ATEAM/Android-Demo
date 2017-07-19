package com.example.sec.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Find extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        Button findid = (Button)findViewById(R.id.btn_findid);

        findid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findid = new Intent(Find.this, Login.class); //일단 지금은 findid 누르면 Login으로 가게했는데 나중에 조건문써서 설정하면될듯
                startActivity(findid);
            }
        });
    }
}
