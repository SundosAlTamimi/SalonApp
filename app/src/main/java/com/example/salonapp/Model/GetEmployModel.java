package com.example.salonapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "EMPLOY_TABLE")

public class GetEmployModel {
//[{"CODE":"54","NAME":"Fawzia - Makeup","JOB_NO":"3","INS_USER_NO":"6","INS_TIME":"16:26","INS_DATE":"03\/06\/2022","UPD_USER_NO":"","UPD_TIME":"","UPD_DATE":"",
// "ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"51","NAME":"Charlie - Colorist","JOB_NO":"1","INS_USER_NO":"6","INS_TIME":"10:33:10","INS_DATE":"25\/04\/2022","UPD_USER_NO":"6","UPD_TIME":"20:13","UPD_DATE":"25\/04\/2022","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"52","NAME":"Taghreed - Coloriest","JOB_NO":"7","INS_USER_NO":"6","INS_TIME":"12:18:07","INS_DATE":"15\/05\/2022","UPD_USER_NO":"6","UPD_TIME":"09:22","UPD_DATE":"23\/05\/2022","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"53","NAME":"Ibrahim - Asst","JOB_NO":"2","INS_USER_NO":"6","INS_TIME":"11:20","INS_DATE":"17\/05\/2022","UPD_USER_NO":"6","UPD_TIME":"11:20","UPD_DATE":"17\/05\/2022","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"39","NAME":"Qais - MEN","JOB_NO":"1","INS_USER_NO":"6","INS_TIME":"12:37","INS_DATE":"07\/05\/2021","UPD_USER_NO":"6","UPD_TIME":"09:07","UPD_DATE":"05\/08\/2021","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"40","NAME":"Abood-MEN","JOB_NO":"1","INS_USER_NO":"6","INS_TIME":"12:37","INS_DATE":"07\/05\/2021","UPD_USER_NO":"6","UPD_TIME":"09:07","UPD_DATE":"05\/08\/2021","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"38","NAME":"Mahmoud","JOB_NO":"2","INS_USER_NO":"6","INS_TIME":"9:25:34 AM","INS_DATE":"24\/01\/2021","UPD_USER_NO":"6","UPD_TIME":"09:31","UPD_DATE":"10\/05\/2021","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"43","NAME":"Taj - Nails","JOB_NO":"5","INS_USER_NO":"6","INS_TIME":"17:55","INS_DATE":"22\/05\/2021","UPD_USER_NO":"","UPD_TIME":"","UPD_DATE":"","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"44","NAME":"Noor - Eyebrow","JOB_NO":"9","INS_USER_NO":"6","INS_TIME":"19:00","INS_DATE":"29\/05\/2021","UPD_USER_NO":"6","UPD_TIME":"10:02:57","UPD_DATE":"21\/02\/2022","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"45","NAME":"Meshmesh - Nails","JOB_NO":"5","INS_USER_NO":"6","INS_TIME":"16:12","INS_DATE":"03\/06\/2021","UPD_USER_NO":"6","UPD_TIME":"09:54","UPD_DATE":"04\/06\/2021","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"46","NAME":"Tia - Nails","JOB_NO":"5","INS_USER_NO":"6","INS_TIME":"16:14","INS_DATE":"03\/06\/2021","UPD_USER_NO":"","UPD_TIME":"","UPD_DATE":"","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"47","NAME":"Rozan-Asst","JOB_NO":"2","INS_USER_NO":"6","INS_TIME":"10:20","INS_DATE":"13\/07\/2021","UPD_USER_NO":"6","UPD_TIME":"12:55","UPD_DATE":"29\/04\/2022","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"48","NAME":"Daisy - Nail","JOB_NO":"6","INS_USER_NO":"6","INS_TIME":"14:40","INS_DATE":"01\/01\/2022","UPD_USER_NO":"6","UPD_TIME":"14:41","UPD_DATE":"01\/01\/2022","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"41","NAME":"Dalal - HairArtist","JOB_NO":"1","INS_USER_NO":"6","INS_TIME":"13:29","INS_DATE":"15\/05\/2021","UPD_USER_NO":"6","UPD_TIME":"09:08","UPD_DATE":"05\/08\/2021","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"42","NAME":"Farah - Makeup","JOB_NO":"3","INS_USER_NO":"6","INS_TIME":"14:50","INS_DATE":"19\/05\/2021","UPD_USER_NO":"","UPD_TIME":"","UPD_DATE":"","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"2","NAME":"Hovig - HairArtist","JOB_NO":"1","INS_USER_NO":"1","INS_TIME":"11:01:27 ص","INS_DATE":"19\/05\/2020","UPD_USER_NO":"6","UPD_TIME":"18:14:22","UPD_DATE":"01\/12\/2021","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"3","NAME":"Khader - HairArtist","JOB_NO":"1","INS_USER_NO":"1","INS_TIME":"11:01:35 ص","INS_DATE":"19\/05\/2020","UPD_USER_NO":"6","UPD_TIME":"14:25:43","UPD_DATE":"10\/03\/2021","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"1","NAME":"Raffi - Colorist","JOB_NO":"7","INS_USER_NO":"1","INS_TIME":"11:01:17 ص","INS_DATE":"19\/05\/2020","UPD_USER_NO":"6","UPD_TIME":"13:58","UPD_DATE":"02\/01\/2021","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"36","NAME":"Reham","JOB_NO":"2","INS_USER_NO":"6","INS_TIME":"13:15:03","INS_DATE":"22\/09\/2020","UPD_USER_NO":"6","UPD_TIME":"09:10","UPD_DATE":"26\/11\/2020","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"5","NAME":"Esraa - HairArtist","JOB_NO":"1","INS_USER_NO":"1","INS_TIME":"11:02:01 ص","INS_DATE":"19\/05\/2020","UPD_USER_NO":"6","UPD_TIME":"09:36","UPD_DATE":"07\/03\/2021","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"6","NAME":"Diana - Makeup","JOB_NO":"3","INS_USER_NO":"1","INS_TIME":"11:03:32 ص","INS_DATE":"19\/05\/2020","UPD_USER_NO":"6","UPD_TIME":"11:24","UPD_DATE":"17\/07\/2021","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"10","NAME":"Jakob - The Boss","JOB_NO":"3","INS_USER_NO":"1","INS_TIME":"5:48:48 PM","INS_DATE":"01\/06\/2020","UPD_USER_NO":"1","UPD_TIME":"19:24","UPD_DATE":"27\/06\/2020","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"18","NAME":"Joy - Nails","JOB_NO":"5","INS_USER_NO":"1","INS_TIME":"09:15","INS_DATE":"14\/06\/2020","UPD_USER_NO":"6","UPD_TIME":"20:32:41","UPD_DATE":"31\/12\/2021","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"21","NAME":"Dina - Nails","JOB_NO":"6","INS_USER_NO":"1","INS_TIME":"3:20:39 PM","INS_DATE":"22\/06\/2020","UPD_USER_NO":"6","UPD_TIME":"09:34","UPD_DATE":"06\/03\/2021","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"22","NAME":"Rasha - MakeupArtist","JOB_NO":"3","INS_USER_NO":"1","INS_TIME":"19:21","INS_DATE":"27\/06\/2020","UPD_USER_NO":"6","UPD_TIME":"9:46:44","UPD_DATE":"04\/10\/2020","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"23","NAME":"YuYu - Nails","JOB_NO":"6","INS_USER_NO":"1","INS_TIME":"9:09:32 AM","INS_DATE":"08\/07\/2020","UPD_USER_NO":"6","UPD_TIME":"20:41","UPD_DATE":"21\/07\/2020","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"30","NAME":"Marina - Lashes","JOB_NO":"10","INS_USER_NO":"6","INS_TIME":"16:26","INS_DATE":"19\/08\/2020","UPD_USER_NO":"6","UPD_TIME":"9:24:35 AM","UPD_DATE":"24\/01\/2021","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"31","NAME":"Julla - Lashes","JOB_NO":"10","INS_USER_NO":"6","INS_TIME":"16:27","INS_DATE":"19\/08\/2020","UPD_USER_NO":"6","UPD_TIME":"13:51","UPD_DATE":"21\/12\/2020","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"32","NAME":"Allona - Lashes","JOB_NO":"10","INS_USER_NO":"6","INS_TIME":"16:27","INS_DATE":"19\/08\/2020","UPD_USER_NO":"6","UPD_TIME":"13:51","UPD_DATE":"21\/12\/2020","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"33","NAME":"Anastasia - Tatto\/Lashes","JOB_NO":"10","INS_USER_NO":"6","INS_TIME":"16:28","INS_DATE":"19\/08\/2020","UPD_USER_NO":"6","UPD_TIME":"13:51","UPD_DATE":"21\/12\/2020","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"34","NAME":"Oxana - Facial","JOB_NO":"12","INS_USER_NO":"6","INS_TIME":"16:28","INS_DATE":"19\/08\/2020","UPD_USER_NO":"6","UPD_TIME":"13:51","UPD_DATE":"21\/12\/2020","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"11","NAME":"Nilly - Nails","JOB_NO":"6","INS_USER_NO":"1","INS_TIME":"11:56:04 AM","INS_DATE":"03\/06\/2020","UPD_USER_NO":"6","UPD_TIME":"16:11","UPD_DATE":"03\/06\/2021","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"26","NAME":"Wala - Colorist","JOB_NO":"7","INS_USER_NO":"6","INS_TIME":"11:07","INS_DATE":"01\/08\/2020","UPD_USER_NO":"6","UPD_TIME":"4:15:27 PM","UPD_DATE":"20\/12\/2020","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"27","NAME":"Atef - Colorist","JOB_NO":"7","INS_USER_NO":"6","INS_TIME":"14:32","INS_DATE":"07\/08\/2020","UPD_USER_NO":"","UPD_TIME":"","UPD_DATE":"","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"28","NAME":"Diana - Nails","JOB_NO":"6","INS_USER_NO":"6","INS_TIME":"12:04","INS_DATE":"10\/08\/2020","UPD_USER_NO":"6","UPD_TIME":"09:27","UPD_DATE":"15\/08\/2020","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"37","NAME":"Hala - Skin Care","JOB_NO":"12","INS_USER_NO":"6","INS_TIME":"17:21:14","INS_DATE":"31\/10\/2020","UPD_USER_NO":"6","UPD_TIME":"9:24:28 AM","UPD_DATE":"24\/01\/2021","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"35","NAME":"Rabab - Ass","JOB_NO":"2","INS_USER_NO":"6","INS_TIME":"20:12","INS_DATE":"08\/09\/2020","UPD_USER_NO":"6","UPD_TIME":"11:05:09","UPD_DATE":"01\/03\/2022","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"25","NAME":"Hamoudeh - Colorist","JOB_NO":"7","INS_USER_NO":"6","INS_TIME":"21:28","INS_DATE":"28\/07\/2020","UPD_USER_NO":"6","UPD_TIME":"10:02:17","UPD_DATE":"21\/02\/2022","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"8","NAME":"Sultan - Ass","JOB_NO":"2","INS_USER_NO":"1","INS_TIME":"12:25:15 م","INS_DATE":"29\/05\/2020","UPD_USER_NO":"6","UPD_TIME":"11:04:45","UPD_DATE":"01\/03\/2022","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"9","NAME":"Sami - Ass","JOB_NO":"2","INS_USER_NO":"1","INS_TIME":"12:25:24 م","INS_DATE":"29\/05\/2020","UPD_USER_NO":"6","UPD_TIME":"12:24","UPD_DATE":"29\/04\/2022","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"14","NAME":"Lorena - Nails","JOB_NO":"6","INS_USER_NO":"1","INS_TIME":"4:07:46 PM","INS_DATE":"06\/06\/2020","UPD_USER_NO":"6","UPD_TIME":"10:02:31 AM","UPD_DATE":"02\/11\/2020","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"15","NAME":"Maria - Nails","JOB_NO":"6","INS_USER_NO":"1","INS_TIME":"6:58:31 PM","INS_DATE":"10\/06\/2020","UPD_USER_NO":"6","UPD_TIME":"17:47","UPD_DATE":"02\/02\/2021","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"16","NAME":"Wejdan - MakeupArtist","JOB_NO":"3","INS_USER_NO":"1","INS_TIME":"6:59:47 PM","INS_DATE":"10\/06\/2020","UPD_USER_NO":"6","UPD_TIME":"14:11","UPD_DATE":"02\/05\/2021","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"19","NAME":"Anna - Nails","JOB_NO":"6","INS_USER_NO":"1","INS_TIME":"12:04","INS_DATE":"17\/06\/2020","UPD_USER_NO":"6","UPD_TIME":"09:01","UPD_DATE":"07\/11\/2020","ACTIVE":"0","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"49","NAME":"Mahmoud - Ass","JOB_NO":"2","INS_USER_NO":"6","INS_TIME":"11:04:03","INS_DATE":"01\/03\/2022","UPD_USER_NO":"6","UPD_TIME":"11:04:19","UPD_DATE":"01\/03\/2022","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""},{"CODE":"50","NAME":"Mohamad - Ass","JOB_NO":"2","INS_USER_NO":"6","INS_TIME":"11:04:34","INS_DATE":"01\/03\/2022","UPD_USER_NO":"","UPD_TIME":"","UPD_DATE":"","ACTIVE":"1","SECTION":"","COMMISSION_RATE":"","GET_COMMISSION":"","COLOR":"","ORDER_EMP":""}]
   @PrimaryKey
    @NonNull
   @SerializedName("CODE")
    @ColumnInfo(name = "CODE")
    private String CODE;
    @SerializedName("NAME")
    @ColumnInfo(name = "NAME")
    private String NAME;
    @SerializedName("JOB_NO")
    @ColumnInfo(name = "JOB_NO")
    private String JOB_NO;
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
    @SerializedName("ACTIVE")
    @ColumnInfo(name = "ACTIVE")
    private String ACTIVE;
    @SerializedName("SECTION")
    @ColumnInfo(name = "SECTION")
    private String SECTION;
    @SerializedName("COMMISSION_RATE")
    @ColumnInfo(name = "COMMISSION_RATE")
    private String COMMISSION_RATE;
    @SerializedName("GET_COMMISSION")
    @ColumnInfo(name = "GET_COMMISSION")
    private String GET_COMMISSION;
    @SerializedName("COLOR")
    @ColumnInfo(name = "COLOR")
    private String COLOR;
    @SerializedName("ORDER_EMP")
    @ColumnInfo(name = "ORDER_EMP")
    private String ORDER_EMP;


