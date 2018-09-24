package com.agriext.willn.agriext.Control;

import android.content.Context;
import android.content.SharedPreferences;

import com.agriext.willn.agriext.Entity.Culture;

import java.util.HashMap;
import java.util.Set;


public class ControlSaveDataCache {
    private static final String SHARED_NAME = "sharedDataFile";
    private static SharedPreferences settings;
    private static ControlSaveDataCache controlSaveDataCache;

    private ControlSaveDataCache(Context context) {
        settings = context.getSharedPreferences(SHARED_NAME, 0);
    }

    public static ControlSaveDataCache getInstance(Context context) {
        if(controlSaveDataCache == null){
            controlSaveDataCache = new ControlSaveDataCache(context);
        }
        return controlSaveDataCache;
    }

    public void addSelectedCulture(HashMap<String,Culture> hashMap){
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet("cultures",hashMap.keySet());
        editor.apply();
    }

    public Set<String> getAllSelectedCulture(){
        return settings.getStringSet("cultures", null);
    }
}
