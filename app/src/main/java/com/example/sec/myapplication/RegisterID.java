package com.example.sec.myapplication;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class RegisterID extends AppCompatActivity {


    public  EditText input_User_ID, input_User_Password, input_first_name,input_last_name,input_User_Birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_id);

        //액션바 설정하는거//
        getSupportActionBar().setTitle("Register ID");//액션바 타이틀 변경하는거
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffd777"))); //액션바 배경색 변경 0xFF339999

        input_User_ID = (EditText)findViewById(R.id.input_User_ID);
        input_User_Password = (EditText)findViewById(R.id.input_User_Password);
        input_first_name = (EditText)findViewById(R.id.input_first_name);
        input_last_name = (EditText)findViewById(R.id.input_last_name);
        input_User_Birthday = (EditText)findViewById(R.id.input_User_Birthday);

        Button submit = (Button)findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User_ID = input_User_ID.getText().toString(); //toString으로 문자변환
                String User_Password = input_User_Password.getText().toString(); //toString으로 문자변환
                String first_name = input_first_name.getText().toString(); //toString으로 문자변환
                String last_name = input_last_name.getText().toString(); //toString으로 문자변환
                String User_Birthday = input_User_Birthday.getText().toString(); //toString으로 문자변환
                //Integer.parseInt(); 문자열을 숫자로

                JsonTransfer userdata_transfer = new JsonTransfer();

                try
                {
                    JSONObject json_dataTransfer = new JSONObject();  //JSONObject는 JSON을 만들기 위함.
                    //json_dataTransfer에 ("키값" : "보낼데이터") 형식으로 저장한다.
                    json_dataTransfer.put("user_id", input_User_ID.getText().toString());
                    json_dataTransfer.put("user_password", input_User_Password.getText().toString());
                    json_dataTransfer.put("first_name", input_first_name.getText().toString());
                    json_dataTransfer.put("last_name", input_last_name.getText().toString());
                    json_dataTransfer.put("user_birthday", input_User_Birthday.getText().toString());
                    //json_dataTransfer의 데이터들을 하나의 json_string으로 묶는다.
                    String json_string = json_dataTransfer.toString();


                    //보내기 전에 json_string 양 쪽 끝에 대괄호를 붙인다. (Object로 처리하기 때문이다. 만약 Array로 처리한다면, 대괄호는 필요없다고 한다.)
                    userdata_transfer.execute("http://teama-iot.calit2.net/slim-api/receive-user-data","["+json_string+"]");  //보낼주소추가

                    //Toast.makeText(getApplicationContext(), json_string, Toast.LENGTH_LONG);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Intent RegisterID = new Intent(RegisterID.this, Login.class);
                startActivity(RegisterID);
                finish();
            }
        });
    }




}