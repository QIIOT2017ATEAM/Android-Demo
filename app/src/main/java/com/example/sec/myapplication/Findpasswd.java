package com.example.sec.myapplication;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class Findpasswd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpasswd);

        Button passwdsubmit = (Button)findViewById(R.id.btn_send);

        passwdsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passwdsubmit = new Intent(Findpasswd.this, Login.class); //요고는 새로운 인텐트 생성시키는거 ㅇㅋㅇㅋ 이 엑티비티에서 딴데로 넘어간다고 설정
                startActivity(passwdsubmit); // 얘가 액티비티 실행시키는거 있어야함꼭
            }
        });
    }
}
