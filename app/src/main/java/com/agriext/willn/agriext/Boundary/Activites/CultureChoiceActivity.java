package com.agriext.willn.agriext.Boundary.Activites;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.agriext.willn.agriext.Control.ControlCulture;
import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.Control.ControlStation;
import com.agriext.willn.agriext.Entity.Callback;
import com.agriext.willn.agriext.R;

public class CultureChoiceActivity extends AppCompatActivity {

    ControlCulture controlCulture;
    ControlSpeaker controlSpeaker;
    ControlStation controlStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_choice);

        controlCulture = new ControlCulture(this);
        controlSpeaker = new ControlSpeaker(this);
        controlStation = new ControlStation(this);

        final ListView listCulture;
        Button btnSpeakerCultureLayout, btnBack, btnSpeakerCalculateLayout;
        final LinearLayout linearCalculate;

        listCulture = findViewById(R.id.listCulture);
        btnSpeakerCultureLayout = findViewById(R.id.btnSpeakerCultureLayout);
        btnSpeakerCalculateLayout = findViewById(R.id.btnSpeakerCalculateLayout);
        btnBack = findViewById(R.id.btnBack);
        linearCalculate = findViewById(R.id.linearCalculate);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CultureChoiceActivity.super.onBackPressed();
            }
        });

        linearCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(controlStation.checkStation()){
                    startActivity(new Intent(CultureChoiceActivity.this, ResultActivity.class));
                    controlSpeaker.speak("Iniciando");
                } else {
                    startActivity(new Intent(CultureChoiceActivity.this, StationChoice.class));
                }
            }
        });

        controlCulture.listCultures(listCulture);
        btnSpeakerCultureLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlSpeaker.speak("Para selecionar o plantio que você possui toque em cima da imagem do plantio desejado");
            }
        });

        btnSpeakerCalculateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pulse);
                linearCalculate.startAnimation(animation);
                controlSpeaker.speak("TOQUE NA IMAGEM EM MOVIMENTO AO LADO para CALCULAR QUANTIDADE DE ÁGUA", new Callback<Boolean>() {
                    @Override
                    public void onCallback(Boolean b) {
                        if(b) linearCalculate.clearAnimation();
                    }
                });
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                controlSpeaker.speak("Selecione o tipo de plantio que você possui");
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() {
        ControlCulture.saveSelectedCultures();
        super.onBackPressed();
    }
}
