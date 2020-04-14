package com.example.azcar.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.azcar.R;
import com.example.azcar.adapters.RecitersAdapter;
import com.example.azcar.links.Constants;
import com.example.azcar.model.Reciters;
import com.example.azcar.parser.RecitersParser;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecitersActivity extends AppCompatActivity {

    @BindView(R.id.Reciters)
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ProgressDialog customProgressDialog;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.noConnectionLine)
    RelativeLayout noConnectionLine;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reciters_main);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(R.string.AlQuraan);

        customProgressDialog = new ProgressDialog(this);
        swipeRefresh.setColorSchemeColors(R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
//        setData();

    }


    private void getData() {

        TextView load = new TextView(this);
        load.setText(R.string.loading);
        String loading = load.getText().toString();
        customProgressDialog.setMessage(loading);
        customProgressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.Reciters_ar, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<Reciters> arrayList = RecitersParser.getData(response);
                    RecitersAdapter recitersAdapter = new RecitersAdapter(RecitersActivity.this, arrayList);

                    linearLayoutManager = new LinearLayoutManager(RecitersActivity.this,
                            LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(recitersAdapter);
                    customProgressDialog.cancel();
                    swipeRefresh.setRefreshing(false);
                    recyclerView.setVisibility(View.VISIBLE);
                    noConnectionLine.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                customProgressDialog.show();
                Log.d("remy", error.toString());
                Toast.makeText(RecitersActivity.this,
                        "No Connection", Toast.LENGTH_SHORT).show();
                customProgressDialog.cancel();
                swipeRefresh.setRefreshing(false);
                recyclerView.setVisibility(View.GONE);
                noConnectionLine.setVisibility(View.VISIBLE);


            }
        });
        queue.add(stringRequest);
    }


    private void setData() {
        ArrayList<Reciters> arrayList = new ArrayList<>();

        Reciters reciters = new Reciters();
        reciters.setReciters(R.string.aboBakr + "");
        reciters.setSoraCount(String.valueOf(R.string.sora114Number));
        arrayList.add(reciters);


        reciters = new Reciters();
        reciters.setReciters(R.string.AhmedElhoawshe + "");
        reciters.setSoraCount(String.valueOf(R.string.sora114Number));
        arrayList.add(reciters);

        reciters = new Reciters();
        reciters.setReciters(R.string.AhmedElhoawshe + "");
        reciters.setSoraCount(String.valueOf(R.string.sora114Number));
        arrayList.add(reciters);

        reciters = new Reciters();
        reciters.setReciters(R.string.ahmedSaber + "");
        reciters.setSoraCount(String.valueOf(R.string.sora114Number));
        arrayList.add(reciters);

        reciters = new Reciters();
        reciters.setReciters(R.string.aboBakr + "");
        reciters.setSoraCount(String.valueOf(R.string.sora114Number));
        arrayList.add(reciters);

        reciters = new Reciters();
        reciters.setReciters(R.string.ahmedElhozefe + "");
        reciters.setSoraCount(String.valueOf(R.string.sora114Number));
        arrayList.add(reciters);

        reciters = new Reciters();
        reciters.setReciters(String.valueOf(R.string.aboBakr));
        reciters.setSoraCount(String.valueOf(R.string.sora114Number));
        arrayList.add(reciters);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new RecitersAdapter(this, arrayList));
    }

}
