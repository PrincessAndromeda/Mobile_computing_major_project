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
    private TextView Rm_number;
    private TextView tempIdeal;
    private TextView tempCurrent;

    private TextView humIdeal;
    private TextView humCurrent;

    private TextView lightIdeal;
    private TextView lightCurrent;
    private static String room_number;


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
        Rm_number=(TextView) findViewById(R.id.rm_number);

        Intent intent = getIntent();
        if (intent.hasExtra("Room_data")){
            data = intent.getStringArrayListExtra("Room_data");
        }
        if ((data.size() >= 5)) {
            room_number=data.get(1);
            tempIdeal.setText(data.get(2));
            tempIdeal.append(" \u00B0C");
            humIdeal.setText(data.get(3));
            humIdeal.append(" %");
            lightIdeal.setText(data.get(4));
            lightIdeal.append(" mLx");
            Rm_number.setText(room_number);
        }

        tempCurrent.setText(MainActivity.helper.gettemp(String.valueOf(Integer.valueOf(room_number))));
        tempCurrent.append(" \u00B0C");
        humCurrent.setText(MainActivity.helper.gethum(String.valueOf(Integer.valueOf(room_number))));
        humCurrent.append(" %");
        lightCurrent.setText(MainActivity.helper.getlux(String.valueOf(Integer.valueOf(room_number))));
        lightCurrent.append(" mLx");

    }

    public void buttonpress(View view)
    {
        finish();
    }
}
