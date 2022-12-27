package com.example.salonapp.Model;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

//@Entity(tableName = "ServiceTable")
public class GetServiceModel {
    @SerializedName("CODE")
    private String CODE;
    @SerializedName("NAME")
    private String NAME;
    @SerializedName("GROUP_NO")
    private String GROUP_NO;
    @SerializedName("PRICE")
    private String PRICE;
    @SerializedName("POINT")
    private String POINT;
    @SerializedName("DURATION_TIME")
    private String DURATION_TIME;
    @SerializedName("INS_USER_NO")
    private String INS_USER_NO;
    @SerializedName("INS_TIME")
    private String INS_TIME;
    @SerializedName("INS_DATE")
    private String INS_DATE;
    @SerializedName("UPD_USER_NO")
    private String UPD_USER_NO;
    @SerializedName("UPD_TIME")
    private String UPD_TIME;
    @SerializedName("UPD_DATE")
    private String UPD_DATE;
    @SerializedName("ACC_CODE")
    private String ACC_CODE;
    @SerializedName("MULTI_GROUP")
    private String MULTI_GROUP;
    @SerializedName("ACTIVE")

    private String ACTIVE;
    @SerializedName("SECTION")
    private String SECTION;
    @SerializedName("COMMISSION_RATE")
    private String COMMISSION_RATE;
    @SerializedName("GET_COMMISSION")
    private String GET_COMMISSION;
    @SerializedName("PRICEVIP")
    private String PRICEVIP;

    public GetServiceModel() {
    }

    public GetServiceModel(String CODE, String NAME, String GROUP_NO, String PRICE, String POINT, String DURATION_TIME, String INS_USER_NO, String INS_TIME, String INS_DATE, String UPD_USER_NO, String UPD_TIME, String UPD_DATE, String ACC_CODE, String MULTI_GROUP,
                           String ACTIVE, String SECTION, String COMMISSION_RATE, String GET_COMMISSION,
                           String PRICEVIP) {
        this.CODE = CODE;
        this.NAME = NAME;
        this.GROUP_NO = GROUP_NO;
        this.PRICE = PRICE;
        this.POINT = POINT;
        this.DURATION_TIME = DURATION_TIME;//الوقت
        this.INS_USER_NO = INS_USER_NO;
        this.INS_TIME = INS_TIME;
        this.INS_DATE = INS_DATE;
        this.UPD_USER_NO = UPD_USER_NO;
        this.UPD_TIME = UPD_TIME;
        this.UPD_DATE = UPD_DATE;
        this.ACC_CODE = ACC_CODE;
        this.MULTI_GROUP = MULTI_GROUP;
        this.ACTIVE = ACTIVE;
        this.SECTION = SECTION;
        this.COMMISSION_RATE = COMMISSION_RATE;
        this.GET_COMMISSION = GET_COMMISSION;
        this.PRICEVIP = PRICEVIP;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getGROUP_NO() {
        return GROUP_NO;
    }

    public void setGROUP_NO(String GROUP_NO) {
        this.GROUP_NO = GROUP_NO;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getPOINT() {
        return POINT;
    }

    public void setPOINT(String POINT) {
        this.POINT = POINT;
    }

    public String getDURATION_TIME() {
        return DURATION_TIME;
    }

    public void setDURATION_TIME(String DURATION_TIME) {
        this.DURATION_TIME = DURATION_TIME;
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

    public String getACC_CODE() {
        return ACC_CODE;
    }

    public void setACC_CODE(String ACC_CODE) {
        this.ACC_CODE = ACC_CODE;
    }

    public String getMULTI_GROUP() {
        return MULTI_GROUP;
    }

    public void setMULTI_GROUP(String MULTI_GROUP) {
        this.MULTI_GROUP = MULTI_GROUP;
    }

    public String getACTIVE() {
        return ACTIVE;
    }

    public void setACTIVE(String ACTIVE) {
        this.ACTIVE = ACTIVE;
    }

    public String getSECTION() {
        return SECTION;
    }

    public void setSECTION(String SECTION) {
        this.SECTION = SECTION;
    }

    public String getCOMMISSION_RATE() {
        return COMMISSION_RATE;
    }

    public void setCOMMISSION_RATE(String COMMISSION_RATE) {
        this.COMMISSION_RATE = COMMISSION_RATE;
    }

    public String getGET_COMMISSION() {
        return GET_COMMISSION;
    }

    public void setGET_COMMISSION(String GET_COMMISSION) {
        this.GET_COMMISSION = GET_COMMISSION;
    }

    public String getPRICEVIP() {
        return PRICEVIP;
    }

    public void setPRICEVIP(String PRICEVIP) {
        this.PRICEVIP = PRICEVIP;
    }
}
