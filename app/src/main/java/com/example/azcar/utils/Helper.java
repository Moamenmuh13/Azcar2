package com.example.azcar.utils;

import android.content.Intent;
import android.util.Log;

public class Helper {

    public static int [] getSurasId(String suras) {
        String [] stringIds = suras.split(",");
        int [] ids = new int[stringIds.length];

        for (int i = 0 ; i < stringIds.length ; i++) {
            ids[i] = Integer.parseInt(stringIds[i]);
        }

        return ids;
    }

}
