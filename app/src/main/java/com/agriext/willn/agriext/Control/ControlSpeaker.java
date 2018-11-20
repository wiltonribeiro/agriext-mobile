package com.agriext.willn.agriext.Control;

import android.app.Activity;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.widget.Toast;

import com.agriext.willn.agriext.Entity.Callback;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ControlSpeaker {
    private TextToSpeech textToSpeech;
    private Context context;

    public ControlSpeaker(Context context) {
        this.context = context;
        textToSpeech = init();
    }

    private TextToSpeech init(){

        return new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(new Locale("pt","br"));
                    textToSpeech.setSpeechRate(0.8f);
                }
            }
        }, "com.google.android.tts");
    }

    public void speak(String text){
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void speak(String text, final Callback<Boolean> booleanCallback){
        textToSpeech.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener() {

            @Override
            public void onUtteranceCompleted(final String utteranceId) {
                ((Activity) context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        booleanCallback.onCallback(true);
                    }
                });
            }
        });

        HashMap<String,String> ttsParams = new HashMap<>();
        ttsParams.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, context.getPackageName());

        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, ttsParams);
    }
}
