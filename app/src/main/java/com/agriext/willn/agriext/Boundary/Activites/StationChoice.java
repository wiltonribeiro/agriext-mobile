package com.agriext.willn.agriext.Boundary.Activites;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.Control.ControlStation;
import com.agriext.willn.agriext.R;

public class StationChoice extends AppCompatActivity {

    Button btnSpeakerStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_choice);
        btnSpeakerStation = findViewById(R.id.btnSpeakerStation);
        final ControlSpeaker controlSpeaker = new ControlSpeaker(this);

        ControlStation controlStation = new ControlStation(StationChoice.this);
        if(controlStation.readFromFile()){
            ActivityCompat.finishAffinity(this);
            startActivity(new Intent(this,MainActivity.class));
        } else{
            ListView listView = findViewById(R.id.listStation);
            controlStation.loadStationList(listView);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    controlSpeaker.speak("Seja bem vindo. Selecione a estação mais próxima de você, na lista abaixo, para começarmos");
                }
            }, 500);
        }

        btnSpeakerStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlSpeaker.speak("ESCOLHA A SUA ESTAÇÃO");
            }
        });
    }
}
