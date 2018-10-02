package com.agriext.willn.agriext.Control;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;

import com.agriext.willn.agriext.Boundary.Activites.ResultActivity;

import static android.content.Context.WIFI_SERVICE;

public class ControlStation {

    private Context context;
    private String stationName = "SUPOSTAESTAÇÃO";

    public ControlStation(Context context){
        this.context = context;
    }

    public boolean checkStation(){
        return getWifiContent().equals(stationName);
    }

    private String getWifiContent(){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        String isAvailable = "";
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = networkInfo.getExtraInfo();
        }

        return isAvailable.replace("\"", "");
    }

    public void connectToWifi(){
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", stationName);
        wifiConfig.preSharedKey = String.format("\"%s\"", "30303030");

        WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        int netId = wifiManager.addNetwork(wifiConfig);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();
    }

}
