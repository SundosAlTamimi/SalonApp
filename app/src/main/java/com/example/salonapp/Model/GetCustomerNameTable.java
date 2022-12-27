package com.example.salonapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "CUSTOMER_NAME_TABLE")
public class GetCustomerNameTable {

    //[{"CODE":"1","FULL_NAME":"ليليان   محيسن","PHONE":"0799214058"},{"CODE":"2","FULL_NAME":"ali   abutouq","PHONE":"0790606791"}]
   @PrimaryKey
   @NonNull
   @SerializedName("CODE")
   @ColumnInfo(name = "CODE")
    private String CODE;
    @SerializedName("FULL_NAME")
    @ColumnInfo(name = "FULL_NAME")
    private  String FULL_NAME;
    @SerializedName("PHONE")
    @ColumnInfo(name = "PHONE")
    private  String PHONE;

    public GetCustomerNameTable() {

    }

    public GetCustomerNameTable(@NonNull String CODE, String FULL_NAME, String PHONE) {
        this.CODE = CODE;
        this.FULL_NAME = FULL_NAME;
        this.PHONE = PHONE;
    }

    @NonNull
    public String getCODE() {
        return CODE;
    }

    public void setCODE(@NonNull String CODE) {
        this.CODE = CODE;
    }

    public String getFULL_NAME() {
        return FULL_NAME;
    }

    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }
}
