package com.example.sec.myapplication;


import android.app.FragmentManager;
import android.bluetooth.BluetoothAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int FRAGMENT1 = 1;
    private final int FRAGMENT2 = 2;
    private final int FRAGMENT3 = 3;
    private final int FRAGMENT4 = 4;

    private Button btn_1, btn_2, btn_3, btn_4;

    private Button btn_on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //액션바 설정하는거//
        getSupportActionBar().setTitle("A");//액션바 타이틀 변경하는거
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999)); //액션바 배경색 변경
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//홈버튼 표시
        //액션바 숨기기
        //hideActionBar();


                // 위젯에 대한 참조
        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_3 = (Button)findViewById(R.id.btn_3);
        btn_4 = (Button)findViewById(R.id.btn_4);

        // 탭 버튼에 대한 리스너 연결
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);

        // 임의로 액티비티 호출 시점에 어느 프레그먼트를 프레임레이아웃에 띄울 것인지를 정함
        callFragment(FRAGMENT1);

        /*   btn_1.setOnClickListener(new View.OnClickListener() {  //setOnClickListener 는 버튼을 클릭시 이벤트발생, View.OnClickListener()는 화면을 불러오고 아래에서 함수설정
        //      @Override
        //     public void onClick(View v) {
        //      }
           });   이걸로 설정하는 방법도 한번 알아보자   */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){ //v.getid()가 뷰에있는놈을 들고오는거
            case R.id.btn_1 :
                // '버튼1' 클릭 시 '프래그먼트1' 호출
                callFragment(FRAGMENT1);
                break;

            case R.id.btn_2 :
                // '버튼2' 클릭 시 '프래그먼트2' 호출
                callFragment(FRAGMENT2);
                break;

            case R.id.btn_3 :
                // '버튼2' 클릭 시 '프래그먼트3' 호출
                callFragment(FRAGMENT3);
                break;
            case R.id.btn_4 :
                // '버튼2' 클릭 시 '프래그먼트3' 호출
                callFragment(FRAGMENT4);
                break;
        }
    }

    private void callFragment(int frament_no){ //이게 프레그먼트 부르는 함수같은데 해석 잘 못하겠듬 ㅠ

        // 프래그먼트 사용을 위해
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (frament_no){
            case 1:
                // '프래그먼트1' 호출
                Fragment1 fragment1 = new Fragment1();  //프래그먼트1을 여기서 선언
                transaction.replace(R.id.fragment_container, fragment1); //위치 있어야함
                transaction.commit();  // 있어야함 위치확정
                break;

            case 2:
                // '프래그먼트2' 호출
                Fragment2 fragment2 = new Fragment2();
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.commit();
                break;

            case 3:
                // '프래그먼트3' 호출
                Fragment3 fragment3 = new Fragment3();
                transaction.replace(R.id.fragment_container, fragment3);
                transaction.commit();
                break;

            case 4:
                // '프래그먼트4' 호출
                Fragment4 fragment4 = new Fragment4();
                transaction.replace(R.id.fragment_container, fragment4);
                transaction.commit();
                break;
        }

    }
/*
    BluetoothAdapter myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if(myBluetoothAdapter == null){
        btn_on.setEnabled(false);
    }else{

    }
*/


    /*
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothadapter == null) {
         //장치가 블루투스를 지원하지 않는 경우.
        }else {
        // 장치가 블루투스를 지원하는 경우.
        }
    */

    //액션버튼 메뉴 액션바에 집어 넣기, 0이 flase 1일때 true
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){ //액션바 이벤트임 눌렀을떄 실행되느
        int id = item.getItemId();
        if (id == R.id.actionbar) {
            Toast.makeText(this, "블루투스", Toast.LENGTH_SHORT).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    //액션바 숨기기
    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();
    }




}