package com.agriext.willn.agriext.Boundary.Activites;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.agriext.willn.agriext.Control.ControlCulture;
import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.R;

public class CultureChoiceActivity extends AppCompatActivity {

    GridView listCulture;
    ControlCulture controlCulture;
    ControlSpeaker controlSpeaker;
    Button btnSpeakerCultureLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_choice);
        listCulture = findViewById(R.id.listCulture);
        controlCulture = new ControlCulture(this);
        controlSpeaker = new ControlSpeaker(this);
        btnSpeakerCultureLayout = findViewById(R.id.btnSpeakerCultureLayout);

        controlCulture.listCultures(listCulture);
        btnSpeakerCultureLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlSpeaker.speak("TIPOS DE PLANTAÇÃO");
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                controlSpeaker.speak("Selecione o tipo de plantação que você possui");
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() {
        ControlCulture.saveSelectedCultures();
        super.onBackPressed();
    }
}
