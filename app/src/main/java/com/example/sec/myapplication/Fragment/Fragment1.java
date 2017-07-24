package com.example.sec.myapplication.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sec.myapplication.MainActivity;
import com.example.sec.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    public View view;
    TextView text_input = (TextView) view.findViewById(R.id.text_input);

    public Fragment1() {
        // Required empty public constructor
    }
    public void setvalue(String read){
        if(text_input != null)
            text_input.setText(read);
        else
            Log.e("test","" + text_input);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1,container, false);

        //text_input.setText(getNumber().toString());
        // text_input.setText(readMessage.toString());
        //MainActivity mainActivity = new MainActivity();  //mainactivity load
        // Inflate the layout for this fragment

        return view;
    }

}