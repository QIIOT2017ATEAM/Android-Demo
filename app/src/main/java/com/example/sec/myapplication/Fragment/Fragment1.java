package com.example.sec.myapplication.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.sec.myapplication.MainActivity;
import com.example.sec.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.lang.String;

import static android.R.attr.id;
import static android.R.attr.text;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    public View view;
    TextView text_input;
    int Hcount = 0;
    int hrt = 0;

    public LineChart Hchart;
    public ArrayList<String> hxVals = new ArrayList<String>();
    public ArrayList<Entry> Heartvalue = new ArrayList<Entry>();

    public Fragment1() {
        // Required empty public constructor
    }

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    public void printHeart(int hr) { //심박수 넣어줌 여기다가
        Hcount++;
        hrt = hr;
        if (text_input != null)
            text_input.setText("" + hr); //인트타입 잡아넣는법
        else
            Log.e("test", "" + text_input);
        //setData();
    }
    /*
    public void maxHeart(int hr) { //심박수 넣어줌 여기다가
        Hcount++;
        if ( (max_val != null) || (Integer.parseInt(max_val.getText) < hr) )
            max_val.setText("" + hr); //인트타입 잡아넣는법
        //setData();
    }*/

    /*
    public void minHeart(int hr) { //심박수 넣어줌 여기다가
        Hcount++;
        if ( (min_val != null) || (Integer.parseInt(min_val.getText) > hr) )
            min_val.setText("" + hr); //인트타입 잡아넣는법
        //setData();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1,container, false);  //xml 에 씌여져 있는 view 의 정의를 실제 view 객체로 만드는 역할을
                                                                       //LayoutInfalter 랑 이거 차이가 뭔가용??

        text_input = (TextView) view.findViewById(R.id.text_input);

        //text_input.setText(getNumber().toString());
        // text_input.setText(readMessage.toString());
        //MainActivity mainActivity = new MainActivity();  //mainactivity load
        // Inflate the layout for this fragment

        //startRocketTweenAnimation();
        return view;
    }
    /*
    private void startRocketTweenAnimation(){
        Animation heart_anim = AnimationUtils.loadAnimation(this.getActivity(), R.id.scale);
        text_input.startAnimation(heart_anim);
    }
    */

    public void setData() {
        Hcount++;

        //ArrayList<String> xVals = new ArrayList<String>(); //x축세팅 위로올림
        hxVals.add(String.valueOf(Hcount)); //heart x바라서 이름이럼


        //ArrayList<Entry> covalue = new ArrayList<Entry>(); //y축세팅 위로올림
        Heartvalue.add(new Entry(hrt, Hcount));


        LineDataSet heart_chart = new LineDataSet(Heartvalue, "Heart");

        heart_chart.setAxisDependency(YAxis.AxisDependency.LEFT); //creat lineDataSet

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(heart_chart);

        LineData heart_data = new LineData(hxVals, dataSets);

        Hchart.setData(heart_data);
        Hchart.invalidate(); //  dont forget to refresh the drawing
    }

}
