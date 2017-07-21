package com.example.sec.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {


    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        //데이터셋 설정
        LineChart chart = (LineChart) view.findViewById(R.id.chart);
        //LineChart chart2 = (LineChart)findViewById(R.id.chart2);
        //LineChart chart3 = (LineChart)findViewById(R.id.chart3);
        //LineChart chart4 = (LineChart)findViewById(R.id.chart4);
        //LineChart chart5 = (LineChart)findViewById(R.id.chart5);
        //LineChart chart6 = (LineChart)findViewById(R.id.chart6);

        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
        LineDataSet setComp1 = new LineDataSet(valsComp1, "CO");
        LineDataSet setComp2 = new LineDataSet(valsComp1, "O3");
        LineDataSet setComp3 = new LineDataSet(valsComp1, "NO2");
        LineDataSet setComp4 = new LineDataSet(valsComp1, "SO2");
        LineDataSet setComp5 = new LineDataSet(valsComp1, "PM-2.5");
        LineDataSet setComp6 = new LineDataSet(valsComp1, "PM-10");


        //ArrayList<Entry> valsComp2 = new ArrayList<Entry>();
        //ArrayList<Entry> valsComp3 = new ArrayList<Entry>();
        //ArrayList<Entry> valsComp4 = new ArrayList<Entry>();
        //ArrayList<Entry> valsComp5 = new ArrayList<Entry>();
        //ArrayList<Entry> valsComp6 = new ArrayList<Entry>();

        //표시랑 데이터 추가
        valsComp1.add(new Entry(0f,0));
        valsComp1.add(new Entry(0f,1));
        valsComp1.add(new Entry(0f,2));
        valsComp1.add(new Entry(0f,3));
        valsComp1.add(new Entry(0f,4));
        valsComp1.add(new Entry(0f,5));




        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);
        dataSets.add(setComp3);
        dataSets.add(setComp4);
        dataSets.add(setComp5);
        dataSets.add(setComp6);

        //ArrayList<LineDataSet> dataSets2 = new ArrayList<LineDataSet>();
        //dataSets2.add(setComp2);

        //ArrayList<LineDataSet> dataSets3 = new ArrayList<LineDataSet>();
        //dataSets3.add(setComp3);

        //ArrayList<LineDataSet> dataSets4 = new ArrayList<LineDataSet>();
        //dataSets4.add(setComp4);

        //ArrayList<LineDataSet> dataSets5 = new ArrayList<LineDataSet>();
        //dataSets5.add(setComp5);

        //ArrayList<LineDataSet> dataSets6 = new ArrayList<LineDataSet>();
        //dataSets6.add(setComp6);

        /*ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("1,Q");
        xVals.add("2,Q");
        xVals.add("3,Q");
        xVals.add("4,Q");

        LineData data = new LineData(xVals, dataSets);*/
        LineDataSet dataSet = new LineDataSet(valsComp1, "# of Calls");
        LineData lineData = new LineData(dataSet);

        chart.setData(lineData);
        chart.invalidate();
        return view;

        //LineData data2 = new LineData(xVals,dataSets2);
        //chart2.setData(data2);
        //chart2.invalidate();

        //LineData data3 = new LineData(xVals,dataSets3);
        //chart3.setData(data3);
        //chart3.invalidate();

        //LineData data4 = new LineData(xVals,dataSets4);
        //chart4.setData(data4);
        //chart4.invalidate();

        //LineData data5 = new LineData(xVals,dataSets5);
        //chart5.setData(data5);
        //chart5.invalidate();

        //LineData data6 = new LineData(xVals,dataSets6);
        //chart6.setData(data6);
        //chart6.invalidate();
        // Inflate the layout for this fragment

    }
}