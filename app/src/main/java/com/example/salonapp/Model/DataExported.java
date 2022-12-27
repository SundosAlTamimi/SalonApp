package com.example.salonapp.Model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class DataExported {


    //{"JSN":[{"iCode":40892,"sFromTime":"10:00","sToTime"
    // :"11:30","sDuration":"01:30","sNote1":"0","sNote2":"0","sUpdUser":"0","sUpdTime":"0","dUpdDate":"03/01/2022"}]}

    private String iCode;
    private String sFromTime;
    private String sToTime;
    private String sDuration;
    private String sNote1;
    private String sNote2;
    private String sUpdUser;
    private String sUpdTime;
    private String dUpdDate;

    //"iServiceNo":"0",
    //// "sDurationService":"0","iRowNumber":"0","sInsUserNo":"0","sInsTime":"03:11","dInsDate":"03/01/2022"}]
    private String iServiceNo;
    private String sDurationService;
    private String iRowNumber;
    private String sInsUserNo;
    private String sInsTime;
    private String dInsDate;




    public DataExported() {
    }

    public DataExported(String iCode, String sFromTime, String sToTime,
                        String sDuration, String sNote1, String sNote2, String sUpdUser, String sUpdTime, String dUpdDate) {
        this.iCode = iCode;
        this.sFromTime = sFromTime;
        this.sToTime = sToTime;
        this.sDuration = sDuration;
        this.sNote1 = sNote1;
        this.sNote2 = sNote2;
        this.sUpdUser = sUpdUser;
        this.sUpdTime = sUpdTime;
        this.dUpdDate = dUpdDate;
    }

    public String getiCode() {
        return iCode;
    }

    public void setiCode(String iCode) {
        this.iCode = iCode;
    }

    public String getsFromTime() {
        return sFromTime;
    }

    public void setsFromTime(String sFromTime) {
        this.sFromTime = sFromTime;
    }

    public String getsToTime() {
        return sToTime;
    }

    public void setsToTime(String sToTime) {
        this.sToTime = sToTime;
    }

    public String getsDuration() {
        return sDuration;
    }

    public void setsDuration(String sDuration) {
        this.sDuration = sDuration;
    }

    public String getsNote1() {
        return sNote1;
    }

    public void setsNote1(String sNote1) {
        this.sNote1 = sNote1;
    }

    public String getsNote2() {
        return sNote2;
    }

    public void setsNote2(String sNote2) {
        this.sNote2 = sNote2;
    }

    public String getsUpdUser() {
        return sUpdUser;
    }

    public void setsUpdUser(String sUpdUser) {
        this.sUpdUser = sUpdUser;
    }

    public String getsUpdTime() {
        return sUpdTime;
    }

    public void setsUpdTime(String sUpdTime) {
        this.sUpdTime = sUpdTime;
    }

    public String getdUpdDate() {
        return dUpdDate;
    }

    public void setdUpdDate(String dUpdDate) {
        this.dUpdDate = dUpdDate;
    }


    public String getiServiceNo() {
        return iServiceNo;
    }

    public void setiServiceNo(String iServiceNo) {
        this.iServiceNo = iServiceNo;
    }

    public String getsDurationService() {
        return sDurationService;
    }

    public void setsDurationService(String sDurationService) {
        this.sDurationService = sDurationService;
    }

    public String getiRowNumber() {
        return iRowNumber;
    }

    public void setiRowNumber(String iRowNumber) {
        this.iRowNumber = iRowNumber;
    }

    public String getsInsUserNo() {
        return sInsUserNo;
    }

    public void setsInsUserNo(String sInsUserNo) {
        this.sInsUserNo = sInsUserNo;
    }

    public String getsInsTime() {
        return sInsTime;
    }

    public void setsInsTime(String sInsTime) {
        this.sInsTime = sInsTime;
    }

    public String getdInsDate() {
        return dInsDate;
    }

    public void setdInsDate(String dInsDate) {
        this.dInsDate = dInsDate;
    }

    public JSONObject getJSONObject2() { // for server
        JSONObject obj = new JSONObject();
        try {

            //{"JSN":[{"iCode":40892,"sFromTime":"10:00","sToTime"
            // :"11:30","sDuration":"01:30","sNote1":"0","sNote2":"0","sUpdUser":"0","sUpdTime":"0","dUpdDate":"03/01/2022"}]}
///UpdateApp?CONO=FSS&JSONSTR={"JSN":[{"iCode":40892,"sFromTime":"10:00","sToTime":"11:30","sDuration":"01:30","sNote1":"0","sNote2":"0","sUpdUser":"0","sUpdTime":"0","dUpdDate":"03/01/2022",
// "iServiceNo":"0",
// "sDurationService":"0","iRowNumber":"0","sInsUserNo":"0","sInsTime":"03:11","dInsDate":"03/01/2022"}]}

            // "iCode ": "37121 ",
            //    "sFromTime ": "13:00 ",
            //    "sToTime ": "15:15:00 ",
            //    "sDuration ": "02:15:00 ",
            //    "sNote1 ": " ",
            //    "sNote2 ": " ",
            //    "sUpdUser ": "user ",
            //    "sUpdTime ": "12:55:35 ",
            //    "dUpdDate ": "01/05/2022 ",
            //    "iServiceNo ": "398 ",
            //    "sDurationService ": "01:00 ",
            //    "iRowNumber ": "2 ",
            //    "sInsUserNo ": "6 ",
            //    "sInsTime ": "10:00 ",
            //    "dInsDate ": "01/05/2022 "

            obj.put("iCode", iCode);
            obj.put("sFromTime", sFromTime);
            obj.put("sToTime", sToTime);
            obj.put("sDuration", sDuration);
            obj.put("sNote1", sNote1);
            obj.put("sNote2", sNote2);
            obj.put("sUpdUser", sUpdUser);
            obj.put("sUpdTime", sUpdTime);
            obj.put("dUpdDate", dUpdDate);
            obj.put("iServiceNo", iServiceNo);
            obj.put("sDurationService", sDurationService);
            obj.put("iRowNumber", iRowNumber);
            obj.put("sInsUserNo", sInsUserNo);
            obj.put("sInsTime", sInsTime);
            obj.put("dInsDate", dInsDate);


        } catch (JSONException e) {
            Log.e("Tag" , "JSONException");
        }
        return obj;
    }


}



