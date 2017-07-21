package com.example.sec.myapplication;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sec.myapplication.Fragment.Fragment1;
import com.example.sec.myapplication.Fragment.Fragment2;
import com.example.sec.myapplication.Fragment.Fragment3;
import com.example.sec.myapplication.Fragment.Fragment4;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int FRAGMENT1 = 1;
    private final int FRAGMENT2 = 2;
    private final int FRAGMENT3 = 3;
    private final int FRAGMENT4 = 4;

    private Button btn_1, btn_2, btn_3, btn_4;

    private Button btn_on;

    private BluetoothAdapter mBluetoothAdapter = null; //안드로이드는 블루투스와 연결하기위해 BluetoothAdapter클래스 제공함
    private BluetoothChatService mChatService = null;

    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;

    private ListView mConversationView;
    private EditText mOutEditText;
    private Button mSendButton;

    /**
     * Name of the connected device
     */
    private String mConnectedDeviceName = null;
    /**
     * Array adapter for the conversation thread
     */
    private ArrayAdapter<String> mConversationArrayAdapter;
    /**
     * String buffer for outgoing messages
     */
    private StringBuffer mOutStringBuffer;
    /**
     * Local Bluetooth adapter
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //필요함
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {   //액션버튼 메뉴 액션바에 집어 넣기, 0이 flase 1일때 true
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){ //액션바 이벤트임 눌렀을떄 실행되느
        int id = item.getItemId();
        if (id == R.id.actionbar) {
            Toast.makeText(this, "제발 되라", Toast.LENGTH_SHORT).show();
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();//내 기기 블루투스 상태 아는놈, 정적메소드 getDefaultAdapter 호출하여 객체생성
                if (mBluetoothAdapter == null) { //블루투스 지원못하면 이창이 뜬다.
                    Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
                }

            if (!mBluetoothAdapter.isEnabled()) { //isEnabled()호출해서 현재 블루투스가 활성화되있는지
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT) ; //블루투스를 활성화시키려면 ACTION_REQUEST_ENABLE 인텐트로 startActivityForResult()를 호출
            }else
            {
                Intent serverIntent = new Intent(this, DeviceListActivity.class); //디바이스 찾는 인텐드
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
                return true;
            }//꺼져있을경우 블루투스 켜주는 메소드(창 띄어서 켤지말지 결정함)

            /*
            다음에 구현한 기능으로는 블루투스 검색기능이다.
            블루투스 검색의 시작과 결과는 모두 BroadcastReceiver 를 이용하여 결과를 받게함
            adapter 는 ListView 의 Adapter 로 검색장치의 이름과 주소를 표시
            검색은 버튼의 OnClickListener 로 버튼을 클릭하였을 때 현재 검색여부를 확인하고 검색을 하도록 하였다.
            */



            return true;
        }


        return super.onOptionsItemSelected(item);
    }

        /*
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothadapter == null) {
         //장치가 블루투스를 지원하지 않는 경우.
        }else {
        // 장치가 블루투스를 지원하는 경우.
        }
       */

    //액션바 숨기기
    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();
    }

    private void ensureDiscoverable() {
        if (mBluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }




}