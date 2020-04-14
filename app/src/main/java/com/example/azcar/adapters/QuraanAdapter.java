package com.example.azcar.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azcar.R;

import com.example.azcar.activities.MediaPlayerActivity;
import com.example.azcar.model.Quraan;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuraanAdapter extends RecyclerView.Adapter<QuraanAdapter.ViewHolder> {
    Activity activity;
    ArrayList<Quraan> arrayList;

    public QuraanAdapter(Activity activity, ArrayList<Quraan> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.quraan_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        Quraan quraan = arrayList.get(i);
        viewHolder.soraName.setText(quraan.getSoraName());
        viewHolder.soraNum.setText(quraan.getSoraNum() + " آية");

        final String sora = viewHolder.soraName.getText().toString();
        viewHolder.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, MediaPlayerActivity.class);
                i.putExtra("soraName", sora);
                activity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sora_name)
        TextView soraName;
        @BindView(R.id.sora_num)
        TextView soraNum;
        @BindView(R.id.line)
        LinearLayout line;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
