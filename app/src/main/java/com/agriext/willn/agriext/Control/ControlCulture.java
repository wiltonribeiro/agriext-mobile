package com.agriext.willn.agriext.Control;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.GridView;
import android.widget.ListView;

import com.agriext.willn.agriext.Boundary.Adapters.AdapterCulture;
import com.agriext.willn.agriext.Entity.Culture;
import com.agriext.willn.agriext.R;

import java.util.HashMap;
import java.util.Set;

public class ControlCulture {

    private Context context;
    private static ControlSaveDataCache controlSaveDataCache;
    private static HashMap<String,Culture> listCultures;
    public static HashMap<String,Culture> listSelectedCultures = new HashMap<>();

    public ControlCulture(Context context) {
        this.context = context;
        listCultures = fillCultureData(context);
    }

    public static void initCultureData(Context context){
        controlSaveDataCache = ControlSaveDataCache.getInstance(context);
        fillSelectedCultures();
    }

    public static void addSelectedCulture(Culture culture){
        listSelectedCultures.put(culture.getName(),culture);
    }

    public static void removeSelectedCulture(Culture culture){
        listSelectedCultures.remove(culture.getName());
    }

    public static boolean checkIfCultureIsSelected(Culture culture){
        return listSelectedCultures.get(culture.getName()) != null;
    }

    private static void fillSelectedCultures(){
        Set<String> mKeys = controlSaveDataCache.getAllSelectedCulture();
        if(mKeys != null)
            for(String key : mKeys) listSelectedCultures.put(key,listCultures.get(key));
    }

    public static void saveSelectedCultures(){
        controlSaveDataCache.addSelectedCulture(listSelectedCultures);
    }

    public void listCultures(ListView listView){
        listView.setAdapter(null);
        listView.requestLayout();
        listView.setAdapter(new AdapterCulture(context,listCultures, new ControlSpeaker(context)));
    }



    private static HashMap<String,Culture> fillCultureData(Context context){
        HashMap<String,Culture> cultures = new HashMap<>();
        cultures.put("Arroz", new Culture("Arroz", ContextCompat.getDrawable(context, R.drawable.arroz),1.05));
        cultures.put("Feijão Verde", new Culture("Feijão Verde", ContextCompat.getDrawable(context, R.drawable.feijao_verde), 0.85));
        cultures.put("Feijão Seco", new Culture("Feijão Seco", ContextCompat.getDrawable(context, R.drawable.feijao_seco), 0.7));
        cultures.put("Soja", new Culture("Soja", ContextCompat.getDrawable(context, R.drawable.soja), 0.75));
        cultures.put("Algodão", new Culture("Algodão", ContextCompat.getDrawable(context, R.drawable.algodao), 0.85));
        cultures.put("Tomate", new Culture("Tomate", ContextCompat.getDrawable(context, R.drawable.tomate), 0.75));
        return  cultures;
    }
}
