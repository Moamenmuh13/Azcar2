package com.example.azcar.parser;

import android.os.Parcelable;
import android.util.Log;

import com.example.azcar.model.Reciters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

public class RecitersParser {


    public static ArrayList<Reciters> getData(String Json) throws JSONException {
        ArrayList<Reciters> arrayList = new ArrayList<>();
        Reciters reciters =  new Reciters();
        try {
            JSONObject jsonObject = new JSONObject(Json);
            JSONArray jsonArray = jsonObject.getJSONArray("reciters");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                reciters = new Reciters();
                reciters.setId(jsonObject2.getString("id"));
                reciters.setReciters(jsonObject2.getString("name"));
                reciters.setRewaya(jsonObject2.getString("rewaya"));
                reciters.setSoraCount(jsonObject2.getString("count"));
                reciters.setServer(jsonObject2.getString("Server"));
                reciters.setSuras(jsonObject2.getString("suras"));
                arrayList.add(reciters);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

}

