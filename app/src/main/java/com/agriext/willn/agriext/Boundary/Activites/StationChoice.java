package com.agriext.willn.agriext.Boundary.Activites;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.agriext.willn.agriext.Control.ControlSaveDataCache;
import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.Control.ControlStation;
import com.agriext.willn.agriext.Entity.Result;
import com.agriext.willn.agriext.R;

public class StationChoice extends AppCompatActivity {

    Button btnSpeakerStation;
    ImageButton connectToWifi;
    ControlStation controlStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_choice);
        btnSpeakerStation = findViewById(R.id.btnSpeakerStation);
        connectToWifi = findViewById(R.id.btnConnectToWifi);
        final ControlSpeaker controlSpeaker = new ControlSpeaker(this);

        controlStation = new ControlStation(StationChoice.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                controlSpeaker.speak("Olá, precisamos que você se conecte ao wifi da estação");
            }
        }, 500);


        connectToWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlStation.connectToWifi();
                continueToResult();
            }
        });

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, intentFilter);

        btnSpeakerStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlSpeaker.speak("USE A WIFI DA ESTAÇÃO");
            }
        });
    }

    private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            continueToResult();
        }
    };

    private void continueToResult(){
        if(controlStation.checkStation()){
            this.finish();
            startActivity(new Intent(this,ResultActivity.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        continueToResult();
    }
}
