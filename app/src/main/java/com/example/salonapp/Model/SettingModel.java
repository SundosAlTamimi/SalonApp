package com.example.salonapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SETTING_TABLE")
public class SettingModel {
   @PrimaryKey
    @NonNull
   @ColumnInfo(name = "IP")
    private String ip;
    @ColumnInfo(name = "CONO")
    private String cono;

    public SettingModel() {
    }

    public SettingModel(@NonNull String ip, String cono) {
        this.ip = ip;
        this.cono = cono;
    }


    @NonNull
    public String getIp() {
        return ip;
    }

    public void setIp(@NonNull String ip) {
        this.ip = ip;
    }

    public String getCono() {
        return cono;
    }

    public void setCono(String cono) {
        this.cono = cono;
    }
}
