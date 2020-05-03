package com.rmit.major_project;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;

//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends Activity
        implements NfcAdapter.OnNdefPushCompleteCallback,
        NfcAdapter.CreateNdefMessageCallback {
    //The array lists to hold our messages
    private ArrayList<String> messagesToSendArray = new ArrayList<>();
    private ArrayList<String> messagesReceivedArray = new ArrayList<>();

    //Text boxes to add and display our messages
    //private EditText txtBoxAddMessage;
    private TextView txtReceivedMessages;
    //private TextView txtMessagesToSend;

    private NfcAdapter mNfcAdapter;

    public static int  target_door=0;




    public void ScannerRequestDialogue(final String message){

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Please hold device to scanner");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNeutralButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                addMessage(message);

                //finish();
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void AccessDeniedAlert(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Access Denied.");
        alertDialogBuilder.setMessage("Please contact your system Administrator for access");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNeutralButton("Close",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void Get_target_door()
    {
        ScannerRequestDialogue("##00000##");
        //String newMessage ="##00001##";
        //addMessage(newMessage);


    }
    public void Unlock_door(View view)
    {
        if(target_door==0) // check that there is a target door
        {
            Get_target_door();


        }
        else {
            //Check if user has access to door(BD lookup) TODO
            //if(USER HAS ACCESS){
            ScannerRequestDialogue("##00010##");
            //}
        /*else
            {
                AccessDeniedAlert();
            }*/
        }


    }
    public void View_current_conds(View view)
    {
        if(target_door==0) // check that there is a target door
        {
            Get_target_door();


        }
        else
            {
                ScannerRequestDialogue("##00002##");//Send climate data NFC message
             }

        //wait for NFC handler to handel the response
    }
    public void Update_contents(View view)
    {
        if(target_door==0) // check that there is a target door
        {
            Get_target_door();


        }
        else {
            to_update_conts_screen();// move to update_conts activity
            while (messagesToSendArray==null)
            { //Empty loop}
            }
            //TODO
            //get user to enter data
            // take data and send it in update contents NFC message
            // display the request scanner screen
            // wait for the scanner to send back a room contents NFC message
        }
    }
    public void Update_conds(View view)
    {
        if(target_door==0) // check that there is a target door
        {
            Get_target_door();

        }
        else {
            to_update_conds_screen();// move to update_conts activity
            while (messagesToSendArray == null) { //Empty loop}
            }
        }
        // move to update_Conds activity
        //get user to enter data
        // take data and send it in update conditions NFC message
        // display the request scanner screen
        // wait for the scanner to send back an ACK NFC message

    }

    public void addMessage(String message) {
        messagesToSendArray.add(message);

        Toast.makeText(this, "Added Message", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNdefPushComplete(NfcEvent event) {
        messagesToSendArray.clear();
        //This is called when the system detects that our NdefMessage was
        //Successfully sent
    }

    public NdefRecord[] createRecords() {
        NdefRecord[] records = new NdefRecord[messagesToSendArray.size() + 1];
        //To Create Messages Manually if API is less than
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            for (int i = 0; i < messagesToSendArray.size(); i++){
                byte[] payload = messagesToSendArray.get(i).
                        getBytes(Charset.forName("UTF-8"));
                NdefRecord record = new NdefRecord(
                        NdefRecord.TNF_WELL_KNOWN,      //Our 3-bit Type name format
                        NdefRecord.RTD_TEXT,            //Description of our payload
                        new byte[0],                    //The optional id for our Record
                        payload);                       //Our payload for the Record

                records[i] = record;
            }
        }
        //Api is high enough that we can use createMime, which is preferred.
        else {
            for (int i = 0; i < messagesToSendArray.size(); i++){
                byte[] payload = messagesToSendArray.get(i).
                        getBytes(Charset.forName("UTF-8"));

                NdefRecord record = NdefRecord.createMime("text/plain",payload);
                records[i] = record;
            }
        }
        records[messagesToSendArray.size()] = NdefRecord.createApplicationRecord("com.rmit.nfctestproj");
        return records;
    }


    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        //This will be called when another NFC capable device is detected.
        if (messagesToSendArray.size() == 0) {
            return null;
        }
        //We'll write the createRecords() method in just a moment
        NdefRecord[] recordsToAttach = createRecords();
        //When creating an NdefMessage we need to provide an NdefRecord[]
        return new NdefMessage(recordsToAttach);
    }

    private void handleNfcIntent(Intent NfcIntent) {

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(NfcIntent.getAction())) {

            Parcelable[] receivedArray =
                    NfcIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if(receivedArray != null) {
                messagesReceivedArray.clear();
                NdefMessage receivedMessage = (NdefMessage) receivedArray[0];
                NdefRecord[] attachedRecords = receivedMessage.getRecords();

                for (NdefRecord record:attachedRecords) {
                    String string = new String(record.getPayload());

                    //Make sure we don't pass along our AAR (Android Applicatoin Record)
                    if (string.equals(getPackageName())) { continue; }
                    messagesReceivedArray.add(string);


                }
                if((messagesReceivedArray.get(0).contains("##00001##"))) //door_number
                {
                    if(messagesReceivedArray.size()>1)
                    {
                        target_door=Integer.parseInt(messagesReceivedArray.get(1));
                        Toast.makeText(this,"Door number is "+target_door,Toast.LENGTH_LONG).show();
                    }
                    else
                    {Toast.makeText(this,"ERROR NO DOOR NUMBER SENT",Toast.LENGTH_LONG).show(); }
                }
                if((messagesReceivedArray.get(0).contains("##00003##")))//sent current conditions
                {
                    if(messagesReceivedArray.size()>3) {
                        to_cond_screen(messagesReceivedArray);
                    }
                    else{Toast.makeText(this,"ERROR NOT ENOUGH CONDITION DATA SENT: "+messagesReceivedArray.size(),Toast.LENGTH_LONG).show();}

                }
                if((messagesReceivedArray.get(0).contains("##00005##")))// sent current contents
                {
                    if(messagesReceivedArray.size()>3) {
                        to_cont_screen(messagesReceivedArray);
                    }
                    else {Toast.makeText(this, "ERROR NOT ENOUGH CONTENTS DATA SENT: " + messagesReceivedArray.size(), Toast.LENGTH_LONG).show(); }
                }
                if((messagesReceivedArray.get(0).contains("##00007##"))) //sent update ack conditions
                {
                    if(messagesReceivedArray.size()>3) {
                        to_cond_screen(messagesReceivedArray);
                    }
                    else{Toast.makeText(this,"ERROR NOT ENOUGH CONDITION ACK DATA SENT: "+messagesReceivedArray.size(),Toast.LENGTH_LONG).show();}
                }
                if((messagesReceivedArray.get(0).contains("##00009##"))) //sent update ack contents
                {
                    if(messagesReceivedArray.size()>3) {
                        to_cont_screen(messagesReceivedArray);
                    }
                    else {Toast.makeText(this, "ERROR NOT ENOUGH CONTENTS ACK DATA SENT: " + messagesReceivedArray.size(), Toast.LENGTH_LONG).show(); }
                }
                if((messagesReceivedArray.get(0).contains("##00011##"))) //sent update ack contents
                {
                    if(messagesReceivedArray.size()>1)
                    {
                        if (Integer.parseInt(messagesReceivedArray.get(1)) == 1) {
                            Toast.makeText(this, "DOOR UNLOCKED", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this, "DOOR LOCKED TRY AGAIN", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {Toast.makeText(this, "ERROR NO DOOR ACK" + messagesReceivedArray.size(), Toast.LENGTH_LONG).show(); }
                }

                updateTextViews();//REMEMBER TO REMOVE TODO
            }
            else {
                Toast.makeText(this, "Received Blank Parcel", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onNewIntent(Intent intent) {
        handleNfcIntent(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
        updateTextViews();
        //handleNfcIntent(getIntent());
    }

    public void to_cond_screen(ArrayList data) {
        Intent myIntent = new Intent(MainActivity.this, ViewCurrentConds.class);
        myIntent.putStringArrayListExtra("Room_data", data);
        startActivity(myIntent); //if do not need to get data back.

    }
    public void to_cont_screen(ArrayList data) { //TODO
        /*Intent myIntent = new Intent(MainActivity.this, ViewCurrentConds.class);
        myIntent.putStringArrayListExtra("Room_data", data);
        startActivity(myIntent); //if do not need to get data back.*/

    }

    public void to_update_conts_screen() {
        Intent myIntent = new Intent(MainActivity.this, Update_conts.class);
        startActivity(myIntent); //if do not need to get data back.

    }

    public void to_update_conds_screen() {
        Intent myIntent = new Intent(MainActivity.this, Update_conditions.class);
        startActivity(myIntent); //if do not need to get data back.
    }
    /*
    //Saving as a base for future use

    public void toChild() {// function named in OnClick
        Intent myIntent = new Intent(MainActivity.this, ViewCurrentConds.class);
        startActivity(myIntent); //if do not need to get data back.

    }
    */


    private  void updateTextViews() //FOR DEBUGGING
    {

        txtReceivedMessages.setText("Messages Received:\n");
        //Populate our list of messages we have received
        if (messagesReceivedArray.size() > 0) {
            for (int i = 0; i < messagesReceivedArray.size(); i++) {
                txtReceivedMessages.append(messagesReceivedArray.get(i));
                txtReceivedMessages.append("\n");
            }
        }
    }
/*
    //Save our Array Lists of Messages for if the user navigates away
    @Override
    public void onSaveInstanceState( Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("messagesToSend", messagesToSendArray);
        savedInstanceState.putStringArrayList("lastMessagesReceived",messagesReceivedArray);
    }

    //Load our Array Lists of Messages for when the user navigates back
    @Override
    public void onRestoreInstanceState( Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        messagesToSendArray = savedInstanceState.getStringArrayList("messagesToSend");
        messagesReceivedArray = savedInstanceState.getStringArrayList("lastMessagesReceived");
    }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Check if NFC is available on device
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(mNfcAdapter != null) {
            //This will refer back to createNdefMessage for what it will send
            mNfcAdapter.setNdefPushMessageCallback(this, this);

            //This will be called if the message is sent successfully
            mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }
        else {
            Toast.makeText(this, "NFC not available on this device",
                    Toast.LENGTH_SHORT).show();
        }



        txtReceivedMessages = (TextView) findViewById(R.id.Text_text);//DEBUGGING
        updateTextViews();//DEBUGGING REMOVE TODO

        if (getIntent().getAction().equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            handleNfcIntent(getIntent());
        }
    }
}