package com.example.sec.myapplication.Fragment;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sec.myapplication.R;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

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
        LineChart chart1 = (LineChart) view.findViewById(R.id.chart1);
        LineChart chart2 = (LineChart) view.findViewById(R.id.chart2);
        LineChart chart3 = (LineChart) view.findViewById(R.id.chart3);
        LineChart chart4 = (LineChart) view.findViewById(R.id.chart4);
        LineChart chart5 = (LineChart) view.findViewById(R.id.chart5);
        LineChart chart6 = (LineChart) view.findViewById(R.id.chart6);

        ArrayList<Entry> valsComp1 = new ArrayList<>();
        ArrayList<Entry> valsComp2 = new ArrayList<>();
        ArrayList<Entry> valsComp3 = new ArrayList<>();
        ArrayList<Entry> valsComp4 = new ArrayList<>();
        ArrayList<Entry> valsComp5 = new ArrayList<>();
        ArrayList<Entry> valsComp6 = new ArrayList<>();

        //표시할 데이터 추가
        valsComp1.add(new Entry(0f,0));
        valsComp2.add(new Entry(0f,1));
        valsComp3.add(new Entry(0f,2));
        valsComp4.add(new Entry(0f,3));
        valsComp5.add(new Entry(0f,4));
        valsComp6.add(new Entry(0f,5));

        LineDataSet setComp1 = new LineDataSet(valsComp1, "CO");
        LineDataSet setComp2 = new LineDataSet(valsComp2, "NO2");
        LineDataSet setComp3 = new LineDataSet(valsComp3, "SO2");
        LineDataSet setComp4 = new LineDataSet(valsComp4, "O3");
        LineDataSet setComp5 = new LineDataSet(valsComp5, "PM-2.5");
        LineDataSet setComp6 = new LineDataSet(valsComp6, "PM-10");

        //setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        //setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        //setComp3.setAxisDependency(YAxis.AxisDependency.LEFT);
        //setComp4.setAxisDependency(YAxis.AxisDependency.LEFT);
        //setComp5.setAxisDependency(YAxis.AxisDependency.LEFT);
        //setComp6.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<LineDataSet> dataSets1 = new ArrayList<>();
        dataSets1.add(setComp1);

        ArrayList<LineDataSet> dataSets2 = new ArrayList<>();
        dataSets2.add(setComp2);

        ArrayList<LineDataSet> dataSets3 = new ArrayList<>();
        dataSets3.add(setComp3);

        ArrayList<LineDataSet> dataSets4 = new ArrayList<>();
        dataSets4.add(setComp4);

        ArrayList<LineDataSet> dataSets5 = new ArrayList<>();
        dataSets5.add(setComp5);

        ArrayList<LineDataSet> dataSets6 = new ArrayList<>();
        dataSets6.add(setComp6);

        ArrayList<String> xVals = new ArrayList<>();
        xVals.add("1,Q");
        xVals.add("2,Q");
        xVals.add("3,Q");
        xVals.add("4,Q");

        //LineDataSet dataSet = new LineDataSet(valsComp1, "# of Calls");
        //LineData lineData = new LineData(dataSet);

        //return view;

        LineData data1 = new LineData(xVals,dataSets1);
        chart1.setData(data1);
        chart1.invalidate();

        LineData data2 = new LineData(xVals,dataSets2);
        chart2.setData(data2);
        chart2.invalidate();

        LineData data3 = new LineData(xVals,dataSets3);
        chart3.setData(data3);
        chart3.invalidate();

        LineData data4 = new LineData(xVals,dataSets4);
        chart4.setData(data4);
        chart4.invalidate();

        LineData data5 = new LineData(xVals,dataSets5);
        chart5.setData(data5);
        chart5.invalidate();

        LineData data6 = new LineData(xVals,dataSets6);
        chart6.setData(data6);
        chart6.invalidate();

        // Inflate the layout for this fragment

        return view;
    }
}