package com.agriext.willn.agriext.Boundary.Activites;

import android.content.Intent;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.agriext.willn.agriext.Control.ControlCulture;
import com.agriext.willn.agriext.Control.ControlResult;
import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.Control.ControlStation;
import com.agriext.willn.agriext.Entity.Callback;
import com.agriext.willn.agriext.R;

public class MainActivity extends AppCompatActivity {

    ImageButton btnCultureSelection, btnStartWatering;
    Button btnSpeakCultureSelection, btnSpeakStartWatering;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCultureSelection = findViewById(R.id.btnCultureSelection);
        btnSpeakCultureSelection = findViewById(R.id.btnSpeakerCultureSelection);

        btnStartWatering = findViewById(R.id.btnStartWatering);
        btnSpeakStartWatering = findViewById(R.id.btnSpeakerStartWatering);

        final ControlSpeaker controlSpeaker = new ControlSpeaker(this);
        final ControlStation controlStation = new ControlStation(this);

        ControlCulture.initCultureData(this);

        btnStartWatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(controlStation.checkStation()){
                    startActivity(new Intent(MainActivity.this, ResultActivity.class));
                    controlSpeaker.speak("Iniciando");
                } else {
                    startActivity(new Intent(MainActivity.this, StationChoice.class));
                }
            }
        });

        btnCultureSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CultureChoiceActivity.class));
                controlSpeaker.speak("Abrindo");
            }
        });

        btnSpeakStartWatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pulse);
                btnStartWatering.startAnimation(animation);
                controlSpeaker.speak("clique abaixo na imagem em movimento abaixo para calcular a irrigação", new Callback<Boolean>() {
                    @Override
                    public void onCallback(Boolean b) {
                        if(b) btnStartWatering.clearAnimation();
                    }
                });
            }
        });

        btnSpeakCultureSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pulse);
                btnCultureSelection.startAnimation(animation);
                controlSpeaker.speak("clique abaixo na imagem em movimento para selecionar os seus tipos de plantio", new Callback<Boolean>() {
                    @Override
                    public void onCallback(Boolean b) {
                        if(b) btnCultureSelection.clearAnimation();
                    }
                });
            }
        });
    }
}
