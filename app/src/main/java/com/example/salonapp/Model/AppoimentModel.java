package com.example.salonapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "APPOINTMENT_TABLE")
public class AppoimentModel {
//    [{"CODE":"38914","TODAY":"30\/05\/2022","FROM_TIME":"14:30","TO_TIME":"16:00","DURATION":"01:30","
//    EMPLOYEE_NO":"10","TYPE_NO":"5","TYPE_COLOR":"clRed","TYPE_DURATION":"00:00","CUSTOMER_NO":"9229","NOTE_1":"",
//    "NOTE_2":"","INS_USER_NO":"6","INS_TIME":"14:06","INS_DATE":"30\/05\/2022","UPD_USER_NO":"","UPD_TIME":"",
//    "UPD_DATE":"","DONE":"1","OLD_TYPE_NO":"2","OLD_TYPE_COLOR":"clNavy","CHECK_IN":"0","CHECK_OUT":"0",
//    "OLD_TYPE_NO_IN":"0","OLD_TYPE_COLOR_IN":"","OLD_TYPE_NO_OUT":"0","OLD_TYPE_COLOR_OUT":"","IS_DELETE":"0",
//    "DELETE_NOTE":"","DONE_TIME":"14:07","DONE_DATE":"30\/05\/2022","IN_PROCESS":"0","IN_PROCESS_TIME":"",
//    "IN_PROCESS_DATE":"","END_PROCESS":"0","END_PROCESS_TIME":"","END_PROCESS_DATE":"","DONE_USER_NO":"6",
//    "IN_PROCESS_USER_NO":"","END_PROCESS_USER_NO":"","DIVISION":"","IS_OPENED":""},
@PrimaryKey
@NonNull
@SerializedName("CODE")
@ColumnInfo(name = "CODE")
    private String CODE;
    @SerializedName("TODAY")
    @ColumnInfo(name = "TODAY")
    private String TODAY;
    @SerializedName("FROM_TIME")
    @ColumnInfo(name = "FROM_TIME")
    private String FROM_TIME;
    @SerializedName("TO_TIME")
    @ColumnInfo(name = "TO_TIME")
    private String TO_TIME;
    @SerializedName("DURATION")
    @ColumnInfo(name = "DURATION")
    private String DURATION;
    @SerializedName("EMPLOYEE_NO")
    @ColumnInfo(name = "EMPLOYEE_NO")
    private String EMPLOYEE_NO;
    @SerializedName("TYPE_NO")
    @ColumnInfo(name = "TYPE_NO")
    private String TYPE_NO;
    @SerializedName("TYPE_COLOR")
    @ColumnInfo(name = "TYPE_COLOR")
    private String TYPE_COLOR;
    @SerializedName("TYPE_DURATION")
    @ColumnInfo(name = "TYPE_DURATION")
    private String TYPE_DURATION;
    @SerializedName("CUSTOMER_NO")
    @ColumnInfo(name = "CUSTOMER_NO")
    private String CUSTOMER_NO;
    @SerializedName("NOTE_1")
    @ColumnInfo(name = "NOTE_1")
    private String NOTE_1;
    @SerializedName("NOTE_2")
    @ColumnInfo(name = "NOTE_2")
    private String NOTE_2;
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
    @SerializedName("DONE")
    @ColumnInfo(name = "DONE")
    private String DONE;
    @SerializedName("OLD_TYPE_NO")
    @ColumnInfo(name = "OLD_TYPE_NO")
    private String OLD_TYPE_NO;
    @SerializedName("OLD_TYPE_COLOR")
    @ColumnInfo(name = "OLD_TYPE_COLOR")
    private String OLD_TYPE_COLOR;
    @SerializedName("CHECK_IN")
    @ColumnInfo(name = "CHECK_IN")
    private String CHECK_IN;
    @SerializedName("CHECK_OUT")
    @ColumnInfo(name = "CHECK_OUT")
    private String CHECK_OUT;
    @SerializedName("OLD_TYPE_NO_IN")
    @ColumnInfo(name = "OLD_TYPE_NO_IN")
    private String OLD_TYPE_NO_IN;
    @SerializedName("OLD_TYPE_COLOR_IN")
    @ColumnInfo(name = "OLD_TYPE_COLOR_IN")
    private String OLD_TYPE_COLOR_IN;
    @SerializedName("OLD_TYPE_NO_OUT")
    @ColumnInfo(name = "OLD_TYPE_NO_OUT")
    private String OLD_TYPE_NO_OUT;
    @SerializedName("OLD_TYPE_COLOR_OUT")
    @ColumnInfo(name = "OLD_TYPE_COLOR_OUT")
    private String OLD_TYPE_COLOR_OUT;
    @SerializedName("IS_DELETE")
    @ColumnInfo(name = "IS_DELETE")
    private String IS_DELETE;
    @SerializedName("DELETE_NOTE")
    @ColumnInfo(name = "DELETE_NOTE")
    private String DELETE_NOTE;
    @SerializedName("DONE_TIME")
    @ColumnInfo(name = "DONE_TIME")
    private String DONE_TIME;
    @SerializedName("DONE_DATE")
    @ColumnInfo(name = "DONE_DATE")
    private String DONE_DATE;
    @SerializedName("IN_PROCESS")
    @ColumnInfo(name = "IN_PROCESS")
    private String IN_PROCESS;
    @SerializedName("IN_PROCESS_TIME")
    @ColumnInfo(name = "IN_PROCESS_TIME")
    private String IN_PROCESS_TIME;
    @SerializedName("IN_PROCESS_DATE")
    @ColumnInfo(name = "IN_PROCESS_DATE")
    private String IN_PROCESS_DATE;
    @SerializedName("END_PROCESS")
    @ColumnInfo(name = "END_PROCESS")
    private String END_PROCESS;
    @SerializedName("END_PROCESS_TIME")
    @ColumnInfo(name = "END_PROCESS_TIME")
    private String END_PROCESS_TIME;
    @SerializedName("END_PROCESS_DATE")
    @ColumnInfo(name = "END_PROCESS_DATE")
    private String END_PROCESS_DATE;
    @SerializedName("DONE_USER_NO")
    @ColumnInfo(name = "DONE_USER_NO")
    private String DONE_USER_NO;
    @SerializedName("IN_PROCESS_USER_NO")
    @ColumnInfo(name = "IN_PROCESS_USER_NO")
    private String IN_PROCESS_USER_NO;
    @SerializedName("END_PROCESS_USER_NO")
    @ColumnInfo(name = "END_PROCESS_USER_NO")
    private String END_PROCESS_USER_NO;
    @SerializedName("DIVISION")
    @ColumnInfo(name = "DIVISION")
    private String DIVISION;
    @SerializedName("IS_OPENED")
    @ColumnInfo(name = "IS_OPENED")
    private String IS_OPENED;

