package com.example.salonapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "USER_GROUP_TABLE")
public class UserGroupModel {
//"CODE":"1","NAME":"Administrator","INS_USER_NO":"1",
// "INS_TIME":"2:49:13 PM","INS_DATE":"11\/07\/2019","UPD_USER_NO":"","UPD_TIME":"","UPD_DATE":""},
@PrimaryKey
    @NonNull
    @SerializedName("CODE")
    @ColumnInfo(name = "CODE")
    private String CODE;
    @SerializedName("NAME")
    @ColumnInfo(name = "NAME")
    private String NAME;
    @SerializedName("INS_USER_NO")
    @ColumnInfo(name = "INS_USER_NO")
    private String INS_USER_NO;
    @SerializedName("INS_TIME")
    @ColumnInfo(name = "INS_TIME")
    private String INS_TIME;
    @SerializedName("INS_DATE")
    @ColumnInfo(name = "INS_DATE")
    private String INS_DATE;
    @SerializedName("UPD_USER_NO")
    @ColumnInfo(name = "UPD_USER_NO")
    private String UPD_USER_NO;
    @SerializedName("UPD_TIME")
    @ColumnInfo(name = "UPD_TIME")
    private String UPD_TIME;
    @SerializedName("UPD_DATE")
    @ColumnInfo(name = "UPD_DATE")
    private String UPD_DATE;

    public UserGroupModel() {
    }

    public UserGroupModel(@NonNull String CODE, String NAME, String INS_USER_NO,
                          String INS_TIME, String INS_DATE, String UPD_USER_NO, String UPD_TIME, String UPD_DATE) {
        this.CODE = CODE;
        this.NAME = NAME;
        this.INS_USER_NO = INS_USER_NO;
        this.INS_TIME = INS_TIME;
        this.INS_DATE = INS_DATE;
        this.UPD_USER_NO = UPD_USER_NO;
        this.UPD_TIME = UPD_TIME;
        this.UPD_DATE = UPD_DATE;
    }


    @NonNull
    public String getCODE() {
        return CODE;
    }

    public void setCODE(@NonNull String CODE) {
        this.CODE = CODE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getINS_USER_NO() {
        return INS_USER_NO;
    }

    public void setINS_USER_NO(String INS_USER_NO) {
        this.INS_USER_NO = INS_USER_NO;
    }

    public String getINS_TIME() {
        return INS_TIME;
    }

    public void setINS_TIME(String INS_TIME) {
        this.INS_TIME = INS_TIME;
    }

    public String getINS_DATE() {
        return INS_DATE;
    }

    public void setINS_DATE(String INS_DATE) {
        this.INS_DATE = INS_DATE;
    }

    public String getUPD_USER_NO() {
        return UPD_USER_NO;
    }

    public void setUPD_USER_NO(String UPD_USER_NO) {
        this.UPD_USER_NO = UPD_USER_NO;
    }

    public String getUPD_TIME() {
        return UPD_TIME;
    }

    public void setUPD_TIME(String UPD_TIME) {
        this.UPD_TIME = UPD_TIME;
    }

    public String getUPD_DATE() {
        return UPD_DATE;
    }

    public void setUPD_DATE(String UPD_DATE) {
        this.UPD_DATE = UPD_DATE;
    }
}
