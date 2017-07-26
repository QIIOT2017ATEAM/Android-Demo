package com.example.sec.myapplication;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sec.myapplication.Bluetooth.BluetoothChatService;
import com.example.sec.myapplication.Bluetooth.Constants;
import com.example.sec.myapplication.Bluetooth.DeviceListActivity;
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

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;//인스턴스변수이름ㅅㄴ언


    private BluetoothAdapter mBluetoothAdapter = null; //안드로이드는 블루투스와 연결하기위해 BluetoothAdapter클래스 제공함
    private BluetoothChatService mChatService = null;

    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;

    private ListView mConversationView;
    private EditText mOutEditText;
    private Button mSendButton;

    android.app.FragmentManager transaction = getFragmentManager();



    /**
     * Name of the connected device
     */
    private String mConnectedDeviceName = null;
    /**
     * Array adapter for the conversation thread
     */
    private ArrayAdapter<String> mConversationArrayAdapter; //<string>을 <comment>로 바꿈
    /**
     * String buffer for outgoing messages
     */
    private StringBuffer mOutStringBuffer;
    /**
     * Local Bluetooth adapter
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) { //이 액티비티가 다시실행될떄마다 초기화
        super.onCreate(savedInstanceState); //필요함
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();  //프래그먼트1을 여기서 선언
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();


        //액션바 설정하는거//
        getSupportActionBar().setTitle("A");//액션바 타이틀 변경하는거
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999)); //액션바 배경색 변경
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//홈버튼 표시
        //액션바 숨기기
        //hideActionBar();

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();//내 기기 블루투스 상태 아는놈, 정적메소드 getDefaultAdapter 호출하여 객체생성

        mChatService = new BluetoothChatService(this, mHandler);

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
        callFragment(FRAGMENT2);


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

        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); //take FragmentRransaction form FragmentManager //이동을위해


        switch (frament_no){
            case 1:
                if(transaction.findFragmentByTag("a") != null){ //a란놈이 있으면 보여주고(fragment1), 없으면 else로 없으면 추가(맨첨을 위해서)
                    transaction.beginTransaction().show(transaction.findFragmentByTag("a")).commit();
                }else {
                    transaction.beginTransaction().add(R.id.fragment_container, fragment1, "a").commit(); //fragment1을 findFragmentByTag("a")하면 찾아지게 선언
                }
                if(transaction.findFragmentByTag("b") != null)
                    transaction.beginTransaction().hide(transaction.findFragmentByTag("b")).commit();
                if(transaction.findFragmentByTag("c") != null)
                    transaction.beginTransaction().hide(transaction.findFragmentByTag("c")).commit();
                if(transaction.findFragmentByTag("d") != null)
                    transaction.beginTransaction().hide(transaction.findFragmentByTag("d")).commit();
                //transaction.replace(R.id.fragment_container, fragment1); //위치 있어야함, 화면전환, 리플레이쓰면 프레그먼트정보 없어짐. 생명주기 사망, 그래서 위에꺼쓰는중
                //transaction.commit();  // after modify, commit() is must written
                break;

            case 2:
                if(transaction.findFragmentByTag("b") != null){
                    transaction.beginTransaction().show(transaction.findFragmentByTag("b")).commit();
                }else {
                    transaction.beginTransaction().add(R.id.fragment_container, fragment2, "b").commit();
                }
                if(transaction.findFragmentByTag("a") != null)
                    transaction.beginTransaction().hide(transaction.findFragmentByTag("a")).commit();
                if(transaction.findFragmentByTag("c") != null)
                    transaction.beginTransaction().hide(transaction.findFragmentByTag("c")).commit();
                if(transaction.findFragmentByTag("d") != null)
                    transaction.beginTransaction().hide(transaction.findFragmentByTag("d")).commit();
                //transaction.replace(R.id.fragment_container, fragment2);
                //transaction.commit();
                break;

            case 3:
                if(transaction.findFragmentByTag("c") != null){
                    transaction.beginTransaction().show(transaction.findFragmentByTag("c")).commit();
                }else {
                    transaction.beginTransaction().add(R.id.fragment_container, fragment3, "c").commit();
                }
                if(transaction.findFragmentByTag("a") != null)
                    transaction.beginTransaction().hide(transaction.findFragmentByTag("a")).commit();
                if(transaction.findFragmentByTag("b") != null)
                    transaction.beginTransaction().hide(transaction.findFragmentByTag("b")).commit();
                if(transaction.findFragmentByTag("d") != null)
                    transaction.beginTransaction().hide(transaction.findFragmentByTag("d")).commit();
                //transaction.replace(R.id.fragment_container, fragment3);
                //transaction.commit();
                break;

            case 4:
                //Fragment4 fragment4 = new Fragment4();
                if(transaction.findFragmentByTag("d") != null){
                    transaction.beginTransaction().show(transaction.findFragmentByTag("d")).commit();
                }else {
                    transaction.beginTransaction().add(R.id.fragment_container, fragment4, "d").commit();
                }
                if(transaction.findFragmentByTag("a") != null)
                    transaction.beginTransaction().hide(transaction.findFragmentByTag("a")).commit();
                if(transaction.findFragmentByTag("b") != null)
                    transaction.beginTransaction().hide(transaction.findFragmentByTag("b")).commit();
                if(transaction.findFragmentByTag("c") != null)
                    transaction.beginTransaction().hide(transaction.findFragmentByTag("c")).commit();
                //transaction.replace(R.id.fragment_container, fragment4);
                //transaction.commit();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {   //액션버튼 메뉴 액션바에 집어 넣기, 0이 flase 1일때 true
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item){ //------------------------------------------------------------------------액션바 이벤트임 눌렀을떄 실행되느
        int id = item.getItemId();
        if (id == R.id.bluetooth) {
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
            }
            return true;
        }
        else
        {
            Intent gologin = new Intent(MainActivity.this, Login.class); //요고는 새로운 인텐트 생성시키는거 ㅇㅋㅇㅋ 이 엑티비티에서 딴데로 넘어간다고 설정
            startActivity(gologin);
        }
        return super.onOptionsItemSelected(item);
    }
// -----------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Establish connection with other device
     *
     * @param data   An {@link Intent} with {@link DeviceListActivity#EXTRA_DEVICE_ADDRESS} extra.
     * @param secure Socket Security type - Secure (true) , Insecure (false)
     */
    private void connectDevice(Intent data, boolean secure) { //상대방 블루투스 주소받아와서
        // Get the device MAC address
        String address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS); // Get the BluetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address); // Attempt to connect to the device
        mChatService.connect(device, secure);
    }

    //Handler does changing UI
    public final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            FragmentActivity activity = MainActivity.this;
            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            break;
                        case BluetoothChatService.STATE_LISTEN:
                        case BluetoothChatService.STATE_NONE:
                            break;
                    }
                    break;
                /*case Constants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    mConversationArrayAdapter.add("Me:  " + writeMessage);
                    break; */
                case Constants.MESSAGE_READ://핸들러로와서 메세지 받을경우에 일로와서 이거실행
                    byte[] readBuf = (byte[]) msg.obj; //byte한글자 ,버퍼가 문자
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1); //byte를 문자열로 변경, 0 : 처음부터 끝까지
                    //here is error
                    Log.v("readMessage : ", readMessage);
                    Toast.makeText(activity, readMessage, Toast.LENGTH_SHORT).show(); //readMessage 로 들어감 값이
                    fragment1.setvalue(readMessage);


                    break; // ----------------------------------------------------------------------------------------------------

                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != activity) {
                        Toast.makeText(activity, "Connected to "
                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    }
                break;
                case Constants.MESSAGE_TOAST:
                    if (null != activity) {
                        Toast.makeText(activity, msg.getData().getString(Constants.TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) { //
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE_SECURE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, true);
                }
                break;
            case REQUEST_CONNECT_DEVICE_INSECURE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, false);
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled, so set up a chat session
                    mChatService = new BluetoothChatService(this, mHandler);
                } else {
                    // User did not enable Bluetooth or an error occurred
                    Log.d("Main", "BT not enabled");
                    Toast.makeText(this, R.string.bt_not_enabled_leaving,
                            Toast.LENGTH_SHORT).show();
                    this.finish();
                }
        }
    }

//------------------------------------------------------------------------------------------Bluetooth




}