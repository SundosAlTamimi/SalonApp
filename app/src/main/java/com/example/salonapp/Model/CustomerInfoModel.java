package com.example.salonapp.Model;

import com.google.gson.annotations.SerializedName;

public class CustomerInfoModel {

    //[{"CODE":"1","BIRTH_DATE_AD":"","AGE":"","GENDER":"","NATIONALITY":"","PHONE":"","ADDRESS":"",
    // "SOURCE":"","INS_USER_NO":"","INS_TIME":"","INS_DATE":"","UPD_USER_NO":"",
    // "UPD_TIME":"","UPD_DATE":"","NOTE_1":"","NOTE_2":"","ACCCODE":"","POINTVIP":"","ALL_CUST":"","BALANCE":"16.6"}]

//[{"CODE":"1","BIRTH_DATE_AD":"","AGE":"","GENDER":"","NATIONALITY":"","PHONE":"","ADDRESS":"",
// "SOURCE":"","INS_USER_NO":"","INS_TIME":"","INS_DATE":"","UPD_USER_NO":"","UPD_TIME":"",
// "UPD_DATE":"","NOTE_1":"","NOTE_2":"","ACCCODE":"","POINTVIP":"","ALL_CUST":"","BALANCE":"16.6",
// "CODE_1":"1","FIRST_NAME":"Abeer","SECOND_NAME":"Saloon","THIERD_NAME":"\/","LAST_NAME":"Kuwait","FULL_NAME":"Abeer Saloon \/ Kuwait","INS_USER_NO_1":""
// ,"INS_TIME_1":"","INS_DATE_1":"","UPD_USER_NO_1":"","UPD_TIME_1":"","UPD_DATE_1":""}]


    @SerializedName("CODE")
    private String CODE;
    @SerializedName("BIRTH_DATE_AD")
    private String BIRTH_DATE_AD;
    @SerializedName("AGE")
    private String AGE;
    @SerializedName("GENDER")
    private String GENDER;
    @SerializedName("NATIONALITY")
    private String NATIONALITY;
    @SerializedName("PHONE")
    private String PHONE;
    @SerializedName("ADDRESS")
    private String ADDRESS;
    @SerializedName("SOURCE")
    private String SOURCE;
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
    @SerializedName("NOTE_1")
    private String NOTE_1;
    @SerializedName("NOTE_2")
    private String NOTE_2;
    @SerializedName("ACCCODE")
    private String ACCCODE;
    @SerializedName("POINTVIP")
    private String POINTVIP;
    @SerializedName("ALL_CUST")
    private String ALL_CUST;
    @SerializedName("BALANCE")
    private String BALANCE;

    @SerializedName("FIRST_NAME")
    private String FIRST_NAME;
    @SerializedName("SECOND_NAME")
    private String SECOND_NAME;
    @SerializedName("THIERD_NAME")
    private String THIERD_NAME;
    @SerializedName("LAST_NAME")
    private String LAST_NAME;
    @SerializedName("FULL_NAME")
    private String FULL_NAME;

    public CustomerInfoModel() {
    }

    public CustomerInfoModel(String CODE, String BIRTH_DATE_AD, String AGE, String GENDER, String NATIONALITY, String PHONE, String ADDRESS, String SOURCE, String INS_USER_NO, String INS_TIME, String INS_DATE, String UPD_USER_NO, String UPD_TIME, String UPD_DATE, String NOTE_1, String NOTE_2, String ACCCODE, String POINTVIP, String ALL_CUST, String BALANCE, String FIRST_NAME, String SECOND_NAME, String THIERD_NAME, String LAST_NAME, String FULL_NAME) {
        this.CODE = CODE;
        this.BIRTH_DATE_AD = BIRTH_DATE_AD;
        this.AGE = AGE;
        this.GENDER = GENDER;
        this.NATIONALITY = NATIONALITY;
        this.PHONE = PHONE;
        this.ADDRESS = ADDRESS;
        this.SOURCE = SOURCE;
        this.INS_USER_NO = INS_USER_NO;
        this.INS_TIME = INS_TIME;
        this.INS_DATE = INS_DATE;
        this.UPD_USER_NO = UPD_USER_NO;
        this.UPD_TIME = UPD_TIME;
        this.UPD_DATE = UPD_DATE;
        this.NOTE_1 = NOTE_1;
        this.NOTE_2 = NOTE_2;
        this.ACCCODE = ACCCODE;
        this.POINTVIP = POINTVIP;
        this.ALL_CUST = ALL_CUST;
        this.BALANCE = BALANCE;
        this.FIRST_NAME = FIRST_NAME;
        this.SECOND_NAME = SECOND_NAME;
        this.THIERD_NAME = THIERD_NAME;
        this.LAST_NAME = LAST_NAME;
        this.FULL_NAME = FULL_NAME;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getBIRTH_DATE_AD() {
        return BIRTH_DATE_AD;
    }

    public void setBIRTH_DATE_AD(String BIRTH_DATE_AD) {
        this.BIRTH_DATE_AD = BIRTH_DATE_AD;
    }

    public String getAGE() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getNATIONALITY() {
        return NATIONALITY;
    }

    public void setNATIONALITY(String NATIONALITY) {
        this.NATIONALITY = NATIONALITY;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getSOURCE() {
        return SOURCE;
    }

    public void setSOURCE(String SOURCE) {
        this.SOURCE = SOURCE;
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

    public String getNOTE_1() {
        return NOTE_1;
    }

    public void setNOTE_1(String NOTE_1) {
        this.NOTE_1 = NOTE_1;
    }

    public String getNOTE_2() {
        return NOTE_2;
    }

    public void setNOTE_2(String NOTE_2) {
        this.NOTE_2 = NOTE_2;
    }

    public String getACCCODE() {
        return ACCCODE;
    }

    public void setACCCODE(String ACCCODE) {
        this.ACCCODE = ACCCODE;
    }

    public String getPOINTVIP() {
        return POINTVIP;
    }

    public void setPOINTVIP(String POINTVIP) {
        this.POINTVIP = POINTVIP;
    }

    public String getALL_CUST() {
        return ALL_CUST;
    }

    public void setALL_CUST(String ALL_CUST) {
        this.ALL_CUST = ALL_CUST;
    }

    public String getBALANCE() {
        return BALANCE;
    }

    public void setBALANCE(String BALANCE) {
        this.BALANCE = BALANCE;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getSECOND_NAME() {
        return SECOND_NAME;
    }

    public void setSECOND_NAME(String SECOND_NAME) {
        this.SECOND_NAME = SECOND_NAME;
    }

    public String getTHIERD_NAME() {
        return THIERD_NAME;
    }

    public void setTHIERD_NAME(String THIERD_NAME) {
        this.THIERD_NAME = THIERD_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getFULL_NAME() {
        return FULL_NAME;
    }

    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }
}
