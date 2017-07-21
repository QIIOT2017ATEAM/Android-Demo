package com.example.sec.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RegisterID extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_id);

        Spinner question = (Spinner) findViewById(R.id.txt_question_type);
        ArrayAdapter questionA = ArrayAdapter.createFromResource(this, R.array.question, android.R.layout.simple_spinner_item);
        questionA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        question.setAdapter(questionA);

        Button submit = (Button)findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegisterID = new Intent(RegisterID.this, Login.class);
                startActivity(RegisterID);
                finish();
            }
        });

    }
}
