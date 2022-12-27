package com.example.salonapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "TypeTable")
public class Type {
    //{"CODE":"1","NAME":"Hair Service","COLOR":"clGreen","LEAVE":"0","BREAKS":"0","INS_USER_NO":"1",
    // "INS_TIME":"21:17","INS_DATE":"19\/05\/2020","UPD_USER_NO":"1","UPD_TIME":"17:46",
    // "UPD_DATE":"09\/06\/2020","REQUEST":"0","DONE":"0","CHECK_IN":"0","CHECK_OUT":"0","IS_DELETE":"0","IN_PROCESS":"0","END_PROCESS":"0"}

    @PrimaryKey
    @NonNull
    @SerializedName("CODE")
    @ColumnInfo(name = "CODE")
    private String CODE;
    @SerializedName("NAME")
    @ColumnInfo(name = "NAME")
    private String NAME;
    @SerializedName("COLOR")
    @ColumnInfo(name = "COLOR")
    private String COLOR;
    @SerializedName("LEAVE")
    @ColumnInfo(name = "LEAVE")
    private String LEAVE;
    @SerializedName("BREAKS")
    @ColumnInfo(name = "BREAKS")
    private String BREAKS;
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
    @SerializedName("REQUEST")
    @ColumnInfo(name = "REQUEST")
    private String REQUEST;
    @SerializedName("DONE")
    @ColumnInfo(name = "DONE")
    private String DONE;
    @SerializedName("CHECK_IN")
    @ColumnInfo(name = "CHECK_IN")
    private String CHECK_IN;
    @SerializedName("CHECK_OUT")
    @ColumnInfo(name = "CHECK_OUT")
    private String CHECK_OUT;
    @SerializedName("IS_DELETE")
    @ColumnInfo(name = "IS_DELETE")
    private String IS_DELETE;
    @SerializedName("IN_PROCESS")
    @ColumnInfo(name = "IN_PROCESS")
    private String IN_PROCESS;
    @SerializedName("END_PROCESS")
    @ColumnInfo(name = "END_PROCESS")
    private String END_PROCESS;

    public Type() {

    }

    public Type(String CODE, String NAME, String COLOR, String LEAVE, String BREAKS, String INS_USER_NO, String INS_TIME, String INS_DATE, String UPD_USER_NO, String UPD_TIME, String UPD_DATE, String REQUEST, String DONE,
                String CHECK_IN, String CHECK_OUT, String IS_DELETE, String IN_PROCESS, String END_PROCESS) {
        this.CODE = CODE;
        this.NAME = NAME;
        this.COLOR = COLOR;
        this.LEAVE = LEAVE;
        this.BREAKS = BREAKS;
        this.INS_USER_NO = INS_USER_NO;
        this.INS_TIME = INS_TIME;
        this.INS_DATE = INS_DATE;
        this.UPD_USER_NO = UPD_USER_NO;
        this.UPD_TIME = UPD_TIME;
        this.UPD_DATE = UPD_DATE;
        this.REQUEST = REQUEST;
        this.DONE = DONE;
        this.CHECK_IN = CHECK_IN;
        this.CHECK_OUT = CHECK_OUT;
        this.IS_DELETE = IS_DELETE;
        this.IN_PROCESS = IN_PROCESS;
        this.END_PROCESS = END_PROCESS;
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

    public String getCOLOR() {
        return COLOR;
    }

    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }

    public String getLEAVE() {
        return LEAVE;
    }

    public void setLEAVE(String LEAVE) {
        this.LEAVE = LEAVE;
    }

    public String getBREAKS() {
        return BREAKS;
    }

    public void setBREAKS(String BREAKS) {
        this.BREAKS = BREAKS;
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

    public String getREQUEST() {
        return REQUEST;
    }

    public void setREQUEST(String REQUEST) {
        this.REQUEST = REQUEST;
    }

    public String getDONE() {
        return DONE;
    }

    public void setDONE(String DONE) {
        this.DONE = DONE;
    }

    public String getCHECK_IN() {
        return CHECK_IN;
    }

    public void setCHECK_IN(String CHECK_IN) {
        this.CHECK_IN = CHECK_IN;
    }

    public String getCHECK_OUT() {
        return CHECK_OUT;
    }

    public void setCHECK_OUT(String CHECK_OUT) {
        this.CHECK_OUT = CHECK_OUT;
    }

    public String getIS_DELETE() {
        return IS_DELETE;
    }

    public void setIS_DELETE(String IS_DELETE) {
        this.IS_DELETE = IS_DELETE;
    }

    public String getIN_PROCESS() {
        return IN_PROCESS;
    }

    public void setIN_PROCESS(String IN_PROCESS) {
        this.IN_PROCESS = IN_PROCESS;
    }

    public String getEND_PROCESS() {
        return END_PROCESS;
    }

    public void setEND_PROCESS(String END_PROCESS) {
        this.END_PROCESS = END_PROCESS;
    }
}
