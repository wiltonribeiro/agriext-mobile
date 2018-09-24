package com.agriext.willn.agriext.Boundary.Activites;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.agriext.willn.agriext.Boundary.Adapters.AdapterResult;
import com.agriext.willn.agriext.Control.ControlCulture;
import com.agriext.willn.agriext.Control.ControlLoading;
import com.agriext.willn.agriext.Control.ControlResult;
import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.Entity.Culture;
import com.agriext.willn.agriext.Entity.Result;
import com.agriext.willn.agriext.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private ControlSpeaker controlSpeaker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ArrayList<Result> results = new ArrayList<>();
        final String textResultExplain = "Veja todos os resultados calculados para sua plantação";
        controlSpeaker = new ControlSpeaker(this);
        ControlResult controlResult = new ControlResult(this);

        Button btnSpeakResult = findViewById(R.id.btnSpeakResult);
        ListView listResult = findViewById(R.id.listResult);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                controlSpeaker.speak(textResultExplain);
            }
        },1000);

        btnSpeakResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlSpeaker.speak(textResultExplain);
            }
        });

        for(Culture culture : ControlCulture.listSelectedCultures.values()){
            results.add(controlResult.calculate(culture));
        }

        listResult.setAdapter(new AdapterResult(this, results, controlSpeaker));

    }
}
