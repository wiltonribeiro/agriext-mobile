package com.agriext.willn.agriext.Boundary.Activites;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.Control.ControlStation;
import com.agriext.willn.agriext.Entity.Callback;
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
                controlSpeaker.speak("Olá, você não está conectado a estação, por isso precisamos que você se conecte na estação");
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
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pulse);
                connectToWifi.startAnimation(animation);

                controlSpeaker.speak("clique abaixo na imagem em movimento para SE CONECTAR A ESTAÇÃO", new Callback<Boolean>() {
                    @Override
                    public void onCallback(Boolean b) {
                        if(b) connectToWifi.clearAnimation();
                    }
                });
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
