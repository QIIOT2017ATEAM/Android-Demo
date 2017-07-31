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

        mChart = (LineChart) view.findViewById(R.id.chart1);
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

    public void printAir(JSONObject JSONAir){ //공기데이터
        try {
            cotextView.setText(JSONAir.getString("type"));   //toString 이 뭔가를 String으로 바꿔주는거
            no2textView.setText(JSONAir.getString("time"));
            cotextView.setText(JSONAir.getString("SN1"));   //toString 이 뭔가를 String으로 바꿔주는거
            no2textView.setText(JSONAir.getString("SN2"));

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //x축넣는거
    private ArrayList<String> setXAxisValues(){
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("10");
        xVals.add("20");
        xVals.add("30");
        xVals.add("30.5");
        xVals.add("40");

        return xVals;
    }

    //데이터값넣기
    private ArrayList<Entry> setYAxisValues(){
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        yVals.add(new Entry(60, 0));
        yVals.add(new Entry(48, 1));
        yVals.add(new Entry(70.5f, 2));
        yVals.add(new Entry(100, 3));
        yVals.add(new Entry(180.9f, 4));

        return yVals;
    }

    //데이터값넣기
    private ArrayList<Entry> setYAxisValues1(){

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        yVals1.add(new Entry(50, 0));
        yVals1.add(new Entry(70, 1));
        yVals1.add(new Entry(70, 2));
        yVals1.add(new Entry(100, 3));
        yVals1.add(new Entry(180, 4));

        return yVals1;
    }

    //
    private void setData() {
        ArrayList<String> xVals = setXAxisValues();

        ArrayList<Entry> yVals = setYAxisValues();
        ArrayList<Entry> yVals1 = setYAxisValues1();

        LineDataSet set1;
        LineDataSet set2;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "DataSet 1");
        set2 = new LineDataSet(yVals1, "DataSEt 2");

        set1.setFillAlpha(110);
        set2.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        //set1.setCircleRadius(3f);
        //set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);

        //밑에 색넣는고얌얌
        //set1.setDrawFilled(true);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets
        dataSets.add(set2);

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);

    }

}