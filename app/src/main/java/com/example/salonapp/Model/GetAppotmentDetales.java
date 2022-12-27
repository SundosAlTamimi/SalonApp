package com.example.salonapp.Model;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class GetAppotmentDetales {

    //[{"CODE":"6","SERVICE_NO":"3","SERVICE_DURATION":"00:20","ROW_NUMBER":"1","INS_USER_NO":"1","INS_TIME":"16:03",
    // "INS_DATE":"30\/05\/2020","JOB_NO":"","APP_DATE":"","NOTE":""},
    // {"CODE":"6","SERVICE_NO":"4","SERVICE_DURATION":"00:10","ROW_NUMBER":"2","INS_USER_NO":"1","INS_TIME":"16:03","INS_DATE":"30\/05\/2020","JOB_NO":"","APP_DATE":"","NOTE":""}]


//    @PrimaryKey
//    @NonNull
//    @SerializedName("CODE")
//    @ColumnInfo(name = "CODE")
//    private String CODE;
    @SerializedName("CODE")
//    @ColumnInfo(name = "CODE")
    private String SERVICE_NO;

//    @ColumnInfo(name = "NAME")
//    private String NAME;
    @SerializedName("SERVICE_DURATION")
//    @ColumnInfo(name = "CODE")
    private String SERVICE_DURATION;
//    @SerializedName("ROW_NUMBER")
//    @ColumnInfo(name = "CODE")
    private String ROW_NUMBER;
//    @SerializedName("INS_USER_NO")
//    @ColumnInfo(name = "CODE")
    private String INS_USER_NO;
//    @SerializedName("INS_TIME")
//    @ColumnInfo(name = "CODE")
    private String INS_TIME;
//    @SerializedName("INS_DATE")
//    @ColumnInfo(name = "CODE")
    private String INS_DATE;
//    @SerializedName("JOB_NO")
//    @ColumnInfo(name = "CODE")
    private String JOB_NO;
//    @SerializedName("APP_DATE")
//    @ColumnInfo(name = "CODE")
    private String APP_DATE;
//    @SerializedName("NOTE")
//    @ColumnInfo(name = "CODE")
    private String NOTE;
//    @ColumnInfo(name = "CODE")
    @SerializedName("NAME")
    private String serviceName;

    private String isNew;

    public GetAppotmentDetales() {
    }

    public GetAppotmentDetales( String SERVICE_NO, String SERVICE_DURATION, String ROW_NUMBER,
                               String INS_USER_NO, String INS_TIME, String INS_DATE, String JOB_NO, String APP_DATE, String NOTE) {
       // this.CODE = CODE;
        this.SERVICE_NO = SERVICE_NO;
        this.SERVICE_DURATION = SERVICE_DURATION;
        this.ROW_NUMBER = ROW_NUMBER;
        this.INS_USER_NO = INS_USER_NO;
        this.INS_TIME = INS_TIME;
        this.INS_DATE = INS_DATE;
        this.JOB_NO = JOB_NO;
        this.APP_DATE = APP_DATE;
        this.NOTE = NOTE;
    }

//    @NonNull
//    public String getCODE() {
//        return CODE;
//    }
//
//    public void setCODE(@NonNull String CODE) {
//        this.CODE = CODE;
//    }

    public String getSERVICE_NO() {
        return SERVICE_NO;
    }

    public void setSERVICE_NO(String SERVICE_NO) {
        this.SERVICE_NO = SERVICE_NO;
    }

    public String getSERVICE_DURATION() {
        return SERVICE_DURATION;
    }

    public void setSERVICE_DURATION(String SERVICE_DURATION) {
        this.SERVICE_DURATION = SERVICE_DURATION;
    }

    public String getROW_NUMBER() {
        return ROW_NUMBER;
    }

    public void setROW_NUMBER(String ROW_NUMBER) {
        this.ROW_NUMBER = ROW_NUMBER;
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

    public String getJOB_NO() {
        return JOB_NO;
    }

    public void setJOB_NO(String JOB_NO) {
        this.JOB_NO = JOB_NO;
    }

    public String getAPP_DATE() {
        return APP_DATE;
    }

    public void setAPP_DATE(String APP_DATE) {
        this.APP_DATE = APP_DATE;
    }

    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {

        this.isNew = isNew;

    }


}

