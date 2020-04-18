package com.example.azcar.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.azcar.R;
import com.example.azcar.adapters.QuraanAdapter;
import com.example.azcar.model.Quraan;
import com.example.azcar.model.Reciters;
import com.example.azcar.utils.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuraanActivity extends AppCompatActivity {

    @BindView(R.id.sora)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    QuraanAdapter quraanAdapter;
//
//    Map<String , String> map ;
//    String sura ;
//    String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quraan);
        ButterKnife.bind(this);

        String reciters = getIntent().getExtras().getString("player");
        Reciters reciter = getIntent().getExtras().getParcelable("reciter");

        getSupportActionBar().setTitle(reciters);

//        map = new HashMap<>( );
//        map = getIntent().getExtras().getParcelable("map");

        //         sura = getIntent().getExtras().getString("suras");
//         id = getIntent().getExtras().getString("id");

//        Log.d("MUUU", "onCreate: " + sura );

//        setData();
        //Log.d("Test", reciter.getSuras());
        assert reciter != null;
        int [] suars = Helper.getSurasId(reciter.getSuras());
        getData(suars);

    }


    private void getData(int[] suars) {


        ArrayList<Quraan> arrayList = new ArrayList<>();
        Quraan quraan = new Quraan();
        try {
            JSONArray jsonArray = new JSONArray(loadJSONFile(this));
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                quraan = new Quraan();
                quraan.setId(jsonObject.getInt("id"));
                quraan.soraName = jsonObject.getString("sn");
                quraan.soraNum = jsonObject.getString("countaya");

                for (int suraId : suars) {
                    if (quraan.id == suraId) {
                        arrayList.add(quraan);
                        break;
                    }
                }

//                Log.e("HAHA", "getData: " + quraan.getId());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        quraanAdapter = new QuraanAdapter(this, arrayList);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(quraanAdapter);
    }


    public String loadJSONFile(Context context) {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("suranames.json");
            int size = inputStream.available();

            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }


    private void setData() {
        ArrayList<Quraan> arrayList = new ArrayList<>();
        Quraan quraan = new Quraan();
        quraan.setSoraName(R.string.elFateha + "");
        quraan.setSoraNum(R.string.aya7 + "");
        arrayList.add(quraan);

        quraan = new Quraan();
        quraan.setSoraName(R.string.elBaqra + "");
        quraan.setSoraNum(R.string.aya286 + "");
        arrayList.add(quraan);

        quraan = new Quraan();
        quraan.setSoraName(R.string.elnesaa + "");
        quraan.setSoraNum(R.string.aya176 + "");
        arrayList.add(quraan);


        quraan = new Quraan();
        quraan.setSoraName(R.string.alaaraf + "");
        quraan.setSoraNum(R.string.aya165 + "");
        arrayList.add(quraan);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new QuraanAdapter(this, arrayList));

    }

}


