package com.example.azcar.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    public static String getConnectivityStatusString(Context context) {
        String status = null;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (networkInfo != null) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                status = "Wifi enable";
                return status;
            } else if (networkInfo.getType() == connectivityManager.TYPE_MOBILE) {
                status = "Mobile data enable";
                return status;
            }
        } else {
            status = "No Internet is available";
            return status;
        }

        return status;
    }
}
