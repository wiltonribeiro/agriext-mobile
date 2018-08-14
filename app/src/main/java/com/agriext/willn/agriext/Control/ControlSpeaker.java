package com.agriext.willn.agriext.Control;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class ControlSpeaker {
    private TextToSpeech textToSpeech;
    private Context context;

    public ControlSpeaker(Context context) {
        this.context = context;
        textToSpeech = init();
    }

    private TextToSpeech init(){
        return  new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(new Locale("pt","br"));
                    textToSpeech.setPitch(0.1f);
                }
            }
        });
    }

    public void speak(String text){
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
