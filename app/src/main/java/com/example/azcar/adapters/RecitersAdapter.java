package com.example.azcar.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.azcar.R;
import com.example.azcar.activities.QuraanActivity;
import com.example.azcar.model.Reciters;

import org.json.JSONArray;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecitersAdapter extends RecyclerView.Adapter<RecitersAdapter.MyViewHolder> {

    private Activity activity;
    private ArrayList<Reciters> arrayList;

    public RecitersAdapter(Activity activity, ArrayList<Reciters> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(activity).inflate(R.layout.reciters_list, viewGroup, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {



        final Reciters reciters = arrayList.get(i);
        myViewHolder.recitersName.setText(reciters.getReciters());
        final String player = myViewHolder.recitersName.getText().toString();
        myViewHolder.soraNum.setText(reciters.getSoraCount() + " سورة");
        myViewHolder.rewaya.setText("رواية " + reciters.getRewaya());


        myViewHolder.mainLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, QuraanActivity.class);
                i.putExtra("player", player);
                activity.startActivity(i);
            }
        });

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.RecitersName)
        TextView recitersName;
        @BindView(R.id.sora_num)
        TextView soraNum;
        @BindView(R.id.rewaya)
        TextView rewaya;
        @BindView(R.id.mainLine)
        RelativeLayout mainLine;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}

