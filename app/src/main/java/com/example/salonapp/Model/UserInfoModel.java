package com.example.salonapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "USER_INFO_TABLE")
public class UserInfoModel {

    //[{"CODE":"1","USER_NO":"1","NAME":"Master","PASSWORD":"20162012202505000",
    // "DEPARTMENT_NO":"2","GROUP_NO":"1","ACTIVE":"1","MASTER":"1","INS_USER_NO":"10115","INS_TIME":"01:01:44 PM",
    // "INS_DATE":"01\/07\/2018","UPD_USER_NO":"1","UPD_TIME":"10:19:09 AM","UPD_DATE":"29\/06\/2021","SECTION":""},
    @PrimaryKey
    @NonNull
    @SerializedName("CODE")
    @ColumnInfo(name = "CODE")
    private String CODE;
    @SerializedName("USER_NO")
    @ColumnInfo(name = "USER_NO")
    private String USER_NO;
    @SerializedName("NAME")
    @ColumnInfo(name = "NAME")
    private String NAME;
    @SerializedName("PASSWORD")
    @ColumnInfo(name = "PASSWORD")
    private String PASSWORD;
    @SerializedName("DEPARTMENT_NO")
    @ColumnInfo(name = "DEPARTMENT_NO")
    private String DEPARTMENT_NO;
    @SerializedName("GROUP_NO")
    @ColumnInfo(name = "GROUP_NO")
    private String GROUP_NO;
    @SerializedName("ACTIVE")
    @ColumnInfo(name = "ACTIVE")
    private String ACTIVE;
    @SerializedName("MASTER")
    @ColumnInfo(name = "MASTER")
    private String MASTER;
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
    @SerializedName("SECTION")
    @ColumnInfo(name = "SECTION")
    private String SECTION;

    public UserInfoModel() {
    }


    public UserInfoModel(@NonNull String CODE, String USER_NO, String NAME, String PASSWORD, String DEPARTMENT_NO,
                         String GROUP_NO, String ACTIVE, String MASTER, String INS_USER_NO
            , String INS_TIME, String INS_DATE, String UPD_USER_NO, String UPD_TIME, String UPD_DATE, String SECTION) {
        this.CODE = CODE;
        this.USER_NO = USER_NO;
        this.NAME = NAME;
        this.PASSWORD = PASSWORD;
        this.DEPARTMENT_NO = DEPARTMENT_NO;
        this.GROUP_NO = GROUP_NO;
        this.ACTIVE = ACTIVE;
        this.MASTER = MASTER;
        this.INS_USER_NO = INS_USER_NO;
        this.INS_TIME = INS_TIME;
        this.INS_DATE = INS_DATE;
        this.UPD_USER_NO = UPD_USER_NO;
        this.UPD_TIME = UPD_TIME;
        this.UPD_DATE = UPD_DATE;
        this.SECTION = SECTION;
    }

    @NonNull
    public String getCODE() {
        return CODE;
    }

    public void setCODE(@NonNull String CODE) {
        this.CODE = CODE;
    }

    public String getUSER_NO() {
        return USER_NO;
    }

    public void setUSER_NO(String USER_NO) {
        this.USER_NO = USER_NO;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getDEPARTMENT_NO() {
        return DEPARTMENT_NO;
    }

    public void setDEPARTMENT_NO(String DEPARTMENT_NO) {
        this.DEPARTMENT_NO = DEPARTMENT_NO;
    }

    public String getGROUP_NO() {
        return GROUP_NO;
    }

    public void setGROUP_NO(String GROUP_NO) {
        this.GROUP_NO = GROUP_NO;
    }

    public String getACTIVE() {
        return ACTIVE;
    }

    public void setACTIVE(String ACTIVE) {
        this.ACTIVE = ACTIVE;
    }

    public String getMASTER() {
        return MASTER;
    }

    public void setMASTER(String MASTER) {
        this.MASTER = MASTER;
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

    public String getSECTION() {
        return SECTION;
    }

    public void setSECTION(String SECTION) {
        this.SECTION = SECTION;
    }
}

