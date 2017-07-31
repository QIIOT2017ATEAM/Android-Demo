package com.example.sec.myapplication.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.io.InputStream;
import java.util.ArrayList;
import java.lang.String;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    public View view;
    TextView text_input;

    private LineChart mChart;

    public Fragment1() {
        // Required empty public constructor
    }

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    public void printHeart(int read) { //심박수 넣어줌 여기다가
        if (text_input != null)
            text_input.setText("" + read); //인트타입 잡아넣는법
        else
            Log.e("test", "" + text_input);
    }

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
            yVals.add(new Entry(Integer.parseInt((String) text_input.getText()), i));
        }

        return yVals;
    }

    //
    private void setData() {
        ArrayList<String> xVals = setXAxisValues();

        ArrayList<Entry> yVals = setYAxisValues();

        LineDataSet heartVals;

        // create a dataset and give it a type
        heartVals = new LineDataSet(yVals, "Heart rate");  //이름바꾸는거

        heartVals.setFillAlpha(110);
        //set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        heartVals.setColor(Color.BLACK);
        //set1.setCircleColor(Color.BLACK);
        //set1.setLineWidth(1f);
        //set1.setCircleRadius(3f);
        //set1.setDrawCircleHole(false);
        //set1.setValueTextSize(9f);

        //밑에 색넣는고얌얌
        //set1.setDrawFilled(true);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(heartVals); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);

    }

}
