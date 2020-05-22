package com.rmit.major_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Update_conditions extends AppCompatActivity {
    private EditText mintemp ;
    private EditText maxtemp ;
    private EditText minhum ;
    private EditText maxhum ;
    private EditText minlux ;
    private EditText maxlux ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_conditions);
        mintemp=(EditText)findViewById(R.id.min_temp_input);
        maxtemp=(EditText)findViewById(R.id.max_temp_input);
        minhum=(EditText)findViewById(R.id.min_hum_input);
        maxhum=(EditText)findViewById(R.id.max_hum_input);
        minlux=(EditText)findViewById(R.id.min_Lux_input);
        maxlux=(EditText)findViewById(R.id.max_lux_input);
    }
    public void save_but(View view)
    {
        final String Temprange=mintemp.getText()+"-"+maxtemp.getText();
        final String Humrange=minhum.getText()+"-"+maxhum.getText();
        final String Luxrange=minlux.getText()+"-"+maxlux.getText();

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Please hold device to scanner");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNeutralButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                MainActivity.addMessage("##00006##");
                MainActivity.addMessage(Temprange);
                MainActivity.addMessage(Humrange);
                MainActivity.addMessage(Luxrange);
               // MainActivity.helper.update("room",)


                finish();

                //finish();
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    public void back_but(View view)
    {
        finish();
    }
}
