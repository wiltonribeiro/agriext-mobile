package com.agriext.willn.agriext.Control;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ListView;

import com.agriext.willn.agriext.Boundary.Activites.MainActivity;
import com.agriext.willn.agriext.Boundary.Adapters.AdapterStation;
import com.agriext.willn.agriext.Entity.Station;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ControlStation {

    private Context context;

    public ControlStation(Context context){
        this.context = context;
    }

    public void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            ActivityCompat.finishAffinity((Activity) context);
            context.startActivity(new Intent(context, MainActivity.class));
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public boolean readFromFile() {

        InputStream inputStream = null;
        try {
            inputStream = context.openFileInput("config.txt");
            if ( inputStream != null ) {
                return true;
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public void loadStationList(ListView listView){
        listView.setAdapter(null);
        listView.requestLayout();
        listView.setAdapter(new AdapterStation(context,fillStationData(), new ControlSpeaker(context), this));
    }

    private List<Station> fillStationData() {
        List<Station> list = new ArrayList<>();
        list.add(new Station("Exemplo de estação 1", "Exemplo de descrição 1"));
        list.add(new Station("Exemplo de estação 2", "Exemplo de descrição 2"));
        list.add(new Station("Exemplo de estação 3", "Exemplo de descrição 3"));

        return list;
    }
}