    public GetEmployModel() {
    }

    public GetEmployModel(@NonNull String CODE, String NAME, String JOB_NO, String INS_USER_NO, String INS_TIME, String INS_DATE, String UPD_USER_NO, String UPD_TIME,
                          String UPD_DATE, String ACTIVE, String SECTION, String COMMISSION_RATE, String GET_COMMISSION, String COLOR, String ORDER_EMP) {
        this.CODE = CODE;
        this.NAME = NAME;
        this.JOB_NO = JOB_NO;
        this.INS_USER_NO = INS_USER_NO;
        this.INS_TIME = INS_TIME;
        this.INS_DATE = INS_DATE;
        this.UPD_USER_NO = UPD_USER_NO;
        this.UPD_TIME = UPD_TIME;
        this.UPD_DATE = UPD_DATE;
        this.ACTIVE = ACTIVE;
        this.SECTION = SECTION;
        this.COMMISSION_RATE = COMMISSION_RATE;
        this.GET_COMMISSION = GET_COMMISSION;
        this.COLOR = COLOR;
        this.ORDER_EMP = ORDER_EMP;
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

    public String getJOB_NO() {
        return JOB_NO;
    }

    public void setJOB_NO(String JOB_NO) {
        this.JOB_NO = JOB_NO;
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

    public String getCOLOR() {
        return COLOR;
    }

    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }

    public String getORDER_EMP() {
        return ORDER_EMP;
    }

    public void setORDER_EMP(String ORDER_EMP) {
        this.ORDER_EMP = ORDER_EMP;
    }
}
