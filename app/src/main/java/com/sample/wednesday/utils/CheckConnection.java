package com.sample.wednesday.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import static android.content.Context.CONNECTIVITY_SERVICE;


public class CheckConnection extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {

            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(context, "Connected with Mobile Data", Toast.LENGTH_SHORT).show();
            }

            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(context, "Connected with Wifi", Toast.LENGTH_SHORT).show();
            }

        } else {
            Task.showWarning(context, "NO Internet");
        }
    }

}