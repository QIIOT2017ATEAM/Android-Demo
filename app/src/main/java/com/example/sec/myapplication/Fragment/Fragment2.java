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

import com.example.sec.myapplication.R;

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
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {
    private LineChart mChart;
    TextView cotextView;
    TextView no2textView;
    TextView so2textView;
    TextView o3textView;
    TextView pm25textView;
    TextView temtextView;

    int CO = 0;
    int NO2 = 0;
    int SO2 = 0;
    int O3 = 0;
    int PM25 = 0;
    int TEM = 0;

    public void onCreate(Bundle saveInstanceState){
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

        mChart = (LineChart) view.findViewById(R.id.chart1);  //xml의 차트와 연결시켜줌
        //mChart.setOnChartGestureListener(this);
        //mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);

        // add data
        setData();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // no description text
        //mChart.setDescription("Demo Line Chart");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setAxisMaxValue(220f);
        leftAxis.setAxisMinValue(-50f);
        //leftAxis.setYOffset(20f);
        //leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        //leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        //mChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);

        //  dont forget to refresh the drawing
        mChart.invalidate();

        return view;
    }
 //(""+int); 이렇게 넣어야함
    public void printAir(JSONObject JSONAir){ //공기데이터
        try {
            CO = JSONAir.getInt("SN1");

            //CO = Integer.parseInt(JSONAir.getString("SN1")); //각 키값들을 꺼내와서 String 변수로 지정
           /* NO2 = JSONAir.getDouble("SN2");
            SO2 = JSONAir.getDouble("SN3");
            O3 = JSONAir.getDouble("SN4");
            PM25 = JSONAir.getDouble("PM25");
            TEM = JSONAir.getDouble("temp");*/

            //왜 안잘라지는가
            cotextView.setText(JSONAir.getString("SN1").substring(0,4));   //toString 이 뭔가를 String으로 바꿔주는거
            no2textView.setText(JSONAir.getString("SN2"));
            so2textView.setText(JSONAir.getString("SN3"));   //toString 이 뭔가를 String으로 바꿔주는거
            o3textView.setText(JSONAir.getString("SN4"));
            pm25textView.setText(JSONAir.getString("PM25"));
            temtextView.setText(JSONAir.getString("temp"));

            //String CO = JSONAir.getString("type"); //각 키값들을 꺼내와서 String 변수로 지정


        }catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //x축넣는거
    private ArrayList<String> setXAxisValues(){
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("0");     //수치넣는거 시간으로 바궈야함
        xVals.add("1");
        xVals.add("2");
        xVals.add("3");
        xVals.add("4");

        return xVals;
    }

    //데이터값넣기
    private ArrayList<Entry> setYAxisValues(){
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for(int i=0; i<5; i++){
            yVals.add(new Entry(CO, i));

        }

        return yVals;
    }

   /*
    private ArrayList<Entry> setYAxisValues1(){

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();


        yVals1.add(new Entry(NO2, 0));
        yVals1.add(new Entry(NO2, 1));
        yVals1.add(new Entry(NO2, 2));
        yVals1.add(new Entry(NO2, 3));
        yVals1.add(new Entry(NO2, 4));

        return yVals1;
    }

    private ArrayList<Entry> setYAxisValues2(){

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        yVals2.add(new Entry(SO2, 0));
        yVals2.add(new Entry(SO2, 1));
        yVals2.add(new Entry(SO2, 2));
        yVals2.add(new Entry(SO2, 3));
        yVals2.add(new Entry(SO2, 4));

        return yVals2;
    }

    private ArrayList<Entry> setYAxisValues3(){

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
        yVals3.add(new Entry(O3, 0));
        yVals3.add(new Entry(O3, 1));
        yVals3.add(new Entry(O3, 2));
        yVals3.add(new Entry(O3, 3));
        yVals3.add(new Entry(O3, 4));

        return yVals3;
    }

    private ArrayList<Entry> setYAxisValues4(){

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
        yVals3.add(new Entry(10, 0));
        yVals3.add(new Entry(20, 1));
        yVals3.add(new Entry(30, 2));
        yVals3.add(new Entry(20, 3));
        yVals3.add(new Entry(50, 4));

        return yVals3;
    }*/

    //
    private void setData() {
        ArrayList<String> xVals = setXAxisValues();

        ArrayList<Entry> yVals = setYAxisValues();
        /*ArrayList<Entry> yVals1 = setYAxisValues1();
        ArrayList<Entry> yVals2 = setYAxisValues2();
        ArrayList<Entry> yVals3 = setYAxisValues3();
        ArrayList<Entry> yVals4 = setYAxisValues4();*/

        LineDataSet coVals;
        LineDataSet no2Vals;
        LineDataSet so2Vals;
        LineDataSet o3Vals;
        LineDataSet pm25Vals;

        // create a dataset and give it a type
        coVals = new LineDataSet(yVals, "co");  //이름바꾸는거
        /*no2Vals = new LineDataSet(yVals1, "no2");
        so2Vals = new LineDataSet(yVals2, "so2");
        o3Vals = new LineDataSet(yVals3, "o3");
        pm25Vals = new LineDataSet(yVals4, "pm25");*/

        coVals.setFillAlpha(110);
       /* no2Vals.setFillAlpha(110);
        so2Vals.setFillAlpha(110);
        o3Vals.setFillAlpha(110);
        pm25Vals.setFillAlpha(110);*/
        //set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        coVals.setColor(Color.BLACK);
       /* no2Vals.setColor(Color.BLUE);
        so2Vals.setColor(Color.RED);
        o3Vals.setColor(Color.GREEN);
        pm25Vals.setCircleColor(Color.YELLOW);*/
        //set1.setLineWidth(1f);
        //set1.setCircleRadius(3f);
        //set1.setDrawCircleHole(false);
        //set1.setValueTextSize(9f);

        //밑에 색넣는고얌얌
        //set1.setDrawFilled(true);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(coVals); // add the datasets
       /* dataSets.add(no2Vals);
        dataSets.add(so2Vals);
        dataSets.add(o3Vals);
        dataSets.add(pm25Vals);*/

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);

    }

}