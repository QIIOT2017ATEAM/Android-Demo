package com.example.sec.myapplication.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sec.myapplication.MainActivity;
import com.example.sec.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    public TextView text_input;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,null);
        text_input = (TextView) view.findViewById(R.id.text_input);

        //MainActivity mainActivity = new MainActivity();  //mainactivity load


        // Inflate the layout for this fragment
        return view;
    }

}
