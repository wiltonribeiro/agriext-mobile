package com.agriext.willn.agriext.Boundary.Activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.agriext.willn.agriext.Control.ControlSaveDataCache;
import com.agriext.willn.agriext.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Activity activity = this;
        final ControlSaveDataCache controlSaveDataCache = ControlSaveDataCache.getInstance(this);

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                if(controlSaveDataCache.firstTime()){
                    startActivity(new Intent(activity, ExplainActivity.class));
                    controlSaveDataCache.setFirstTime();
                }
                else
                    startActivity(new Intent(activity, MainActivity.class));
            }
        }, 1200);
    }
}
