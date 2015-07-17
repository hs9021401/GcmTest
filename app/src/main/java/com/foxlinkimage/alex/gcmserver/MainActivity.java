package com.foxlinkimage.alex.gcmserver;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    Button btnReg;
    static MainActivity ths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.id);
        btnReg = (Button) findViewById(R.id.reg);
        ths = this;

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetRegIdTask().execute();
                //TODO send the regID to app server
            }
        });

        Intent serv = new Intent(getApplicationContext(), MyGcmListenerService.class);
        startService(serv);
    }

    public static MainActivity  getInstace(){
        return ths;
    }

    public void updateTheTextView(final String t) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                TextView textV1 = (TextView) findViewById(R.id.rcv);
                textV1.setText(t);
            }
        });
    }

    public class GetRegIdTask extends AsyncTask<Void,String,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());
            try {
                String token = instanceID.getToken("539942271748", GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                publishProgress(token);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            txt.setText(values[0]);
        }
    }

}
