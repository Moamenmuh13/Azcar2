package com.example.azcar.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Reciters implements Parcelable {
    private String id ;
    private String reciters ;
    private String soraCount ;
    private String rewaya ;
    private String server ;
    private String suras ;


    public static final Creator<Reciters> CREATOR = new Creator<Reciters>() {
        @Override
        public Reciters createFromParcel(Parcel in) {
            return new Reciters(in);
        }

        @Override
        public Reciters[] newArray(int size) {
            return new Reciters[size];
        }
    };

    public Reciters() {

    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    public Reciters(Parcel in) {
        id = in.readString();
        reciters = in.readString();
        soraCount = in.readString();
        rewaya = in.readString();
        server = in.readString();
        suras = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(reciters);
        dest.writeString(soraCount);
        dest.writeString(rewaya);
        dest.writeString(server);
        dest.writeString(suras);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReciters() {
        return reciters;
    }

    public void setReciters(String reciters) {
        this.reciters = reciters;
    }

    public String getSoraCount() {
        return soraCount;
    }

    public void setSoraCount(String soraCount) {
        this.soraCount = soraCount;
    }

    public String getRewaya() {
        return rewaya;
    }

    public void setRewaya(String rewaya) {
        this.rewaya = rewaya;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getSuras() {
        return suras;
    }

    public void setSuras(String suras) {
        this.suras = suras;
    }
}

