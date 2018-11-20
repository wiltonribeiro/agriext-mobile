package com.agriext.willn.agriext.Boundary.Activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.Entity.Callback;
import com.agriext.willn.agriext.R;

public class ExplainActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        Button btnSpeakerExplain = findViewById(R.id.btnSpeakerExplain);

        final TextView textExplain = findViewById(R.id.textExplain);
        final ControlSpeaker controlSpeaker = new ControlSpeaker(this);


        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                controlSpeaker.speak(textExplain.getText().toString(), new Callback<Boolean>() {
                    @Override
                    public void onCallback(Boolean b) {
                        if(b) startActivity(new Intent(ExplainActivity.this, MainActivity.class));
                    }
                });
            }
        },500);


        btnSpeakerExplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlSpeaker.speak(textExplain.getText().toString());
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
