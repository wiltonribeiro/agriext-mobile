package com.agriext.willn.agriext.Control;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.agriext.willn.agriext.R;

public class ControlLoading {
    private Activity activity;
    private ControlSpeaker controlSpeaker;

    public ControlLoading(Activity activity, ControlSpeaker controlSpeaker) {
        this.activity = activity;
        this.controlSpeaker = controlSpeaker;
    }

    private void implementRules(){
        RelativeLayout loading = activity.findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);

        Button btnSpeakLoading = activity.findViewById(R.id.btnSpeakLoading);
        btnSpeakLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlSpeaker.speak("AGUARDE UM POUCO");
            }
        });
    }

    public void initLoading(){
        implementRules();
    }

    public void finishLoading(){
        activity.findViewById(R.id.loading).setVisibility(View.GONE);
    }
}
