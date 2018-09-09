package com.agriext.willn.agriext.Boundary.Activites;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agriext.willn.agriext.Control.ControlLoading;
import com.agriext.willn.agriext.Control.ControlResult;
import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.Entity.Result;
import com.agriext.willn.agriext.R;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    ControlSpeaker controlSpeaker;
    TextView textQuantityWater;
    Result result = ControlResult.result;
    Button btnSpeakResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        controlSpeaker = new ControlSpeaker(this);
        textQuantityWater = findViewById(R.id.textQuantityWater);
        btnSpeakResult = findViewById(R.id.btnSpeakResult);
        int waterResult = (int) result.getQuantityWater();


        final String textSpeakResult = "Calculamos que para hoje, seja ideal que você faça a irrigação com "+waterResult+" litros de água por hectare na sua" +
                "plantação de "+result.getCulture().getName();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                controlSpeaker.speak(textSpeakResult);
            }
        },1000);

        btnSpeakResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlSpeaker.speak(textSpeakResult);
            }
        });

        textQuantityWater.setText((Integer.toString(waterResult)));

    }
}
