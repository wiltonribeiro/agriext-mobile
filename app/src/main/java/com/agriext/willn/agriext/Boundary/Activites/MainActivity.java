package com.agriext.willn.agriext.Boundary.Activites;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.R;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnSpeakStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        btnSpeakStart = findViewById(R.id.btnSpeakerStart);

        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        btnStart.startAnimation(pulse);

        final ControlSpeaker controlSpeaker = new ControlSpeaker(this);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CultureChoiceActivity.class));
                controlSpeaker.speak("Iniciando");
            }
        });

        btnSpeakStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlSpeaker.speak("Iniciar");
            }
        });

    }
}