    public AppoimentModel() {
    }

    public AppoimentModel(@NonNull String CODE, String TODAY, String FROM_TIME, String TO_TIME, String DURATION,
                          String EMPLOYEE_NO, String TYPE_NO, String TYPE_COLOR, String TYPE_DURATION,
                          String CUSTOMER_NO, String NOTE_1, String NOTE_2, String INS_USER_NO, String INS_TIME,
                          String INS_DATE, String UPD_USER_NO, String UPD_TIME, String UPD_DATE, String DONE,
                          String OLD_TYPE_NO, String OLD_TYPE_COLOR, String CHECK_IN, String CHECK_OUT,
                          String OLD_TYPE_NO_IN, String OLD_TYPE_COLOR_IN, String OLD_TYPE_NO_OUT,
                          String OLD_TYPE_COLOR_OUT, String IS_DELETE, String DELETE_NOTE, String DONE_TIME,
                          String DONE_DATE, String IN_PROCESS, String IN_PROCESS_TIME, String IN_PROCESS_DATE,
                          String END_PROCESS, String END_PROCESS_TIME, String END_PROCESS_DATE, String DONE_USER_NO,
                          String IN_PROCESS_USER_NO, String END_PROCESS_USER_NO, String DIVISION, String IS_OPENED) {
        this.CODE = CODE;
        this.TODAY = TODAY;
        this.FROM_TIME = FROM_TIME;
        this.TO_TIME = TO_TIME;
        this.DURATION = DURATION;
        this.EMPLOYEE_NO = EMPLOYEE_NO;
        this.TYPE_NO = TYPE_NO;
        this.TYPE_COLOR = TYPE_COLOR;
        this.TYPE_DURATION = TYPE_DURATION;
        this.CUSTOMER_NO = CUSTOMER_NO;
        this.NOTE_1 = NOTE_1;
        this.NOTE_2 = NOTE_2;
        this.INS_USER_NO = INS_USER_NO;
        this.INS_TIME = INS_TIME;
        this.INS_DATE = INS_DATE;
        this.UPD_USER_NO = UPD_USER_NO;
        this.UPD_TIME = UPD_TIME;
        this.UPD_DATE = UPD_DATE;
        this.DONE = DONE;
        this.OLD_TYPE_NO = OLD_TYPE_NO;
        this.OLD_TYPE_COLOR = OLD_TYPE_COLOR;
        this.CHECK_IN = CHECK_IN;
        this.CHECK_OUT = CHECK_OUT;
        this.OLD_TYPE_NO_IN = OLD_TYPE_NO_IN;
        this.OLD_TYPE_COLOR_IN = OLD_TYPE_COLOR_IN;
        this.OLD_TYPE_NO_OUT = OLD_TYPE_NO_OUT;
        this.OLD_TYPE_COLOR_OUT = OLD_TYPE_COLOR_OUT;
        this.IS_DELETE = IS_DELETE;
        this.DELETE_NOTE = DELETE_NOTE;
        this.DONE_TIME = DONE_TIME;
        this.DONE_DATE = DONE_DATE;
        this.IN_PROCESS = IN_PROCESS;
        this.IN_PROCESS_TIME = IN_PROCESS_TIME;
        this.IN_PROCESS_DATE = IN_PROCESS_DATE;
        this.END_PROCESS = END_PROCESS;
        this.END_PROCESS_TIME = END_PROCESS_TIME;
        this.END_PROCESS_DATE = END_PROCESS_DATE;
        this.DONE_USER_NO = DONE_USER_NO;
        this.IN_PROCESS_USER_NO = IN_PROCESS_USER_NO;
        this.END_PROCESS_USER_NO = END_PROCESS_USER_NO;
        this.DIVISION = DIVISION;
        this.IS_OPENED = IS_OPENED;
    }

