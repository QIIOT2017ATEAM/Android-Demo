package com.example.sec.myapplication.Fragment;

import android.graphics.Color;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sec.myapplication.MainActivity;
import com.example.sec.myapplication.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    public LineChart chart;
    public ArrayList<String> axVals = new ArrayList<String>();

    public ArrayList<Entry> covalue = new ArrayList<Entry>();
    public ArrayList<Entry> no2value = new ArrayList<Entry>();
    public ArrayList<Entry> so2value = new ArrayList<Entry>();
    public ArrayList<Entry> o3value = new ArrayList<Entry>();
    public ArrayList<Entry> pm25value = new ArrayList<Entry>();

    TextView cotextView;
    TextView no2textView;
    TextView so2textView;
    TextView o3textView;
    TextView pm25textView;
    TextView temtextView;

    int CO, NO2, SO2, O3, PM25, TEM  = 0;

    int count = 0;


    public void onCreate(Bundle saveInstanceState){ //여기서 변수를 지정해주면 초기화된다.
        super.onCreate(saveInstanceState);

    }


    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        cotextView = (TextView)view.findViewById(R.id.cotextView);
        no2textView = (TextView)view.findViewById(R.id.no2textView);
        so2textView = (TextView)view.findViewById(R.id.so2textView);
        o3textView = (TextView)view.findViewById(R.id.o3textView);
        pm25textView = (TextView)view.findViewById(R.id.pm25textView);
        temtextView = (TextView)view.findViewById(R.id.temtextView);

        chart = (LineChart)view.findViewById(R.id.chart); //차트만듦


        return view;
    }
 //(""+int); 이렇게 넣어야함
    public void printAir(JSONObject JSONAir){ //공기데이터
        try {
            CO = JSONAir.getInt("CO");
            NO2 = JSONAir.getInt("NO2");
            SO2 = JSONAir.getInt("SO2");
            O3 = JSONAir.getInt("O3");
            PM25 = JSONAir.getInt("PM25");


            //왜 안잘라지는가
            cotextView.setText(JSONAir.getString("CO")); //textView에 JSON받은거 바로넣는거
            no2textView.setText(JSONAir.getString("NO2"));
            so2textView.setText(JSONAir.getString("SO2"));
            o3textView.setText(JSONAir.getString("O3"));
            pm25textView.setText(JSONAir.getString("PM25"));
            temtextView.setText(JSONAir.getString("temp"));

            //String CO = JSONAir.getString("type"); //각 키값들을 꺼내와서 String 변수로 지정
            setData();

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setData() {

        count++;

        //ArrayList<String> xVals = new ArrayList<String>(); //x축세팅 위로올림
        axVals.add(String.valueOf(count));


        //ArrayList<Entry> covalue = new ArrayList<Entry>(); //y축세팅 위로올림
        covalue.add(new Entry(CO, count));
        so2value.add(new Entry(SO2, count));
        no2value.add(new Entry(NO2, count));
        o3value.add(new Entry(O3, count));
        pm25value.add(new Entry(PM25, count));

        LineDataSet cochart = new LineDataSet(covalue, "CO");
        LineDataSet so2chart = new LineDataSet(so2value, "SO2");
        LineDataSet no2chart = new LineDataSet(no2value, "NO2");
        LineDataSet o3chart = new LineDataSet(o3value, "O3");
        LineDataSet pm25chart = new LineDataSet(pm25value, "PM25");

        cochart.setAxisDependency(YAxis.AxisDependency.LEFT); //creat lineDataSet
        so2chart.setAxisDependency(YAxis.AxisDependency.LEFT);
        no2chart.setAxisDependency(YAxis.AxisDependency.LEFT);
        o3chart.setAxisDependency(YAxis.AxisDependency.LEFT);
        pm25chart.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(cochart);
        dataSets.add(so2chart);
        dataSets.add(no2chart);
        dataSets.add(o3chart);
        dataSets.add(pm25chart);

        LineData codata = new LineData(axVals, dataSets);
        LineData so2data = new LineData(axVals, dataSets);
        LineData no2data = new LineData(axVals, dataSets);
        LineData o3data = new LineData(axVals, dataSets);
        LineData pm25data = new LineData(axVals, dataSets);


        chart.setData(codata);
        chart.setData(so2data);
        chart.setData(no2data);
        chart.setData(o3data);
        chart.setData(pm25data);

        chart.invalidate(); //  dont forget to refresh the drawing
    }



}