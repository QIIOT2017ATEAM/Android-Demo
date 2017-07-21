package com.example.sec.myapplication;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
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
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;


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
//핸들러가 UI바꾸는거 돠주는거
    private final Handler mHandler = new Handler() {
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
                case Constants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    mConversationArrayAdapter.add("Me:  " + writeMessage);
                    break;
                case Constants.MESSAGE_READ://핸들러로와서 메세지 받을경우에 일로와서 이거실행
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    mConversationArrayAdapter.add(mConnectedDeviceName + ":  " + readMessage);
                    break;
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
                Toast.makeText(this, "connect", Toast.LENGTH_SHORT).show();
                return true;
            }//꺼져있을경우 블루투스 켜주는 메소드(창 띄어서 켤지말지 결정함)


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

    /**
     * Establish connection with other device
     *
     * @param data   An {@link Intent} with {@link DeviceListActivity#EXTRA_DEVICE_ADDRESS} extra.
     * @param secure Socket Security type - Secure (true) , Insecure (false)
     */
    private void connectDevice(Intent data, boolean secure) { //상대방 블루투스 주소받아와서
        // Get the device MAC address
        String address = data.getExtras()
                .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
        // Get the BluetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        // Attempt to connect to the device
        mChatService.connect(device, secure);
    }

}