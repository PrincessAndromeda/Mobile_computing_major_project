package com.rmit.major_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewCurrentConds extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<>();
    private TextView tempIdeal;
    private TextView tempCurrent;

    private TextView humIdeal;
    private TextView humCurrent;

    private TextView lightIdeal;
    private TextView lightCurrent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_current_conds);
        tempIdeal=(TextView) findViewById(R.id.Temp_Ideal_range_text);
        tempCurrent=(TextView) findViewById(R.id.Temp_Current_Value_text);
        humIdeal=(TextView) findViewById(R.id.Humidity_Ideal_range_text);
        humCurrent=(TextView) findViewById(R.id.Humidity_Current_ValueText);
        lightIdeal=(TextView) findViewById(R.id.Light_Ideal_range_text);
        lightCurrent=(TextView) findViewById(R.id.Light_Current_Value_text);

        Intent intent = getIntent();
        if (intent.hasExtra("Room_data")){
            data = intent.getStringArrayListExtra("Room_data");
        }
        if ((data.size() > 0)&(data.size()<=4)) {
            tempIdeal.setText(data.get(1));
            tempIdeal.append(" \u00B0C");
            humIdeal.setText(data.get(2));
            humIdeal.append(" %");
            lightIdeal.setText(data.get(3));
            lightIdeal.append(" mLx");
        }
        tempCurrent.setText("Stub");
        humCurrent.setText("Stub");
        lightCurrent.setText("Stub");

    }

    public void buttonpress(View view)
    {
        finish();
    }
}
