package com.rmit.major_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Update_conts extends Activity implements AdapterView.OnItemSelectedListener {
    Spinner on_lend_from;
    Spinner user_lvl;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_conts2);
        description=(EditText) findViewById(R.id.rm_desc);
        on_lend_from = (Spinner) findViewById(R.id.On_lend_from);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.museums, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        on_lend_from.setAdapter( new NothingSelectedSpinnerAdapter(adapter1, R.layout.contact_spinner_row_nothing_selected_lend ,this));
        on_lend_from.setOnItemSelectedListener(this);
        user_lvl = (Spinner) findViewById(R.id.Who_can_access);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.User_access_vlv, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        user_lvl.setAdapter(new NothingSelectedSpinnerAdapter(adapter2, R.layout.contact_spinner_row_nothing_selected_users ,this));
        user_lvl.setOnItemSelectedListener(this);




    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        //parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    public void back_but(View view)
    {
        finish();
    }

    public void save_but(View view)
    {
        final String onlend=on_lend_from.getSelectedItem().toString();
        final String User_lvl=user_lvl.getSelectedItem().toString();
        final String Room_desc=description.getText().toString();

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Please hold device to scanner");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNeutralButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                MainActivity.addMessage("##00008##");
                MainActivity.addMessage(onlend);
                MainActivity.addMessage(User_lvl);
                MainActivity.addMessage(Room_desc);
                finish();
                }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}




