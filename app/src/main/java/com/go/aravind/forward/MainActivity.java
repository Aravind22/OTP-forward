package com.go.aravind.forward;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.lang.String;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView.OnEditorActionListener;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //SMS receiving method
     class SmsReceiver extends BroadcastReceiver {
        private String TAG = SmsReceiver.class.getSimpleName();

        public SmsReceiver() {
        }


        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String str = "";
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];
                for (int i=0; i < msgs.length; i++) {
                   msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    str += msgs[i].getMessageBody().toString();
                    str += "\n";//SMS copied to str  This is where the checker has to be implemented
                    final String fin = str; //only final members can be used within the inner functions,so Copying str to final fin
                    //Check SMS Sending method for Usage of fin

                    //To get the phone number from user
                    final EditText editText  = (EditText) findViewById(R.id.edittext);
                    editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                        public  boolean onEditorAction(TextView v, int i, KeyEvent event) {

                            boolean handled = false;
                            if (i == EditorInfo.IME_ACTION_GO){

                                Toast toast = new Toast(getApplicationContext());
                                toast.makeText(MainActivity.this,"Your Forward Number is: " + editText.getText(), toast.LENGTH_SHORT).show();
                            }
                            String ex = editText.getText().toString();//Storing the input in ex
                            int exx = Integer.parseInt(ex);//Converting it to integer  (Some confusions here)
                            //Sending the sms
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(ex,null,fin,null,null);//For using fin here it has to be final
                            return handled;

                }
                    });

            }
        }
    }
            }



}



}