    @NonNull
    public String getCODE() {
        return CODE;
    }

    public void setCODE(@NonNull String CODE) {
        this.CODE = CODE;
    }

    public String getTODAY() {
        return TODAY;
    }

    public void setTODAY(String TODAY) {
        this.TODAY = TODAY;
    }

    public String getFROM_TIME() {
        return FROM_TIME;
    }

    public void setFROM_TIME(String FROM_TIME) {
        this.FROM_TIME = FROM_TIME;
    }

    public String getTO_TIME() {
        return TO_TIME;
    }

    public void setTO_TIME(String TO_TIME) {
        this.TO_TIME = TO_TIME;
    }

    public String getDURATION() {
        return DURATION;
    }

    public void setDURATION(String DURATION) {
        this.DURATION = DURATION;
    }

    public String getEMPLOYEE_NO() {
        return EMPLOYEE_NO;
    }

    public void setEMPLOYEE_NO(String EMPLOYEE_NO) {
        this.EMPLOYEE_NO = EMPLOYEE_NO;
    }

    public String getTYPE_NO() {
        return TYPE_NO;
    }

    public void setTYPE_NO(String TYPE_NO) {
        this.TYPE_NO = TYPE_NO;
    }

    public String getTYPE_COLOR() {
        return TYPE_COLOR;
    }

    public void setTYPE_COLOR(String TYPE_COLOR) {
        this.TYPE_COLOR = TYPE_COLOR;
    }

    public String getTYPE_DURATION() {
        return TYPE_DURATION;
    }

    public void setTYPE_DURATION(String TYPE_DURATION) {
        this.TYPE_DURATION = TYPE_DURATION;
    }

    public String getCUSTOMER_NO() {
        return CUSTOMER_NO;
    }

