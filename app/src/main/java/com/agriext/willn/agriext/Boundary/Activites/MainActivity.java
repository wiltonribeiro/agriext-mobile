package com.agriext.willn.agriext.Boundary.Activites;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.agriext.willn.agriext.Control.ControlResult;
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
        final ControlResult controlResult = new ControlResult(this);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String x = controlResult.quixadaHC(5);
                    Log.i("willneto",x);
                } catch (Exception e) {
                    Log.e("willneto",e.getMessage());
                    e.printStackTrace();
                }
//                startActivity(new Intent(MainActivity.this, CultureChoiceActivity.class));
//                controlSpeaker.speak("Iniciando");
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
