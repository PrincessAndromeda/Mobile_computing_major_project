package com.rmit.major_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class current_contents extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<>();
    private TextView Rm_number;
    private TextView origin;
    private TextView description;
    private static String room_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_contents);

        origin=(TextView) findViewById(R.id.Origin);
        description=(TextView) findViewById(R.id.Description_of_contents);
        Rm_number=(TextView) findViewById(R.id.rm_number);
        Intent intent = getIntent();
        if (intent.hasExtra("Room_data")){
            data = intent.getStringArrayListExtra("Room_data");
        }
        if ((data.size() > 0)) {
            room_number=data.get(1);
            origin.setText(data.get(2));
            description.setText(data.get(3));
            Rm_number.setText(room_number);
        }



    }
    public void buttonpress(View view)
    {
        finish();
    }
}