    public void setCUSTOMER_NO(String CUSTOMER_NO) {
        this.CUSTOMER_NO = CUSTOMER_NO;
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

    public String getDONE() {
        return DONE;
    }

    public void setDONE(String DONE) {
        this.DONE = DONE;
    }

    public String getOLD_TYPE_NO() {
        return OLD_TYPE_NO;
    }

    public void setOLD_TYPE_NO(String OLD_TYPE_NO) {
        this.OLD_TYPE_NO = OLD_TYPE_NO;
    }

    public String getOLD_TYPE_COLOR() {
        return OLD_TYPE_COLOR;
    }

    public void setOLD_TYPE_COLOR(String OLD_TYPE_COLOR) {
        this.OLD_TYPE_COLOR = OLD_TYPE_COLOR;
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

    public String getOLD_TYPE_NO_IN() {
        return OLD_TYPE_NO_IN;
    }

    public void setOLD_TYPE_NO_IN(String OLD_TYPE_NO_IN) {
        this.OLD_TYPE_NO_IN = OLD_TYPE_NO_IN;
    }

    public String getOLD_TYPE_COLOR_IN() {
        return OLD_TYPE_COLOR_IN;
    }

    public void setOLD_TYPE_COLOR_IN(String OLD_TYPE_COLOR_IN) {
        this.OLD_TYPE_COLOR_IN = OLD_TYPE_COLOR_IN;
    }

    public String getOLD_TYPE_NO_OUT() {
        return OLD_TYPE_NO_OUT;
    }

    public void setOLD_TYPE_NO_OUT(String OLD_TYPE_NO_OUT) {
        this.OLD_TYPE_NO_OUT = OLD_TYPE_NO_OUT;
    }

    public String getOLD_TYPE_COLOR_OUT() {
        return OLD_TYPE_COLOR_OUT;
    }

    public void setOLD_TYPE_COLOR_OUT(String OLD_TYPE_COLOR_OUT) {
        this.OLD_TYPE_COLOR_OUT = OLD_TYPE_COLOR_OUT;
    }

    public String getIS_DELETE() {
        return IS_DELETE;
    }

    public void setIS_DELETE(String IS_DELETE) {
        this.IS_DELETE = IS_DELETE;
    }

    public String getDELETE_NOTE() {
        return DELETE_NOTE;
    }

    public void setDELETE_NOTE(String DELETE_NOTE) {
        this.DELETE_NOTE = DELETE_NOTE;
    }

    public String getDONE_TIME() {
        return DONE_TIME;
    }

    public void setDONE_TIME(String DONE_TIME) {
        this.DONE_TIME = DONE_TIME;
    }

    public String getDONE_DATE() {
        return DONE_DATE;
    }

    public void setDONE_DATE(String DONE_DATE) {
        this.DONE_DATE = DONE_DATE;
    }

    public String getIN_PROCESS() {
        return IN_PROCESS;
    }

    public void setIN_PROCESS(String IN_PROCESS) {
        this.IN_PROCESS = IN_PROCESS;
    }

    public String getIN_PROCESS_TIME() {
        return IN_PROCESS_TIME;
    }

    public void setIN_PROCESS_TIME(String IN_PROCESS_TIME) {
        this.IN_PROCESS_TIME = IN_PROCESS_TIME;
    }

    public String getIN_PROCESS_DATE() {
        return IN_PROCESS_DATE;
    }

    public void setIN_PROCESS_DATE(String IN_PROCESS_DATE) {
        this.IN_PROCESS_DATE = IN_PROCESS_DATE;
    }

    public String getEND_PROCESS() {
        return END_PROCESS;
    }

    public void setEND_PROCESS(String END_PROCESS) {
        this.END_PROCESS = END_PROCESS;
    }

    public String getEND_PROCESS_TIME() {
        return END_PROCESS_TIME;
    }

    public void setEND_PROCESS_TIME(String END_PROCESS_TIME) {
        this.END_PROCESS_TIME = END_PROCESS_TIME;
    }

    public String getEND_PROCESS_DATE() {
        return END_PROCESS_DATE;
    }

    public void setEND_PROCESS_DATE(String END_PROCESS_DATE) {
        this.END_PROCESS_DATE = END_PROCESS_DATE;
    }

    public String getDONE_USER_NO() {
        return DONE_USER_NO;
    }

    public void setDONE_USER_NO(String DONE_USER_NO) {
        this.DONE_USER_NO = DONE_USER_NO;
    }

    public String getIN_PROCESS_USER_NO() {
        return IN_PROCESS_USER_NO;
    }

    public void setIN_PROCESS_USER_NO(String IN_PROCESS_USER_NO) {
        this.IN_PROCESS_USER_NO = IN_PROCESS_USER_NO;
    }

    public String getEND_PROCESS_USER_NO() {
        return END_PROCESS_USER_NO;
    }

    public void setEND_PROCESS_USER_NO(String END_PROCESS_USER_NO) {
        this.END_PROCESS_USER_NO = END_PROCESS_USER_NO;
    }

    public String getDIVISION() {
        return DIVISION;
    }

    public void setDIVISION(String DIVISION) {
        this.DIVISION = DIVISION;
    }

    public String getIS_OPENED() {
        return IS_OPENED;
    }

    public void setIS_OPENED(String IS_OPENED) {
        this.IS_OPENED = IS_OPENED;
    }
}
