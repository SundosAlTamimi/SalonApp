package com.example.salonapp;


import static com.example.salonapp.FirstLayoutApp.dates;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.salonapp.Model.AppoimentModel;
import com.example.salonapp.Model.GetCustomerNameTable;
import com.example.salonapp.Model.GetEmployModel;
import com.example.salonapp.Model.GetServiceModel;
import com.example.salonapp.Model.GroupsTable;
import com.example.salonapp.Model.SettingModel;
import com.example.salonapp.Model.Type;
import com.example.salonapp.Model.UserGroupModel;
import com.example.salonapp.Model.UserInfoModel;
import com.example.salonapp.RoomInterface.AppDatabase;
import com.example.salonapp.RoomInterface.UserDaoUserGroupTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ControllClass {
    public AppDatabase db;
    Context context;
    static String empNo="0",empName="null",dateTime;


    double v1,v2;
//   public List<LocationData> locationData;

    public ControllClass(Context context) {
        this.context=context;
        db = AppDatabase.getInstanceDatabase(context);
//        locationData=new ArrayList<>();


    }


//    public List<Setting> getSettingTable() {
//
//        // UserDaoCard userDao = db.itemCard();
//        return db.SettingTable().getAll();
//    }

    public String saveAppoinmentTable(List<AppoimentModel>list) {

        // UserDaoCard userDao = db.itemCard();
        if(list.size()!=0) {
            db.AppointmentTable().deleteAll();
            db.AppointmentTable().insertAll(list);
        }else {
            //Toast.makeText(context, "NoData", Toast.LENGTH_SHORT).show();
        }
        return "Save Successful";
    }

    public String DeleteAppoinmentTable() {

        // UserDaoCard userDao = db.itemCard();

            db.AppointmentTable().deleteAll();

        return "delete Successful";
    }

    public List<AppoimentModel> getAppointment() {

        // UserDaoCard userDao = db.itemCard();
        return db.AppointmentTable().getAll();
    }

    public List<GetEmployModel> getEmployList() {

        // UserDaoCard userDao = db.itemCard();
        return db.daoEmployTable().getEmployByWork();
    }


    public List<AppoimentModel> getAppointmentByUser(String employNo) {

        // UserDaoCard userDao = db.itemCard();
        return db.AppointmentTable().getAppimint(employNo);
    }


    String getDay(){
        Date currentTimeAndDate = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String today = df.format(currentTimeAndDate);
        return convertToEnglish(today);
    }

    String getTime(){
        Date currentTimeAndDate = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String today = df.format(currentTimeAndDate);
        return convertToEnglish(today);
    }

    public String saveUserInfoTable(List<UserInfoModel>list) {

        // UserDaoCard userDao = db.itemCard();
        if(list.size()!=0) {
            db.daoUserInfoTable().deleteAll();
            db.daoUserInfoTable().insertAll(list);
        }else {
         //   Toast.makeText(context, "NoData", Toast.LENGTH_SHORT).show();
        }
        return "Save Successful";
    }

    public List<UserInfoModel> getUserInfoTable() {

        // UserDaoCard userDao = db.itemCard();
        return db.daoUserInfoTable().getAll();
    }


    public String saveUserGroupTable(List<UserGroupModel>list) {

        // UserDaoCard userDao = db.itemCard();
        if(list.size()!=0) {
            db.daoUserGroupTable().deleteAll();
            db.daoUserGroupTable().insertAll(list);
        }else {
         //   Toast.makeText(context, "NoData", Toast.LENGTH_SHORT).show();
        }
        return "Save Successful";
    }

    public List<UserInfoModel> getUserGroupTable() {

        // UserDaoCard userDao = db.itemCard();
        return db.daoUserInfoTable().getAll();
    }
    public List<UserInfoModel> getUser(String user,String pass) {

        return db.daoUserInfoTable().getUserInfo(user,pass);
    }


    public String saveGroupsTable(List<GroupsTable>list) {

        // UserDaoCard userDao = db.itemCard();
        if(list.size()!=0) {
            db.daoGroupTable().deleteAll();
            db.daoGroupTable().insertAll(list);
        }else {
        //    Toast.makeText(context, "NoData", Toast.LENGTH_SHORT).show();
        }
        return "Save Successful";
    }

    public List<GroupsTable> getGroupsTable() {

        // UserDaoCard userDao = db.itemCard();
        return db.daoGroupTable().getAll();
    }

    public List<GetEmployModel> getEmployModels() {

        // UserDaoCard userDao = db.itemCard();
        return db.daoEmployTable().getAll();
    }

    public List<GetEmployModel> getEmployName(String empNo) {

        // UserDaoCard userDao = db.itemCard();
        return db.daoEmployTable().getEmployByNo(empNo);
    }

    public List<GetCustomerNameTable> getCustomerName(String custNo) {

        // UserDaoCard userDao = db.itemCard();
        return db.userDaoCustomer().getCustomerByNo(custNo);
    }

    public List<String> getType() {

        // UserDaoCard userDao = db.itemCard();

        return db.TypeTable().getName();
    }

    public String saveTypeList(List<Type>list) {

        // UserDaoCard userDao = db.itemCard();
        if(list.size()!=0) {
            db.TypeTable().deleteAll();
            db.TypeTable().insertAll(list);
        }else {
           // Toast.makeText(context, "NoData", Toast.LENGTH_SHORT).show();
        }
        return "Save Successful";
    }

    public String saveEmployTable(List<GetEmployModel>list) {

        // UserDaoCard userDao = db.itemCard();
        if(list.size()!=0) {
            db.daoEmployTable().deleteAll();
            db.daoEmployTable().insertAll(list);
        }else {
         //   Toast.makeText(context, "NoData", Toast.LENGTH_SHORT).show();
        }
        return "Save Successful";
    }


    public String saveCustomerTable(List<GetCustomerNameTable>list) {

        // UserDaoCard userDao = db.itemCard();
        if(list.size()!=0) {
            db.userDaoCustomer().deleteAll();
            db.userDaoCustomer().insertAll(list);
        }else {
         //   Toast.makeText(context, "NoData", Toast.LENGTH_SHORT).show();
        }
        return "Save Successful";
    }

    public SettingModel getSettings() {

        // UserDaoCard userDao = db.itemCard();
        return db.userDaoSetting().getAll();
    }

    public String saveSetting(SettingModel list) {


            db.userDaoSetting().deleteAll();
            db.userDaoSetting().insertUsers(list);

        return "Save Successful";
    }



    String TimeDialog(final TextView itemExpDate) {

        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog mDatePicker;
        mDatePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {

                selectedMonth = selectedMonth + 1;
                dates ="" + selectedDay + "/" + selectedMonth + "/" + selectedYear;
                itemExpDate.setText(("" + selectedDay + "/" + selectedMonth + "/" + selectedYear));
//                tempText.setText(("" + selectedDay + "/" + selectedMonth + "/" + selectedYear));

            }
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select Date");
        mDatePicker.show();

        return convertToEnglish(dates);

    }

    public String convertToEnglish(String value) {
        String newValue = (((((((((((value + "").replaceAll("١", "1")).replaceAll("٢", "2")).replaceAll("٣", "3")).replaceAll("٤", "4")).replaceAll("٥", "5")).replaceAll("٦", "6")).replaceAll("٧", "7")).replaceAll("٨", "8")).replaceAll("٩", "9")).replaceAll("٠", "0").replaceAll("٫","."));
        return newValue;
    }

//    public String SaveStatusTable(DataStatus setting) {
//
//        // UserDaoCard userDao = db.itemCard();
//        db.dataStatusTable().deleteAll();
//        db.dataStatusTable().insertUsers(setting);
//        db.daoTransactionTable().UpdateShow(setting.getMotionNo(),"1");
//        return "Save Successful";
//    }
//
//    public String getDateStatusTable() {
//
//        // UserDaoCard userDao = db.itemCard();
//
//        List<DataStatus>list=db.dataStatusTable().getAll();
//        String date="";
//        if (list.size()!=0){
//            date=list.get(0).getDate();
//        }else{
//            date="00/00/0000";
//        }
//        return date;
//    }

//    public String deleteStatusTable() {
//
//        db.dataStatusTable().deleteAll();
//        return "delete Successful";
//    }
//
//    public String UpdateTransactionTable() {
//
//        db.daoTransactionTable().UpdateAllShow("0");
//        return "Save Successful";
//    }
//
//    String getDate(){
//
//        Calendar calendar = Calendar.getInstance();
////date format is:  "Date-Month-Year Hour:Minutes am/pm"
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Date and time
//        String currentDate = sdf.format(calendar.getTime());
//        SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
//        Date date = new Date();
//        String dayName = sdf_.format(date);
//        dates=currentDate;
//        return dayName.toUpperCase()+"    "+currentDate;
//    }


//
//    public void getLoc() {
//       LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        String locationProvider = LocationManager.NETWORK_PROVIDER;
//        // I suppressed the missing-permission warning because this wouldn't be executed in my
//        // case without location services being enabled
//        //  @SuppressLint("MissingPermission")
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//
//           // sweetMassage("Please Open Location Permission");
//
//            if (ContextCompat.checkSelfPermission(context,
//                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
//                        Manifest.permission.ACCESS_FINE_LOCATION)) {
//                    ActivityCompat.requestPermissions((Activity) context,
//                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//                } else {
//                    ActivityCompat.requestPermissions((Activity) context,
//                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//                }
//            }
//
//            return;
//        }
//        android.location.Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
//
//        try {
//            v1 = lastKnownLocation.getLatitude();
//            v2 = lastKnownLocation.getLongitude();
//        } catch (Exception e) {
//        }
//
////        LatLng latLng = new LatLng(v1, v2);
//        Log.e("LocationLanLag", "  loo");
//        Log.e("LocationLanLag", "  n  " + v1 + "   " + v2);
//        LocationData locationDatas=new LocationData(v1,v2);
//        locationData.add(locationDatas);
//
//    }
//
//    public boolean isShow(String transeKind) {
//
//       List<TransactionTable>list= db.daoTransactionTable().GetIfShow(transeKind);
//        if(list.size()!=0) {
//          if(list.get(0).getShow().equals("1")){
//              return true;
//          }else {
//              return false;
//          }
//        }else {
//            return false;
//        }
//    }
//
//    public void sweetMassage(String titleText){
//        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                .setTitleText(titleText)
//                .setContentText("")
////                            .setCancelButton("cancel", new SweetAlertDialog.OnSweetClickListener() {
////                                @Override
////                                public void onClick(SweetAlertDialog sweetAlertDialog) {
////                                    sweetAlertDialog.dismissWithAnimation();
////                                }
////                            })
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//
//                        sweetAlertDialog.dismissWithAnimation();
//
//
//                    }
//                })
//                .show();
//    }
//
//    public void sweetMassageAlert(String titleText){
//        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                .setTitleText(titleText)
//                .setContentText("")
////                            .setCancelButton("cancel", new SweetAlertDialog.OnSweetClickListener() {
////                                @Override
////                                public void onClick(SweetAlertDialog sweetAlertDialog) {
////                                    sweetAlertDialog.dismissWithAnimation();
////                                }
////                            })
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//
//                        sweetAlertDialog.dismissWithAnimation();
//
//
//                    }
//                })
//                .show();
//    }
//    public void sweetMassageSuccess(String titleText){
//        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
//                .setTitleText(titleText)
//                .setContentText("")
////                            .setCancelButton("cancel", new SweetAlertDialog.OnSweetClickListener() {
////                                @Override
////                                public void onClick(SweetAlertDialog sweetAlertDialog) {
////                                    sweetAlertDialog.dismissWithAnimation();
////                                }
////                            })
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//
//                        sweetAlertDialog.dismissWithAnimation();
//
//
//                    }
//                })
//                .show();
//    }
//
//    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
//        try {
//            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }





}
