package com.example.salonapp.DataServer;


import static com.example.salonapp.AppendActivity.isClose;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import com.example.salonapp.AppendActivity;
import com.example.salonapp.ControllClass;
import com.example.salonapp.FirstLayoutApp;
import com.example.salonapp.Model.AppoimentModel;
import com.example.salonapp.Model.CustomerInfoModel;
import com.example.salonapp.Model.GetAppotmentDetales;
import com.example.salonapp.Model.GetCustomerNameTable;
import com.example.salonapp.Model.GetEmployModel;
import com.example.salonapp.Model.GetServiceModel;
import com.example.salonapp.Model.GroupsTable;
import com.example.salonapp.Model.SettingModel;
import com.example.salonapp.Model.UserGroupModel;
import com.example.salonapp.Model.UserInfoModel;
import com.example.salonapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class importJson {

    private Context context;
    private ProgressDialog progressDialog;
    private ProgressDialog progressDialogSave;
    private JSONObject obj;
    String itemCode;
    String JsonResponseSave;
    ControllClass controllClass;
    String groupNo="0";
    String fromD="",toD="";
    SweetAlertDialog pd;

    String ip = "10.0.0.121:8088", CoNo = "FSS",N1="0",custNo="0";

    List<AppoimentModel> appoimentModelList;
    List<UserInfoModel> userInfoTable;
    List<UserGroupModel> userGroupModel;
    List<GroupsTable> groupList;
    List<GetServiceModel> servicesList;
    List<GetEmployModel> EmployList;
    List<GetCustomerNameTable> customerList;
    List<GetAppotmentDetales> appoimentDetail;
    List<com.example.salonapp.Model.Type>typeList;
    List<CustomerInfoModel> customerInfo;
    final SettingModel mainSettings;
    private int inAp=0;

    public importJson(Context context) {//, JSONObject obj
        this.context = context;
        appoimentModelList = new ArrayList<>();
        userInfoTable = new ArrayList<>();
        userGroupModel=new ArrayList<>();
        groupList=new ArrayList<>();
        controllClass = new ControllClass(context);
        servicesList=new ArrayList<>();
        customerInfo=new ArrayList<>();
        EmployList=new ArrayList<>();
        typeList=new ArrayList<>();
        appoimentDetail=new ArrayList<>();
        customerList=new ArrayList<>();
         mainSettings = controllClass.getSettings();

        if (mainSettings != null) {
            ip = mainSettings.getIp();
            CoNo=mainSettings.getCono();
        }

    }

    public void getAll(String date) {
        if (mainSettings != null) {
            this.fromD = date;
            this.toD = date;
            this.inAp=0;
            new GetType().execute();
            new GetAppointment().execute();
            new GetUserInfo().execute();
            new GetUserGroup().execute();
            new GetGroups().execute();
            new GetEmploy().execute();
            new GetAllCustomer().execute();
        }else {
            Toast.makeText(context, "Please Add Setting", Toast.LENGTH_LONG).show();
        }
    }

    public  void getServiceByGroup(String groupNo){
        if(mainSettings !=null) {
            this.groupNo = groupNo;
            new GetService().execute();
        }else {
            Toast.makeText(context, "Please Add Setting", Toast.LENGTH_LONG).show();
        }
    }

    public  void getAppDetailsByGroup(String groupNo){
        if(mainSettings !=null) {
            this.N1 = groupNo;
            new GetAppoinmentDetail().execute();
        }else {
            Toast.makeText(context, "Please Add Setting", Toast.LENGTH_LONG).show();
        }
    }

    public void getType() {
        if(mainSettings!=null) {
            new GetType().execute();
        }else {
            Toast.makeText(context, " Please Add Before", Toast.LENGTH_LONG).show();

        }
    }

    public void getCustomer(String custNo){
        if(mainSettings !=null) {

            this.custNo = custNo;
            new GetCustomer().execute();
        }else {
            Toast.makeText(context, " Please Add Setting", Toast.LENGTH_LONG).show();

        }

    }

    public void getAppointment(String date) {
        if(mainSettings!=null) {
            this.fromD = date;
            this.toD = date;
            this.inAp=1;
            new GetAppointment().execute();
        }else {
            Toast.makeText(context, "Please Add Setting", Toast.LENGTH_LONG).show();

        }
    }

    private class GetType extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();
//            pd = ProgressDialog.show(context, "title", "loading", true);
//            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
//            pd.setTitleText(context.getResources().getString("Import Data"));


            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {
Log.e("GetType1","in");
                String link = "http://"+ip+"/GetType".trim();

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8");

                URL url = new URL(link);
                Log.e("urlStringCard = ", "" + url.toString() + "?" + data);
                Log.e("GetType1",""+url.toString()+"?"+data);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");


                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                Log.e("GetType1","String");

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.e("GetType1",""+stringBuffer.toString());

                // Log.e("tag", "ItemOCode -->" + stringBuffer.toString());

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

//              JsonResponse = "[{\"CODE\":\"1\",\"NAME\":\"Hair Service\",\"COLOR\":\"clGreen\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"21:17\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"17:46\",\"UPD_DATE\":\"09\\/06\\/2020\",\"REQUEST\":\"0\",\"DONE\":\"0\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"0\",\"IN_PROCESS\":\"0\",\"END_PROCESS\":\"0\"},{\"CODE\":\"2\",\"NAME\":\"Makeup\",\"COLOR\":\"clNavy\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"21:18\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"REQUEST\":\"0\",\"DONE\":\"0\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"0\",\"IN_PROCESS\":\"0\",\"END_PROCESS\":\"0\"},{\"CODE\":\"3\",\"NAME\":\"Treatment\",\"COLOR\":\"clOlive\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"21:19\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"REQUEST\":\"0\",\"DONE\":\"0\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"0\",\"IN_PROCESS\":\"0\",\"END_PROCESS\":\"0\"},{\"CODE\":\"4\",\"NAME\":\"Tattoo\",\"COLOR\":\"clPurple\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"21:20\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"REQUEST\":\"0\",\"DONE\":\"0\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"0\",\"IN_PROCESS\":\"0\",\"END_PROCESS\":\"0\"},{\"CODE\":\"5\",\"NAME\":\"Done\",\"COLOR\":\"clRed\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"10:52\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"14:56\",\"UPD_DATE\":\"12\\/06\\/2020\",\"REQUEST\":\"0\",\"DONE\":\"1\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"0\",\"IN_PROCESS\":\"0\",\"END_PROCESS\":\"0\"},{\"CODE\":\"11\",\"NAME\":\"Lashes\",\"COLOR\":\"clPurple\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"16:35\",\"INS_DATE\":\"22\\/06\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"16:38\",\"UPD_DATE\":\"22\\/06\\/2020\",\"REQUEST\":\"0\",\"DONE\":\"0\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"0\",\"IN_PROCESS\":\"0\",\"END_PROCESS\":\"0\"},{\"CODE\":\"7\",\"NAME\":\"Padicare and Manicare\",\"COLOR\":\"clGray\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"10:55\",\"INS_DATE\":\"04\\/06\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"20:23\",\"UPD_DATE\":\"08\\/07\\/2020\",\"REQUEST\":\"0\",\"DONE\":\"0\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"0\",\"IN_PROCESS\":\"0\",\"END_PROCESS\":\"0\"},{\"CODE\":\"14\",\"NAME\":\"Skincare\",\"COLOR\":\"clMedGray\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"10:50\",\"INS_DATE\":\"04\\/09\\/2020\",\"UPD_USER_NO\":\"2\",\"UPD_TIME\":\"13:38\",\"UPD_DATE\":\"18\\/10\\/2020\",\"REQUEST\":\"0\",\"DONE\":\"0\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"0\",\"IN_PROCESS\":\"0\",\"END_PROCESS\":\"0\"},{\"CODE\":\"8\",\"NAME\":\"Wax and Body Care\",\"COLOR\":\"clBlue\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"17:42\",\"INS_DATE\":\"06\\/06\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"18:31\",\"UPD_DATE\":\"07\\/06\\/2020\",\"REQUEST\":\"0\",\"DONE\":\"0\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"0\",\"IN_PROCESS\":\"0\",\"END_PROCESS\":\"0\"},{\"CODE\":\"6\",\"NAME\":\"Delete\",\"COLOR\":\"clBlack\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"14:55:10\",\"INS_DATE\":\"02\\/06\\/2020\",\"UPD_USER_NO\":\"2\",\"UPD_TIME\":\"12:44\",\"UPD_DATE\":\"03\\/06\\/2020\",\"REQUEST\":\"0\",\"DONE\":\"0\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"1\",\"IN_PROCESS\":\"0\",\"END_PROCESS\":\"0\"},{\"CODE\":\"10\",\"NAME\":\"Nail Service\",\"COLOR\":\"clSkyBlue\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"14:57\",\"INS_DATE\":\"12\\/06\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"REQUEST\":\"0\",\"DONE\":\"0\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"0\",\"IN_PROCESS\":\"0\",\"END_PROCESS\":\"0\"},{\"CODE\":\"12\",\"NAME\":\"In Process\",\"COLOR\":\"clFuchsia\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"17:33\",\"INS_DATE\":\"22\\/07\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"REQUEST\":\"0\",\"DONE\":\"0\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"0\",\"IN_PROCESS\":\"1\",\"END_PROCESS\":\"0\"},{\"CODE\":\"13\",\"NAME\":\"End Process\",\"COLOR\":\"clHighlight\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"17:33\",\"INS_DATE\":\"22\\/07\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"REQUEST\":\"0\",\"DONE\":\"0\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"0\",\"IN_PROCESS\":\"0\",\"END_PROCESS\":\"1\"},{\"CODE\":\"9\",\"NAME\":\"Hair Extension\",\"COLOR\":\"clLime\",\"LEAVE\":\"0\",\"BREAKS\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"18:48\",\"INS_DATE\":\"06\\/06\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"REQUEST\":\"0\",\"DONE\":\"0\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"IS_DELETE\":\"0\",\"IN_PROCESS\":\"0\",\"END_PROCESS\":\"0\"}]";
            Log.e("GetType1","json");

        if (JsonResponse != null && JsonResponse.contains("CODE")) {
                Log.e("tag_ItemOCode", "****Success");
            Log.e("GetType1",""+JsonResponse);

                JsonResponseSave = JsonResponse;

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<com.example.salonapp.Model.Type>>() {
                }.getType();
                Collection<com.example.salonapp.Model.Type> enums = gson.fromJson(JsonResponse, collectionType);
                typeList.clear();
            typeList = (List<com.example.salonapp.Model.Type>) enums;

                controllClass.saveTypeList(typeList);
      //          Toast.makeText(context, "save", Toast.LENGTH_LONG).show();

            } else if (JsonResponse != null && JsonResponse.contains("No Data Found.")) {
                // new SyncItemSwitch().execute();
                // Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();
            Log.e("GetType1","nodatafound");

            } else {
                Log.e("tag_itemCard", "****Failed to export data");
//                Log.e("tag_itemCard", "****"+JsonResponse.toString());
                //   Toast.makeText(context, "faild export"+ JsonResponse.toString(), Toast.LENGTH_LONG).show();

//                Toast.makeText(context, "Failed to Get data", Toast.LENGTH_SHORT).show();
//                if(pd!=null) {
//                    pd.dismiss();
//                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText(context.getResources().getString(R.string.ops))
//                            .setContentText(context.getResources().getString(R.string.fildtoimportitemswitch))
//                            .show();
//                }
            Log.e("GetType1","faild");


            }

        }
    }

    private class GetAppointment extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();
            if(inAp==1) {
                Log.e("GetAppointmen1010","InSwwet");

                pd = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
                pd.setTitleText(context.getResources().getString(R.string.getAppo));
           pd.show();
            }

            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Log.e("GetAppointmen","in");

                String link = "http://" + ip + "/GetAppointment?".trim();
//D1=01/05/2022&D2=01/09/2022
                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8")+"&"+
                        "D1=" + URLEncoder.encode(fromD, "UTF-8")+"&"+
                        "D2=" + URLEncoder.encode(toD, "UTF-8");

                URL url = new URL(link + data);
                Log.e("importEmploy1 = ", "" + url.toString());

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("GET");

                Log.e("importEmploy2 = ", "in");
//
//                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
//                wr.writeBytes(data);
//                wr.flush();
//                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }
                Log.e("GetAppointmen66",""+bufferedReader.toString());

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                // Log.e("tag", "ItemOCode -->" + stringBuffer.toString());
                try {
                    Log.e("GetAppointmen55 = ", "" + stringBuffer.toString());
                } catch (Exception e) {
                    Log.e("GetAppointmen44 = ", "Error String");

                }
                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("GetAppointmen33 = ", "" + e.toString());

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
                Log.e("GetAppointmen = ", "Error");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

//              JsonResponse = "[{\"CODE\":\"38915\",\"TODAY\":\"30\\/05\\/2022\",\"FROM_TIME\":\"14:30\",\"TO_TIME\":\"16:00\",\"DURATION\":\"01:30\",\"EMPLOYEE_NO\":\"10\",\"TYPE_NO\":\"5\",\"TYPE_COLOR\":\"clRed\",\"TYPE_DURATION\":\"00:00\",\"CUSTOMER_NO\":\"9229\",\"NOTE_1\":\"\",\"NOTE_2\":\"\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"14:06\",\"INS_DATE\":\"30\\/05\\/2022\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"DONE\":\"0\",\"OLD_TYPE_NO\":\"2\",\"OLD_TYPE_COLOR\":\"clNavy\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"OLD_TYPE_NO_IN\":\"0\",\"OLD_TYPE_COLOR_IN\":\"\",\"OLD_TYPE_NO_OUT\":\"0\",\"OLD_TYPE_COLOR_OUT\":\"\",\"IS_DELETE\":\"0\",\"DELETE_NOTE\":\"\",\"DONE_TIME\":\"14:07\",\"DONE_DATE\":\"30\\/05\\/2022\",\"IN_PROCESS\":\"0\",\"IN_PROCESS_TIME\":\"\",\"IN_PROCESS_DATE\":\"\",\"END_PROCESS\":\"0\",\"END_PROCESS_TIME\":\"\",\"END_PROCESS_DATE\":\"\",\"DONE_USER_NO\":\"6\",\"IN_PROCESS_USER_NO\":\"\",\"END_PROCESS_USER_NO\":\"\",\"DIVISION\":\"\",\"IS_OPENED\":\"\"},{\"CODE\":\"3814\",\"TODAY\":\"30\\/05\\/2022\",\"FROM_TIME\":\"14:30\",\"TO_TIME\":\"16:00\",\"DURATION\":\"01:30\",\"EMPLOYEE_NO\":\"10\",\"TYPE_NO\":\"5\",\"TYPE_COLOR\":\"clRed\",\"TYPE_DURATION\":\"00:00\",\"CUSTOMER_NO\":\"9229\",\"NOTE_1\":\"\",\"NOTE_2\":\"\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"14:06\",\"INS_DATE\":\"30\\/05\\/2022\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"DONE\":\"1\",\"OLD_TYPE_NO\":\"2\",\"OLD_TYPE_COLOR\":\"clNavy\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"OLD_TYPE_NO_IN\":\"0\",\"OLD_TYPE_COLOR_IN\":\"\",\"OLD_TYPE_NO_OUT\":\"0\",\"OLD_TYPE_COLOR_OUT\":\"\",\"IS_DELETE\":\"0\",\"DELETE_NOTE\":\"\",\"DONE_TIME\":\"14:07\",\"DONE_DATE\":\"30\\/05\\/2022\",\"IN_PROCESS\":\"0\",\"IN_PROCESS_TIME\":\"\",\"IN_PROCESS_DATE\":\"\",\"END_PROCESS\":\"0\",\"END_PROCESS_TIME\":\"\",\"END_PROCESS_DATE\":\"\",\"DONE_USER_NO\":\"6\",\"IN_PROCESS_USER_NO\":\"\",\"END_PROCESS_USER_NO\":\"\",\"DIVISION\":\"\",\"IS_OPENED\":\"\"},{\"CODE\":\"38914\",\"TODAY\":\"30\\/05\\/2022\",\"FROM_TIME\":\"14:30\",\"TO_TIME\":\"16:00\",\"DURATION\":\"01:30\",\"EMPLOYEE_NO\":\"10\",\"TYPE_NO\":\"5\",\"TYPE_COLOR\":\"clRed\",\"TYPE_DURATION\":\"00:00\",\"CUSTOMER_NO\":\"9229\",\"NOTE_1\":\"\",\"NOTE_2\":\"\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"14:06\",\"INS_DATE\":\"30\\/05\\/2022\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"DONE\":\"1\",\"OLD_TYPE_NO\":\"2\",\"OLD_TYPE_COLOR\":\"clNavy\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"OLD_TYPE_NO_IN\":\"0\",\"OLD_TYPE_COLOR_IN\":\"\",\"OLD_TYPE_NO_OUT\":\"0\",\"OLD_TYPE_COLOR_OUT\":\"\",\"IS_DELETE\":\"0\",\"DELETE_NOTE\":\"\",\"DONE_TIME\":\"14:07\",\"DONE_DATE\":\"30\\/05\\/2022\",\"IN_PROCESS\":\"0\",\"IN_PROCESS_TIME\":\"\",\"IN_PROCESS_DATE\":\"\",\"END_PROCESS\":\"0\",\"END_PROCESS_TIME\":\"\",\"END_PROCESS_DATE\":\"\",\"DONE_USER_NO\":\"6\",\"IN_PROCESS_USER_NO\":\"\",\"END_PROCESS_USER_NO\":\"\",\"DIVISION\":\"\",\"IS_OPENED\":\"\"},{\"CODE\":\"38914\",\"TODAY\":\"30\\/05\\/2022\",\"FROM_TIME\":\"14:30\",\"TO_TIME\":\"16:00\",\"DURATION\":\"01:30\",\"EMPLOYEE_NO\":\"10\",\"TYPE_NO\":\"5\",\"TYPE_COLOR\":\"clRed\",\"TYPE_DURATION\":\"00:00\",\"CUSTOMER_NO\":\"9229\",\"NOTE_1\":\"\",\"NOTE_2\":\"\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"14:06\",\"INS_DATE\":\"30\\/05\\/2022\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"DONE\":\"1\",\"OLD_TYPE_NO\":\"2\",\"OLD_TYPE_COLOR\":\"clNavy\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"OLD_TYPE_NO_IN\":\"0\",\"OLD_TYPE_COLOR_IN\":\"\",\"OLD_TYPE_NO_OUT\":\"0\",\"OLD_TYPE_COLOR_OUT\":\"\",\"IS_DELETE\":\"0\",\"DELETE_NOTE\":\"\",\"DONE_TIME\":\"14:07\",\"DONE_DATE\":\"30\\/05\\/2022\",\"IN_PROCESS\":\"0\",\"IN_PROCESS_TIME\":\"\",\"IN_PROCESS_DATE\":\"\",\"END_PROCESS\":\"0\",\"END_PROCESS_TIME\":\"\",\"END_PROCESS_DATE\":\"\",\"DONE_USER_NO\":\"6\",\"IN_PROCESS_USER_NO\":\"\",\"END_PROCESS_USER_NO\":\"\",\"DIVISION\":\"\",\"IS_OPENED\":\"\"},{\"CODE\":\"40819\",\"TODAY\":\"02\\/07\\/2022\",\"FROM_TIME\":\"13:00\",\"TO_TIME\":\"14:00\",\"DURATION\":\"01:00\",\"EMPLOYEE_NO\":\"43\",\"TYPE_NO\":\"7\",\"TYPE_COLOR\":\"clGray\",\"TYPE_DURATION\":\"00:00\",\"CUSTOMER_NO\":\"8765\",\"NOTE_1\":\"\",\"NOTE_2\":\"\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"12:22\",\"INS_DATE\":\"26\\/06\\/2022\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"DONE\":\"0\",\"OLD_TYPE_NO\":\"0\",\"OLD_TYPE_COLOR\":\"\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"OLD_TYPE_NO_IN\":\"0\",\"OLD_TYPE_COLOR_IN\":\"\",\"OLD_TYPE_NO_OUT\":\"0\",\"OLD_TYPE_COLOR_OUT\":\"\",\"IS_DELETE\":\"0\",\"DELETE_NOTE\":\"\",\"DONE_TIME\":\"\",\"DONE_DATE\":\"\",\"IN_PROCESS\":\"0\",\"IN_PROCESS_TIME\":\"\",\"IN_PROCESS_DATE\":\"\",\"END_PROCESS\":\"0\",\"END_PROCESS_TIME\":\"\",\"END_PROCESS_DATE\":\"\",\"DONE_USER_NO\":\"\",\"IN_PROCESS_USER_NO\":\"\",\"END_PROCESS_USER_NO\":\"\",\"DIVISION\":\"\",\"IS_OPENED\":\"\"},{\"CODE\":\"37455\",\"TODAY\":\"13\\/05\\/2022\",\"FROM_TIME\":\"16:30\",\"TO_TIME\":\"17:00\",\"DURATION\":\"00:30\",\"EMPLOYEE_NO\":\"41\",\"TYPE_NO\":\"5\",\"TYPE_COLOR\":\"clRed\",\"TYPE_DURATION\":\"00:00\",\"CUSTOMER_NO\":\"5182\",\"NOTE_1\":\"\",\"NOTE_2\":\"\",\"INS_USER_NO\":\"4\",\"INS_TIME\":\"12:54\",\"INS_DATE\":\"07\\/05\\/2022\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"17:26\",\"UPD_DATE\":\"13\\/05\\/2022\",\"DONE\":\"1\",\"OLD_TYPE_NO\":\"12\",\"OLD_TYPE_COLOR\":\"clFuchsia\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"OLD_TYPE_NO_IN\":\"0\",\"OLD_TYPE_COLOR_IN\":\"\",\"OLD_TYPE_NO_OUT\":\"0\",\"OLD_TYPE_COLOR_OUT\":\"\",\"IS_DELETE\":\"0\",\"DELETE_NOTE\":\"\",\"DONE_TIME\":\"17:43\",\"DONE_DATE\":\"13\\/05\\/2022\",\"IN_PROCESS\":\"1\",\"IN_PROCESS_TIME\":\"15:08\",\"IN_PROCESS_DATE\":\"13\\/05\\/2022\",\"END_PROCESS\":\"0\",\"END_PROCESS_TIME\":\"\",\"END_PROCESS_DATE\":\"\",\"DONE_USER_NO\":\"6\",\"IN_PROCESS_USER_NO\":\"6\",\"END_PROCESS_USER_NO\":\"\",\"DIVISION\":\"\",\"IS_OPENED\":\"\"},{\"CODE\":\"35183\",\"TODAY\":\"08\\/05\\/2022\",\"FROM_TIME\":\"10:30\",\"TO_TIME\":\"11:50\",\"DURATION\":\"01:20\",\"EMPLOYEE_NO\":\"26\",\"TYPE_NO\":\"5\",\"TYPE_COLOR\":\"clRed\",\"TYPE_DURATION\":\"00:00\",\"CUSTOMER_NO\":\"2560\",\"NOTE_1\":\"00966538256060\",\"NOTE_2\":\"\",\"INS_USER_NO\":\"4\",\"INS_TIME\":\"13:13\",\"INS_DATE\":\"07\\/05\\/2022\",\"UPD_USER_NO\":\"4\",\"UPD_TIME\":\"18:32\",\"UPD_DATE\":\"08\\/05\\/2022\",\"DONE\":\"1\",\"OLD_TYPE_NO\":\"12\",\"OLD_TYPE_COLOR\":\"clFuchsia\",\"CHECK_IN\":\"0\",\"CHECK_OUT\":\"0\",\"OLD_TYPE_NO_IN\":\"0\",\"OLD_TYPE_COLOR_IN\":\"\",\"OLD_TYPE_NO_OUT\":\"0\",\"OLD_TYPE_COLOR_OUT\":\"\",\"IS_DELETE\":\"0\",\"DELETE_NOTE\":\"\",\"DONE_TIME\":\"18:33\",\"DONE_DATE\":\"08\\/05\\/2022\",\"IN_PROCESS\":\"1\",\"IN_PROCESS_TIME\":\"10:31\",\"IN_PROCESS_DATE\":\"08\\/05\\/2022\",\"END_PROCESS\":\"0\",\"END_PROCESS_TIME\":\"\",\"END_PROCESS_DATE\":\"\",\"DONE_USER_NO\":\"4\",\"IN_PROCESS_USER_NO\":\"2\",\"END_PROCESS_USER_NO\":\"\",\"DIVISION\":\"\",\"IS_OPENED\":\"\"}]";

            if (JsonResponse != null && JsonResponse.contains("CODE")) {
                Log.e("iGetAppointmen7 = ", "Successful");

                JsonResponseSave = JsonResponse;

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<AppoimentModel>>() {
                }.getType();
                Collection<AppoimentModel> enums = gson.fromJson(JsonResponse, collectionType);
                appoimentModelList.clear();
                appoimentModelList = (List<AppoimentModel>) enums;

                controllClass.saveAppoinmentTable(appoimentModelList);
                Toast.makeText(context, "Please Select Employ", Toast.LENGTH_SHORT).show();

                try{

                    FirstLayoutApp appendActivity=(FirstLayoutApp) context;
                    appendActivity.refreshList(appoimentModelList);

                }catch (Exception e){
                    Log.e("GetAppointmen1010","eRROR"+e.toString());
                }


                try{


                    AppendActivity appendActivity=(AppendActivity) context;
                    isClose=false;
                    appendActivity.refreshFirstLayout();

                }catch (Exception e){
                    Log.e("GetAppointmen1020","eRROR"+e.toString());
                }

                try {
                    if (inAp == 1) {
                        pd.dismiss();
                    }
                }catch (Exception e){}


            } else if (JsonResponse != null && JsonResponse.contains("No Data Found.")) {
                // new SyncItemSwitch().execute();
                // Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();
                Log.e("GetAppointmen8 = ", "no data");
                controllClass.DeleteAppoinmentTable();
                try{

                    FirstLayoutApp appendActivity=(FirstLayoutApp) context;
                    appendActivity.refresh(appendActivity.getResources().getString(R.string.noData));

                }catch (Exception e){
                    Log.e("GetAppointmen1000","eRROR"+e.toString());
                }


                try{


                    AppendActivity appendActivity=(AppendActivity) context;
                    isClose=false;
                    appendActivity.refreshFirstLayout();

                }catch (Exception e){
                    Log.e("GetAppointmen1030","eRROR"+e.toString());
                }

                try {
                    if (inAp == 1) {
                        pd.dismiss();
                    }
                }catch (Exception e){}



            } else {
                Log.e("tag_itemCard", "****Failed to export data");
                Log.e("GetAppointmen9 = ", "Failed to export data");
//                Log.e("tag_itemCard", "****"+JsonResponse.toString());
                //   Toast.makeText(context, "faild export"+ JsonResponse.toString(), Toast.LENGTH_LONG).show();

//                Toast.makeText(context, "Failed to Get data", Toast.LENGTH_SHORT).show();
//                if(pd!=null) {
//                    pd.dismiss();
//                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText(context.getResources().getString(R.string.ops))
//                            .setContentText(context.getResources().getString(R.string.fildtoimportitemswitch))
//                            .show();
//                }

                controllClass.DeleteAppoinmentTable();
                try{

                    FirstLayoutApp appendActivity=(FirstLayoutApp) context;
                    appendActivity.refresh(appendActivity.getResources().getString(R.string.fildto));

                }catch (Exception e){
                    Log.e("GetAppointmen1000","eRROR"+e.toString());
                }

                try{


                    AppendActivity appendActivity=(AppendActivity) context;
                    isClose=false;
                    appendActivity.refreshFirstLayout();

                }catch (Exception e){
                    Log.e("GetAppointmen1040","eRROR"+e.toString());
                }

                try {
                    if (inAp == 1) {
                        pd.dismiss();
                    }
                }catch (Exception e){}



            }

        }
    }

    private class GetUserInfo extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();
//            pd = ProgressDialog.show(context, "title", "loading", true);
//            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
//            pd.setTitleText(context.getResources().getString("Import Data"));


            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                String link = "http://" + ip + "/GetUser_Info?".trim();

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8");

                URL url = new URL(link + data);
                Log.e("GetUser_Info1 = ", "" + url.toString());

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("GET");

                Log.e("GetUser_Info2 = ", "in");
//
//                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
//                wr.writeBytes(data);
//                wr.flush();
//                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                // Log.e("tag", "ItemOCode -->" + stringBuffer.toString());
                try {
                    Log.e("GetUser_Info3 = ", "" + stringBuffer.toString());
                } catch (Exception e) {
                    Log.e("GetUser_Info4 = ", "Error String");

                }
                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("GetUser_Info5 = ", "" + e.toString());

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
                Log.e("iGetUser_Info6 = ", "Error");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

             // JsonResponse = "[{\"CODE\":\"1\",\"USER_NO\":\"1\",\"NAME\":\"Master\",\"PASSWORD\":\"20162012202505000\",\"DEPARTMENT_NO\":\"2\",\"GROUP_NO\":\"1\",\"ACTIVE\":\"1\",\"MASTER\":\"1\",\"INS_USER_NO\":\"10115\",\"INS_TIME\":\"01:01:44 PM\",\"INS_DATE\":\"01\\/07\\/2018\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"10:19:09 AM\",\"UPD_DATE\":\"29\\/06\\/2021\",\"SECTION\":\"\"},{\"CODE\":\"2\",\"USER_NO\":\"2\",\"NAME\":\"Lilyan\",\"PASSWORD\":\"1234\",\"DEPARTMENT_NO\":\"1\",\"GROUP_NO\":\"3\",\"ACTIVE\":\"1\",\"MASTER\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"21:10\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"11:27\",\"UPD_DATE\":\"26\\/05\\/2020\",\"SECTION\":\"\"},{\"CODE\":\"3\",\"USER_NO\":\"3\",\"NAME\":\"Ali Rantisi\",\"PASSWORD\":\"20162012202505000\",\"DEPARTMENT_NO\":\"-1\",\"GROUP_NO\":\"-1\",\"ACTIVE\":\"1\",\"MASTER\":\"1\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"10:50\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"SECTION\":\"\"},{\"CODE\":\"5\",\"USER_NO\":\"5\",\"NAME\":\"Yazan\",\"PASSWORD\":\"123123\",\"DEPARTMENT_NO\":\"1\",\"GROUP_NO\":\"3\",\"ACTIVE\":\"1\",\"MASTER\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"15:35\",\"INS_DATE\":\"09\\/07\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"15:41:12\",\"UPD_DATE\":\"09\\/07\\/2020\",\"SECTION\":\"\"},{\"CODE\":\"6\",\"USER_NO\":\"6\",\"NAME\":\"Ali Almasri\",\"PASSWORD\":\"789\",\"DEPARTMENT_NO\":\"2\",\"GROUP_NO\":\"2\",\"ACTIVE\":\"1\",\"MASTER\":\"1\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"16:14:30\",\"INS_DATE\":\"09\\/07\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"10:19:30 AM\",\"UPD_DATE\":\"29\\/06\\/2021\",\"SECTION\":\"\"},{\"CODE\":\"4\",\"USER_NO\":\"4\",\"NAME\":\"Nisreen\",\"PASSWORD\":\"1234\",\"DEPARTMENT_NO\":\"1\",\"GROUP_NO\":\"3\",\"ACTIVE\":\"1\",\"MASTER\":\"0\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"15:45:18\",\"INS_DATE\":\"31\\/05\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"15:19:50\",\"UPD_DATE\":\"09\\/07\\/2020\",\"SECTION\":\"\"}]";

            if (JsonResponse != null && JsonResponse.contains("CODE")) {
                Log.e("GetUser_Info7 = ", "Successful");

                JsonResponseSave = JsonResponse;

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<UserInfoModel>>() {
                }.getType();
                Collection<UserInfoModel> enums = gson.fromJson(JsonResponse, collectionType);
                userInfoTable.clear();
                userInfoTable = (List<UserInfoModel>) enums;

                controllClass.saveUserInfoTable(userInfoTable);
    //            Toast.makeText(context, "save", Toast.LENGTH_LONG).show();

            } else if (JsonResponse != null && JsonResponse.contains("No Data Found.")) {
                // new SyncItemSwitch().execute();
                // Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();
                Log.e("GetUser_Info8 = ", "no data");

            } else {
                Log.e("tag_itemCard", "****Failed to export data");
                Log.e("GetUser_Info9 = ", "Failed to export data");
//                Log.e("tag_itemCard", "****"+JsonResponse.toString());
                //   Toast.makeText(context, "faild export"+ JsonResponse.toString(), Toast.LENGTH_LONG).show();

//                Toast.makeText(context, "Failed to Get data", Toast.LENGTH_SHORT).show();
//                if(pd!=null) {
//                    pd.dismiss();
//                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText(context.getResources().getString(R.string.ops))
//                            .setContentText(context.getResources().getString(R.string.fildtoimportitemswitch))
//                            .show();
//                }


            }

        }
    }

    private class GetUserGroup extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();
//            pd = ProgressDialog.show(context, "title", "loading", true);
//            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
//            pd.setTitleText(context.getResources().getString("Import Data"));


            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                String link = "http://" + ip + "/GetUserGroup?".trim();

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8");

                URL url = new URL(link+data);
                Log.e("GetUserGroup1 = ", "" + url.toString());

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("GET");

                Log.e("GetUserGroup2 = ", "in" );
//
//                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
//                wr.writeBytes(data);
//                wr.flush();
//                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                // Log.e("tag", "ItemOCode -->" + stringBuffer.toString());
                try {
                    Log.e("GetUserGroup3 = ", "" + stringBuffer.toString());
                }catch (Exception e){
                    Log.e("GetUserGroup4 = ", "Error String");

                }
                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("GetUserGroup5 = ", "" + e.toString());

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
                Log.e("GetUserGroup6 = ", "Error" );
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

           // JsonResponse = "[{\"CODE\":\"1\",\"NAME\":\"Administrator\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"2:49:13 PM\",\"INS_DATE\":\"11\\/07\\/2019\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\"},{\"CODE\":\"2\",\"NAME\":\"Accounting\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"2:49:19 PM\",\"INS_DATE\":\"11\\/07\\/2019\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\"},{\"CODE\":\"3\",\"NAME\":\"Reception\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"2:49:25 PM\",\"INS_DATE\":\"11\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"02:16:42 م\",\"UPD_DATE\":\"26\\/05\\/2020\"}]";

            if (JsonResponse != null && JsonResponse.contains("CODE")) {
                Log.e("GetUserGroup7 = ", "Successful" );

                JsonResponseSave = JsonResponse;

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<UserGroupModel>>() {
                }.getType();
                Collection<UserGroupModel> enums = gson.fromJson(JsonResponse, collectionType);
                userGroupModel.clear();
                userGroupModel = (List<UserGroupModel>) enums;

                controllClass.saveUserGroupTable(userGroupModel);
     //           Toast.makeText(context, "save", Toast.LENGTH_LONG).show();

            } else if (JsonResponse != null && JsonResponse.contains("No Data Found.")) {
                // new SyncItemSwitch().execute();
               // Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();
                Log.e("GetUserGroup8 = ", "no data" );

            } else {
                Log.e("tag_itemCard", "****Failed to export data");
                Log.e("GetUserGroup9 = ", "Failed to export data" );
//                Log.e("tag_itemCard", "****"+JsonResponse.toString());
             //   Toast.makeText(context, "faild export"+ JsonResponse.toString(), Toast.LENGTH_LONG).show();

//                Toast.makeText(context, "Failed to Get data", Toast.LENGTH_SHORT).show();
//                if(pd!=null) {
//                    pd.dismiss();
//                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText(context.getResources().getString(R.string.ops))
//                            .setContentText(context.getResources().getString(R.string.fildtoimportitemswitch))
//                            .show();
//                }


            }

        }
    }

    private class GetGroups extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();
//            pd = ProgressDialog.show(context, "title", "loading", true);
//            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
//            pd.setTitleText(context.getResources().getString("Import Data"));


            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                String link = "http://" + ip + "/GetGroup?".trim();

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8");

                URL url = new URL(link+data);
                Log.e("iGetGroup1 = ", "" + url.toString());

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("GET");

                Log.e("GetGroup2 = ", "in" );
//
//                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
//                wr.writeBytes(data);
//                wr.flush();
//                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                // Log.e("tag", "ItemOCode -->" + stringBuffer.toString());
                try {
                    Log.e("GetGroup3 = ", "" + stringBuffer.toString());
                }catch (Exception e){
                    Log.e("GetGroup4 = ", "Error String");

                }
                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("GetGroup5 = ", "" + e.toString());

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
                Log.e("GetGroup6 = ", "Error" );
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

          //  JsonResponse = "[{\"CODE\":\"18\",\"NAME\":\"Men\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"12:37\",\"INS_DATE\":\"07\\/05\\/2021\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"2\",\"NAME\":\"Makeup & Lashes\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"18:28\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"3\",\"NAME\":\"Eyebrows & Upper lips\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"18:28\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"4\",\"NAME\":\"Manicure & Pedicure\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"18:28\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"5\",\"NAME\":\"Treatments\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"18:29\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"6\",\"NAME\":\"Hair Extensions\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"18:29\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"7\",\"NAME\":\"Tattoo\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"18:29\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"8\",\"NAME\":\"Bridal\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"18:29\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"9\",\"NAME\":\"Bridal Packages\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"18:29\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"10\",\"NAME\":\"Engagement\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"18:29\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"11\",\"NAME\":\"Engagement Packages\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"18:30\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"1\",\"NAME\":\"Hair Services\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"06:17\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"12\",\"NAME\":\"Sale\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"14:08\",\"INS_DATE\":\"31\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"11:04:15 PM\",\"UPD_DATE\":\"24\\/03\\/2020\",\"ACTIVE\":\"1\"},{\"CODE\":\"13\",\"NAME\":\"Waxing and Body Services\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"11:02:44 ص\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"15\",\"NAME\":\"Skin Care\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"16:29\",\"INS_DATE\":\"19\\/08\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"14\",\"NAME\":\"Product\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"10:39:29\",\"INS_DATE\":\"31\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"},{\"CODE\":\"16\",\"NAME\":\"Permenant Lashes\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"13:52\",\"INS_DATE\":\"21\\/12\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"13:52\",\"UPD_DATE\":\"21\\/12\\/2020\",\"ACTIVE\":\"1\"},{\"CODE\":\"17\",\"NAME\":\"Not Available\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"13:57\",\"INS_DATE\":\"02\\/01\\/2021\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\"}]";

            if (JsonResponse != null && JsonResponse.contains("CODE")) {
                Log.e("GetGroup7 = ", "Successful" );

                JsonResponseSave = JsonResponse;

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<GroupsTable>>() {
                }.getType();
                Collection<GroupsTable> enums = gson.fromJson(JsonResponse, collectionType);
                groupList.clear();
                groupList = (List<GroupsTable>) enums;

                controllClass.saveGroupsTable(groupList);
                Toast.makeText(context, "save", Toast.LENGTH_LONG).show();

            } else if (JsonResponse != null && JsonResponse.contains("No Data Found.")) {
                // new SyncItemSwitch().execute();
               // Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();
                Log.e("iGetGroup8 = ", "no data" );

            } else {
                Log.e("tag_itemCard", "****Failed to export data");
                Log.e("GetGroup9 = ", "Failed to export data" );
//                Log.e("tag_itemCard", "****"+JsonResponse.toString());
             //   Toast.makeText(context, "faild export"+ JsonResponse.toString(), Toast.LENGTH_LONG).show();

//                Toast.makeText(context, "Failed to Get data", Toast.LENGTH_SHORT).show();
//                if(pd!=null) {
//                    pd.dismiss();
//                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText(context.getResources().getString(R.string.ops))
//                            .setContentText(context.getResources().getString(R.string.fildtoimportitemswitch))
//                            .show();
//                }


            }

        }
    }

    private class GetService extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();
//            pd = ProgressDialog.show(context, "title", "loading", true);
//            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
//            pd.setTitleText(context.getResources().getString("Import Data"));


            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                String link = "http://"+ip+"/GetServices".trim();

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8")+"&"+
                        "N1=" + URLEncoder.encode(groupNo, "UTF-8");

                URL url = new URL(link);
                Log.e("urlStringCard = ", "" + url.toString() + "?" + data);
                Log.e("GetServices1 = ", "" + url.toString() + "?" + data );

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");


                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }
                Log.e("GetServices2 = ", "" + JsonResponse );

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                // Log.e("tag", "ItemOCode -->" + stringBuffer.toString());

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

          //  JsonResponse = "[{\"CODE\":\"215\",\"NAME\":\"Full Hair Color\\/Short\",\"GROUP_NO\":\"1\",\"PRICE\":\"50\"," +
           //         "\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"12:44:18 م\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"216\",\"NAME\":\"Roots Highlight\",\"GROUP_NO\":\"1\",\"PRICE\":\"50\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:30\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"12:48:51 م\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"375\",\"NAME\":\"T Section Highlight\",\"GROUP_NO\":\"1\",\"PRICE\":\"45\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:40\",\"INS_USER_NO\":\"2\",\"INS_TIME\":\"17:30\",\"INS_DATE\":\"29\\/07\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"376\",\"NAME\":\"GK Miami\",\"GROUP_NO\":\"1\",\"PRICE\":\"125\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:20\",\"INS_USER_NO\":\"5\",\"INS_TIME\":\"18:06\",\"INS_DATE\":\"29\\/07\\/2020\",\"UPD_USER_NO\":\"2\",\"UPD_TIME\":\"18:13\",\"UPD_DATE\":\"29\\/07\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"420\",\"NAME\":\"Eyebrow Color\",\"GROUP_NO\":\"1\",\"PRICE\":\"5\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:15\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"15:22\",\"INS_DATE\":\"22\\/08\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"272\",\"NAME\":\"Ombre Short Hair\",\"GROUP_NO\":\"1\",\"PRICE\":\"50\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"2\",\"INS_TIME\":\"14:12\",\"INS_DATE\":\"06\\/06\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"18:38\",\"UPD_DATE\":\"07\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"278\",\"NAME\":\"half head highlight short\",\"GROUP_NO\":\"1\",\"PRICE\":\"35\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:01\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"11:00\",\"INS_DATE\":\"09\\/06\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"11:04\",\"UPD_DATE\":\"09\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"282\",\"NAME\":\"Roots Color INOA\",\"GROUP_NO\":\"1\",\"PRICE\":\"40\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:40\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:30\",\"INS_DATE\":\"11\\/06\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"11:56\",\"UPD_DATE\":\"18\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"459\",\"NAME\":\"Wet Look\",\"GROUP_NO\":\"1\",\"PRICE\":\"25\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:01\",\"INS_USER_NO\":\"2\",\"INS_TIME\":\"13:33\",\"INS_DATE\":\"21\\/01\\/2021\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"464\",\"NAME\":\"Hair Training\",\"GROUP_NO\":\"1\",\"PRICE\":\"300\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:01\",\"INS_USER_NO\":\"4\",\"INS_TIME\":\"11:58\",\"INS_DATE\":\"15\\/03\\/2021\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"284\",\"NAME\":\"wavy blow dry\",\"GROUP_NO\":\"1\",\"PRICE\":\"13\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:15\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"16:25\",\"INS_DATE\":\"12\\/06\\/2020\",\"UPD_USER_NO\":\"2\",\"UPD_TIME\":\"19:29\",\"UPD_DATE\":\"03\\/07\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"288\",\"NAME\":\"GK Retouch\",\"GROUP_NO\":\"1\",\"PRICE\":\"0\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:30\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"18:40\",\"INS_DATE\":\"14\\/06\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"20:13\",\"UPD_DATE\":\"14\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"316\",\"NAME\":\"blow dry with mask\",\"GROUP_NO\":\"1\",\"PRICE\":\"15\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:15\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"12:41\",\"INS_DATE\":\"21\\/06\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"174\",\"NAME\":\"Braids\",\"GROUP_NO\":\"1\",\"PRICE\":\"20\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:20\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:36\",\"INS_DATE\":\"27\\/08\\/2019\",\"UPD_USER_NO\":\"4\",\"UPD_TIME\":\"11:18\",\"UPD_DATE\":\"28\\/09\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"3\",\"NAME\":\"Blow Dry\",\"GROUP_NO\":\"1\",\"PRICE\":\"10\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:20\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:01\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"05:21:16 م\",\"UPD_DATE\":\"06\\/08\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"217\",\"NAME\":\"Roots Color\",\"GROUP_NO\":\"1\",\"PRICE\":\"35\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:30\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"16:33\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"4\",\"NAME\":\"Trimming\",\"GROUP_NO\":\"1\",\"PRICE\":\"13\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:15\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:01\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"2\",\"UPD_TIME\":\"18:09\",\"UPD_DATE\":\"23\\/11\\/2021\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"5\",\"NAME\":\"Haircut Kids\",\"GROUP_NO\":\"1\",\"PRICE\":\"13\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:15\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:02\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"05:22:06 م\",\"UPD_DATE\":\"06\\/08\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"7\",\"NAME\":\"Toner\\/Rinse\",\"GROUP_NO\":\"1\",\"PRICE\":\"25\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:10\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:02\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"05:23:16 م\",\"UPD_DATE\":\"06\\/08\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"10\",\"NAME\":\"Ponytail\",\"GROUP_NO\":\"1\",\"PRICE\":\"30\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:25\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:03\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"05:23:41 م\",\"UPD_DATE\":\"06\\/08\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"11\",\"NAME\":\"Fair Style\",\"GROUP_NO\":\"1\",\"PRICE\":\"15\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:20\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:03\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"18:35\",\"UPD_DATE\":\"07\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"12\",\"NAME\":\"Fair Curly\",\"GROUP_NO\":\"1\",\"PRICE\":\"30\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:15\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:03\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"16:58\",\"UPD_DATE\":\"05\\/09\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"15\",\"NAME\":\"Bleaching Hair\\/Short\",\"GROUP_NO\":\"1\",\"PRICE\":\"50\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:30\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:04\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"2\",\"UPD_TIME\":\"09:17\",\"UPD_DATE\":\"04\\/01\\/2021\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"16\",\"NAME\":\"Highlight Long\",\"GROUP_NO\":\"1\",\"PRICE\":\"80\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:04\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"18:37\",\"UPD_DATE\":\"07\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"17\",\"NAME\":\"Highlight Medium Hair\",\"GROUP_NO\":\"1\",\"PRICE\":\"70\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:04\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"18:38\",\"UPD_DATE\":\"07\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"18\",\"NAME\":\"Highlight Short Hair\",\"GROUP_NO\":\"1\",\"PRICE\":\"60\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:04\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"14:01\",\"UPD_DATE\":\"08\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"19\",\"NAME\":\"Ombre Long \\/ Thick Hair\",\"GROUP_NO\":\"1\",\"PRICE\":\"80\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:05\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"18:37\",\"UPD_DATE\":\"07\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"20\",\"NAME\":\"Ombre Medium Hair\",\"GROUP_NO\":\"1\",\"PRICE\":\"70\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:06\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"18:38\",\"UPD_DATE\":\"07\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"21\",\"NAME\":\"Full Hair Color Long - INOA\",\"GROUP_NO\":\"1\",\"PRICE\":\"70\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:06\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"14:06\",\"UPD_DATE\":\"08\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"22\",\"NAME\":\"Full Hair Color Medium - INOA\",\"GROUP_NO\":\"1\",\"PRICE\":\"60\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:06\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"14:06\",\"UPD_DATE\":\"08\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"23\",\"NAME\":\"Full Hair Color Short - INOA\",\"GROUP_NO\":\"1\",\"PRICE\":\"50\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:06\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"14:05\",\"UPD_DATE\":\"08\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"32\",\"NAME\":\"Top Highlight\",\"GROUP_NO\":\"1\",\"PRICE\":\"40\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:30\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:09\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"14:06\",\"UPD_DATE\":\"08\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"35\",\"NAME\":\"Hair Updo\",\"GROUP_NO\":\"1\",\"PRICE\":\"60\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:45\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:09\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"13:57\",\"UPD_DATE\":\"08\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"37\",\"NAME\":\"Half Hair Updo\",\"GROUP_NO\":\"1\",\"PRICE\":\"35\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:45\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:10\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"09:32\",\"UPD_DATE\":\"25\\/05\\/2022\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"350\",\"NAME\":\"PowerShot Treatment\",\"GROUP_NO\":\"1\",\"PRICE\":\"15\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:10\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"09:29\",\"INS_DATE\":\"08\\/07\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"351\",\"NAME\":\"Refill Hair Talk - 5 Packs\",\"GROUP_NO\":\"1\",\"PRICE\":\"125\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"14:32\",\"INS_DATE\":\"09\\/07\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"363\",\"NAME\":\"Forhead Haircut\",\"GROUP_NO\":\"1\",\"PRICE\":\"5\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:10\",\"INS_USER_NO\":\"2\",\"INS_TIME\":\"14:39\",\"INS_DATE\":\"18\\/07\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"269\",\"NAME\":\"Color Consultation\",\"GROUP_NO\":\"1\",\"PRICE\":\"0\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:10\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"12:05\",\"INS_DATE\":\"04\\/06\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"12:07\",\"UPD_DATE\":\"04\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"270\",\"NAME\":\"Fiber Clinix Treatment Long\",\"GROUP_NO\":\"1\",\"PRICE\":\"45\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:25\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"12:06\",\"INS_DATE\":\"04\\/06\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"12:35\",\"UPD_DATE\":\"06\\/07\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"6\",\"NAME\":\"Haircut\",\"GROUP_NO\":\"1\",\"PRICE\":\"20\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:30\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:02\",\"INS_DATE\":\"02\\/07\\/2019\",\"UPD_USER_NO\":\"2\",\"UPD_TIME\":\"18:09\",\"UPD_DATE\":\"23\\/11\\/2021\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"195\",\"NAME\":\"Retrew\",\"GROUP_NO\":\"1\",\"PRICE\":\"50\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"12:28:49 م\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"196\",\"NAME\":\"Bleaching Hair\\/Meduim\",\"GROUP_NO\":\"1\",\"PRICE\":\"60\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:30\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"12:32:25 م\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"197\",\"NAME\":\"Bleaching Hair\\/Long\",\"GROUP_NO\":\"1\",\"PRICE\":\"80\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:30\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"05:06:34 م\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"198\",\"NAME\":\"Half Head Highlight\\/Meduim\",\"GROUP_NO\":\"1\",\"PRICE\":\"40\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:40\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"05:20:19 م\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"199\",\"NAME\":\"Half Head Highlight\\/Long\",\"GROUP_NO\":\"1\",\"PRICE\":\"40\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:40\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"05:20:47 م\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"11:03\",\"UPD_DATE\":\"09\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"200\",\"NAME\":\"Low Light\\/Long\",\"GROUP_NO\":\"1\",\"PRICE\":\"80\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:40\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"05:39:41 م\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"201\",\"NAME\":\"Low Light\\/Meduim\",\"GROUP_NO\":\"1\",\"PRICE\":\"65\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:40\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"05:40:11 م\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"202\",\"NAME\":\"Low Light\\/Short\",\"GROUP_NO\":\"1\",\"PRICE\":\"50\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:40\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"05:40:38 م\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"341\",\"NAME\":\"Bridal Hair 2nd Look\",\"GROUP_NO\":\"1\",\"PRICE\":\"150\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:01\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"13:34\",\"INS_DATE\":\"02\\/07\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"09:32\",\"UPD_DATE\":\"08\\/07\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"347\",\"NAME\":\"Braids Fair Style\",\"GROUP_NO\":\"1\",\"PRICE\":\"25\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:35\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"16:02\",\"INS_DATE\":\"06\\/07\\/2020\",\"UPD_USER_NO\":\"4\",\"UPD_TIME\":\"11:19\",\"UPD_DATE\":\"28\\/09\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"348\",\"NAME\":\"GK Fast Blow Dry\",\"GROUP_NO\":\"1\",\"PRICE\":\"25\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:25\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"12:35\",\"INS_DATE\":\"07\\/07\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"352\",\"NAME\":\"Hair Extension Rent\",\"GROUP_NO\":\"1\",\"PRICE\":\"100\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:01\",\"INS_USER_NO\":\"2\",\"INS_TIME\":\"12:34\",\"INS_DATE\":\"10\\/07\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"389\",\"NAME\":\"Natural Hair Extension Buy\",\"GROUP_NO\":\"1\",\"PRICE\":\"350\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:01\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"10:56\",\"INS_DATE\":\"06\\/08\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"390\",\"NAME\":\"Natural Hair Extension Rent\",\"GROUP_NO\":\"1\",\"PRICE\":\"150\",\"POINT\":\"0\",\"DURATION_TIME\":\"00:01\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"10:57\",\"INS_DATE\":\"06\\/08\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"213\",\"NAME\":\"Full Hair Color\\/Long\",\"GROUP_NO\":\"1\",\"PRICE\":\"70\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"12:42:54 م\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"2\",\"UPD_TIME\":\"9:12:21 AM\",\"UPD_DATE\":\"28\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"},{\"CODE\":\"214\",\"NAME\":\"Full Hair Color\\/Meduim\",\"GROUP_NO\":\"1\",\"PRICE\":\"60\",\"POINT\":\"0\",\"DURATION_TIME\":\"01:00\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"12:43:28 م\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"2\",\"UPD_TIME\":\"9:12:10 AM\",\"UPD_DATE\":\"28\\/06\\/2020\",\"ACC_CODE\":\"41000\",\"MULTI_GROUP\":\"\",\"ACTIVE\":\"1\"," +
           //         "\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"PRICEVIP\":\"\"}]";
            if (JsonResponse != null && JsonResponse.contains("CODE")) {
                Log.e("tag_ItemOCode", "****Success");
                Log.e("GetServices3 = ", "" + JsonResponse );

                JsonResponseSave = JsonResponse;

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<GetServiceModel>>() {
                }.getType();
                Collection<GetServiceModel> enums = gson.fromJson(JsonResponse, collectionType);
                servicesList.clear();
                servicesList = (List<GetServiceModel>) enums;

                Log.e("GetServices3 = ", "" + servicesList.size() );

                try{

                    AppendActivity appendActivity=(AppendActivity) context;
                    appendActivity.fillServiceList(servicesList,1);

                }catch (Exception e){
                    Log.e("importGetService","eRROR"+e.toString());
                }

//
//                controllClass.saveTransactionTable(transactionTableList);
                Toast.makeText(context, "save", Toast.LENGTH_LONG).show();

            } else if (JsonResponse != null && JsonResponse.contains("No Data Found.")) {
                // new SyncItemSwitch().execute();
                // Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();

                try{
                    servicesList.clear();
                    AppendActivity appendActivity=(AppendActivity) context;
                    appendActivity.fillServiceList(servicesList,1);

                }catch (Exception e){
                    Log.e("importGetService","eRROR"+e.toString());
                }

                Log.e("GetServices4 = ", "nodata"  );

            } else {
                Log.e("tag_itemCard", "****Failed to export data");
                Log.e("GetServices5 = ", "Failed"  );

                try{
                    servicesList.clear();
                    AppendActivity appendActivity=(AppendActivity) context;
                    appendActivity.fillServiceList(servicesList,1);

                }catch (Exception e){
                    Log.e("importGetService","eRROR"+e.toString());
                }

//                Log.e("tag_itemCard", "****"+JsonResponse.toString());
                //   Toast.makeText(context, "faild export"+ JsonResponse.toString(), Toast.LENGTH_LONG).show();

//                Toast.makeText(context, "Failed to Get data", Toast.LENGTH_SHORT).show();
//                if(pd!=null) {
//                    pd.dismiss();
//                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText(context.getResources().getString(R.string.ops))
//                            .setContentText(context.getResources().getString(R.string.fildtoimportitemswitch))
//                            .show();
//                }


            }

        }
    }

    private class GetEmploy extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();
//            pd = ProgressDialog.show(context, "title", "loading", true);
//            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
//            pd.setTitleText(context.getResources().getString("Import Data"));


            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                String link = "http://"+ip+"/GetEmployee".trim();

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8");

                URL url = new URL(link);
                Log.e("urlStringCard = ", "" + url.toString() + "?" + data);
                Log.e("GetEmployee1 = ", ""+ url.toString() + "?" + data  );

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");


                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                // Log.e("tag", "ItemOCode -->" + stringBuffer.toString());
                Log.e("GetEmployee2 = ", ""+ stringBuffer.toString() );

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

//            JsonResponse = "[{\"CODE\":\"54\",\"NAME\":\"Fawzia - Makeup\",\"JOB_NO\":\"3\"," +
//                    "\"INS_USER_NO\":\"6\",\"INS_TIME\":\"16:26\",\"INS_DATE\":\"03\\/06\\/2022\"," +
//                    "\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\"," +
//                    "\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"51\",\"NAME\":\"Charlie - Colorist\",\"JOB_NO\":\"1\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"10:33:10\",\"INS_DATE\":\"25\\/04\\/2022\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"20:13\",\"UPD_DATE\":\"25\\/04\\/2022\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"52\",\"NAME\":\"Taghreed - Coloriest\",\"JOB_NO\":\"7\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"12:18:07\",\"INS_DATE\":\"15\\/05\\/2022\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"09:22\",\"UPD_DATE\":\"23\\/05\\/2022\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"53\",\"NAME\":\"Ibrahim - Asst\",\"JOB_NO\":\"2\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"11:20\",\"INS_DATE\":\"17\\/05\\/2022\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"11:20\",\"UPD_DATE\":\"17\\/05\\/2022\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"39\",\"NAME\":\"Qais - MEN\",\"JOB_NO\":\"1\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"12:37\",\"INS_DATE\":\"07\\/05\\/2021\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"09:07\",\"UPD_DATE\":\"05\\/08\\/2021\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"40\",\"NAME\":\"Abood-MEN\",\"JOB_NO\":\"1\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"12:37\",\"INS_DATE\":\"07\\/05\\/2021\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"09:07\",\"UPD_DATE\":\"05\\/08\\/2021\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"38\",\"NAME\":\"Mahmoud\",\"JOB_NO\":\"2\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"9:25:34 AM\",\"INS_DATE\":\"24\\/01\\/2021\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"09:31\",\"UPD_DATE\":\"10\\/05\\/2021\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"43\",\"NAME\":\"Taj - Nails\",\"JOB_NO\":\"5\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"17:55\",\"INS_DATE\":\"22\\/05\\/2021\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"44\",\"NAME\":\"Noor - Eyebrow\",\"JOB_NO\":\"9\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"19:00\",\"INS_DATE\":\"29\\/05\\/2021\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"10:02:57\",\"UPD_DATE\":\"21\\/02\\/2022\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"45\",\"NAME\":\"Meshmesh - Nails\",\"JOB_NO\":\"5\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"16:12\",\"INS_DATE\":\"03\\/06\\/2021\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"09:54\",\"UPD_DATE\":\"04\\/06\\/2021\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"46\",\"NAME\":\"Tia - Nails\",\"JOB_NO\":\"5\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"16:14\",\"INS_DATE\":\"03\\/06\\/2021\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"47\",\"NAME\":\"Rozan-Asst\",\"JOB_NO\":\"2\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"10:20\",\"INS_DATE\":\"13\\/07\\/2021\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"12:55\",\"UPD_DATE\":\"29\\/04\\/2022\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"48\",\"NAME\":\"Daisy - Nail\",\"JOB_NO\":\"6\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"14:40\",\"INS_DATE\":\"01\\/01\\/2022\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"14:41\",\"UPD_DATE\":\"01\\/01\\/2022\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"41\",\"NAME\":\"Dalal - HairArtist\",\"JOB_NO\":\"1\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"13:29\",\"INS_DATE\":\"15\\/05\\/2021\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"09:08\",\"UPD_DATE\":\"05\\/08\\/2021\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"42\",\"NAME\":\"Farah - Makeup\",\"JOB_NO\":\"3\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"14:50\",\"INS_DATE\":\"19\\/05\\/2021\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"2\",\"NAME\":\"Hovig - HairArtist\",\"JOB_NO\":\"1\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"11:01:27 ص\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"18:14:22\",\"UPD_DATE\":\"01\\/12\\/2021\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"3\",\"NAME\":\"Khader - HairArtist\",\"JOB_NO\":\"1\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"11:01:35 ص\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"14:25:43\",\"UPD_DATE\":\"10\\/03\\/2021\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"1\",\"NAME\":\"Raffi - Colorist\",\"JOB_NO\":\"7\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"11:01:17 ص\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"13:58\",\"UPD_DATE\":\"02\\/01\\/2021\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"36\",\"NAME\":\"Reham\",\"JOB_NO\":\"2\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"13:15:03\",\"INS_DATE\":\"22\\/09\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"09:10\",\"UPD_DATE\":\"26\\/11\\/2020\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"5\",\"NAME\":\"Esraa - HairArtist\",\"JOB_NO\":\"1\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"11:02:01 ص\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"09:36\",\"UPD_DATE\":\"07\\/03\\/2021\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"6\",\"NAME\":\"Diana - Makeup\",\"JOB_NO\":\"3\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"11:03:32 ص\",\"INS_DATE\":\"19\\/05\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"11:24\",\"UPD_DATE\":\"17\\/07\\/2021\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"10\",\"NAME\":\"Jakob - The Boss\",\"JOB_NO\":\"3\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"5:48:48 PM\",\"INS_DATE\":\"01\\/06\\/2020\",\"UPD_USER_NO\":\"1\",\"UPD_TIME\":\"19:24\",\"UPD_DATE\":\"27\\/06\\/2020\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"18\",\"NAME\":\"Joy - Nails\",\"JOB_NO\":\"5\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"09:15\",\"INS_DATE\":\"14\\/06\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"20:32:41\",\"UPD_DATE\":\"31\\/12\\/2021\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"21\",\"NAME\":\"Dina - Nails\",\"JOB_NO\":\"6\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"3:20:39 PM\",\"INS_DATE\":\"22\\/06\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"09:34\",\"UPD_DATE\":\"06\\/03\\/2021\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"22\",\"NAME\":\"Rasha - MakeupArtist\",\"JOB_NO\":\"3\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"19:21\",\"INS_DATE\":\"27\\/06\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"9:46:44\",\"UPD_DATE\":\"04\\/10\\/2020\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"23\",\"NAME\":\"YuYu - Nails\",\"JOB_NO\":\"6\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"9:09:32 AM\",\"INS_DATE\":\"08\\/07\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"20:41\",\"UPD_DATE\":\"21\\/07\\/2020\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"30\",\"NAME\":\"Marina - Lashes\",\"JOB_NO\":\"10\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"16:26\",\"INS_DATE\":\"19\\/08\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"9:24:35 AM\",\"UPD_DATE\":\"24\\/01\\/2021\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"31\",\"NAME\":\"Julla - Lashes\",\"JOB_NO\":\"10\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"16:27\",\"INS_DATE\":\"19\\/08\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"13:51\",\"UPD_DATE\":\"21\\/12\\/2020\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"32\",\"NAME\":\"Allona - Lashes\",\"JOB_NO\":\"10\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"16:27\",\"INS_DATE\":\"19\\/08\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"13:51\",\"UPD_DATE\":\"21\\/12\\/2020\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"33\",\"NAME\":\"Anastasia - Tatto\\/Lashes\",\"JOB_NO\":\"10\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"16:28\",\"INS_DATE\":\"19\\/08\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"13:51\",\"UPD_DATE\":\"21\\/12\\/2020\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"34\",\"NAME\":\"Oxana - Facial\",\"JOB_NO\":\"12\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"16:28\",\"INS_DATE\":\"19\\/08\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"13:51\",\"UPD_DATE\":\"21\\/12\\/2020\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"11\",\"NAME\":\"Nilly - Nails\",\"JOB_NO\":\"6\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"11:56:04 AM\",\"INS_DATE\":\"03\\/06\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"16:11\",\"UPD_DATE\":\"03\\/06\\/2021\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"26\",\"NAME\":\"Wala - Colorist\",\"JOB_NO\":\"7\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"11:07\",\"INS_DATE\":\"01\\/08\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"4:15:27 PM\",\"UPD_DATE\":\"20\\/12\\/2020\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"27\",\"NAME\":\"Atef - Colorist\",\"JOB_NO\":\"7\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"14:32\",\"INS_DATE\":\"07\\/08\\/2020\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"28\",\"NAME\":\"Diana - Nails\",\"JOB_NO\":\"6\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"12:04\",\"INS_DATE\":\"10\\/08\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"09:27\",\"UPD_DATE\":\"15\\/08\\/2020\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"37\",\"NAME\":\"Hala - Skin Care\",\"JOB_NO\":\"12\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"17:21:14\",\"INS_DATE\":\"31\\/10\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"9:24:28 AM\",\"UPD_DATE\":\"24\\/01\\/2021\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"35\",\"NAME\":\"Rabab - Ass\",\"JOB_NO\":\"2\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"20:12\",\"INS_DATE\":\"08\\/09\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"11:05:09\",\"UPD_DATE\":\"01\\/03\\/2022\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"25\",\"NAME\":\"Hamoudeh - Colorist\",\"JOB_NO\":\"7\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"21:28\",\"INS_DATE\":\"28\\/07\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"10:02:17\",\"UPD_DATE\":\"21\\/02\\/2022\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"8\",\"NAME\":\"Sultan - Ass\",\"JOB_NO\":\"2\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"12:25:15 م\",\"INS_DATE\":\"29\\/05\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"11:04:45\",\"UPD_DATE\":\"01\\/03\\/2022\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"9\",\"NAME\":\"Sami - Ass\",\"JOB_NO\":\"2\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"12:25:24 م\",\"INS_DATE\":\"29\\/05\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"12:24\",\"UPD_DATE\":\"29\\/04\\/2022\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"14\",\"NAME\":\"Lorena - Nails\",\"JOB_NO\":\"6\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"4:07:46 PM\",\"INS_DATE\":\"06\\/06\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"10:02:31 AM\",\"UPD_DATE\":\"02\\/11\\/2020\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"15\",\"NAME\":\"Maria - Nails\",\"JOB_NO\":\"6\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"6:58:31 PM\",\"INS_DATE\":\"10\\/06\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"17:47\",\"UPD_DATE\":\"02\\/02\\/2021\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"16\",\"NAME\":\"Wejdan - MakeupArtist\",\"JOB_NO\":\"3\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"6:59:47 PM\",\"INS_DATE\":\"10\\/06\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"14:11\",\"UPD_DATE\":\"02\\/05\\/2021\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"19\",\"NAME\":\"Anna - Nails\",\"JOB_NO\":\"6\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"12:04\",\"INS_DATE\":\"17\\/06\\/2020\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"09:01\",\"UPD_DATE\":\"07\\/11\\/2020\",\"ACTIVE\":\"0\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"49\",\"NAME\":\"Mahmoud - Ass\",\"JOB_NO\":\"2\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"11:04:03\",\"INS_DATE\":\"01\\/03\\/2022\",\"UPD_USER_NO\":\"6\",\"UPD_TIME\":\"11:04:19\",\"UPD_DATE\":\"01\\/03\\/2022\",\"ACTIVE\":\"1\",\"SECTION\":\"\",\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"},{\"CODE\":\"50\",\"NAME\":\"Mohamad - Ass\",\"JOB_NO\":\"2\",\"INS_USER_NO\":\"6\",\"INS_TIME\":\"11:04:34\",\"INS_DATE\":\"01\\/03\\/2022\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\"UPD_DATE\":\"\",\"ACTIVE\":\"1\",\"SECTION\":\"\"," +
//                    "\"COMMISSION_RATE\":\"\",\"GET_COMMISSION\":\"\",\"COLOR\":\"\",\"ORDER_EMP\":\"\"}]";
            if (JsonResponse != null && JsonResponse.contains("CODE")) {
                Log.e("tag_ItemOCode", "****Success");
                Log.e("GetEmployee4 = ", ""+ JsonResponse.toString() );

                JsonResponseSave = JsonResponse;

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<GetEmployModel>>() {
                }.getType();
                Collection<GetEmployModel> enums = gson.fromJson(JsonResponse, collectionType);
                EmployList.clear();
                EmployList = (List<GetEmployModel>) enums;

                controllClass.saveEmployTable(EmployList);

                try{

                    FirstLayoutApp appendActivity=(FirstLayoutApp) context;
                    appendActivity.showAllDataAccount();

                }catch (Exception e){
                    Log.e("importGetService","eRROR"+e.toString());
                }

                Toast.makeText(context, "save", Toast.LENGTH_LONG).show();

            } else if (JsonResponse != null && JsonResponse.contains("No Data Found.")) {
                // new SyncItemSwitch().execute();
                // Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();
                Log.e("GetEmployee6 = ", "no Data Found" );

            } else {
                Log.e("tag_itemCard", "****Failed to export data");
//                Log.e("tag_itemCard", "****"+JsonResponse.toString());
                //   Toast.makeText(context, "faild export"+ JsonResponse.toString(), Toast.LENGTH_LONG).show();
                Log.e("GetEmployee8 = ", "faild" );

//                Toast.makeText(context, "Failed to Get data", Toast.LENGTH_SHORT).show();
//                if(pd!=null) {
//                    pd.dismiss();
//                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText(context.getResources().getString(R.string.ops))
//                            .setContentText(context.getResources().getString(R.string.fildtoimportitemswitch))
//                            .show();
//                }


            }

        }
    }

    private class GetAllCustomer extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();
//            pd = ProgressDialog.show(context, "title", "loading", true);
//            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
//            pd.setTitleText(context.getResources().getString("Import Data"));


            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                String link = "http://"+ip+"/GetCustName".trim();

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8");

                URL url = new URL(link);
                Log.e("urlStringCard = ", "" + url.toString() + "?" + data);
                Log.e("GetCustomer1 = ", ""+ url.toString() + "?" + data  );

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");


                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                // Log.e("tag", "ItemOCode -->" + stringBuffer.toString());
                Log.e("GetCustomer2 = ", ""+ stringBuffer.toString() );

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

//            JsonResponse = "[{\"CODE\":\"1\",\"FULL_NAME\":\"ليليان   محيسن\",\"PHONE\":\"0799214058\"},{\"CODE\":\"2\",\"FULL_NAME\":\"ali   abutouq\",\"PHONE\":\"0790606791\"}]";
            if (JsonResponse != null && JsonResponse.contains("CODE")) {
                Log.e("tag_ItemOCode", "****Success");
                Log.e("GetCustomer4 = ", ""+ JsonResponse.toString() );

                JsonResponseSave = JsonResponse;

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<GetCustomerNameTable>>() {
                }.getType();
                Collection<GetCustomerNameTable> enums = gson.fromJson(JsonResponse, collectionType);
                customerList.clear();
                customerList = (List<GetCustomerNameTable>) enums;

                controllClass.saveCustomerTable(customerList);

                try{



                }catch (Exception e){
                    Log.e("importGetService","eRROR"+e.toString());
                }

                Toast.makeText(context, "save", Toast.LENGTH_LONG).show();

            } else if (JsonResponse != null && JsonResponse.contains("No Data Found.")) {
                // new SyncItemSwitch().execute();
                // Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();
                Log.e("GetCustomer6 = ", "no Data Found" );

            } else {
                Log.e("tag_itemCard", "****Failed to export data");
//                Log.e("tag_itemCard", "****"+JsonResponse.toString());
                //   Toast.makeText(context, "faild export"+ JsonResponse.toString(), Toast.LENGTH_LONG).show();
                Log.e("GetCustomer8 = ", "faild" );

//                Toast.makeText(context, "Failed to Get data", Toast.LENGTH_SHORT).show();
//                if(pd!=null) {
//                    pd.dismiss();
//                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText(context.getResources().getString(R.string.ops))
//                            .setContentText(context.getResources().getString(R.string.fildtoimportitemswitch))
//                            .show();
//                }


            }

        }
    }

    private class GetAppoinmentDetail extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();
//            pd = ProgressDialog.show(context, "title", "loading", true);
//            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
//            pd.setTitleText(context.getResources().getString("Import Data"));


            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                String link = "http://"+ip+"/GetAppotmentDetales".trim();

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8")+"&"
                        +"N1=" + URLEncoder.encode(N1, "UTF-8");

                URL url = new URL(link);
                Log.e("urlStringCard = ", "" + url.toString() + "?" + data);
                Log.e("GetAppotmentDetales1 = ", ""+ url.toString() + "?" + data );

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");


                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                // Log.e("tag", "ItemOCode -->" + stringBuffer.toString());

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

//            JsonResponse = "[{\"CODE\":\"6\",\"SERVICE_NO\":\"3\",\"SERVICE_DURATION\":\"00:20\",\"ROW_NUMBER\":\"1\",\"INS_USER_NO\":\"1\",\"INS_TIME\":\"16:03\",\"INS_DATE\":\"30\\/05\\/2020\",\"JOB_NO\":\"\",\"APP_DATE\":\"\",\"NOTE\":\"\"},{\"CODE\":\"6\",\"SERVICE_NO\":\"4\",\"SERVICE_DURATION\":\"00:10\",\"ROW_NUMBER\":\"2\",\"INS_USER_NO\":\"1\"" +
//                    ",\"INS_TIME\":\"16:03\",\"INS_DATE\":\"30\\/05\\/2020\",\"JOB_NO\":\"\",\"APP_DATE\":\"\",\"NOTE\":\"\"}]";
            if (JsonResponse != null && JsonResponse.contains("CODE")) {
                Log.e("tag_ItemOCode", "****Success");
                Log.e("GetAppotmentDetales2 = ", "successful" );

                JsonResponseSave = JsonResponse;

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<GetAppotmentDetales>>() {
                }.getType();
                Collection<GetAppotmentDetales> enums = gson.fromJson(JsonResponse, collectionType);
                appoimentDetail.clear();
                appoimentDetail = (List<GetAppotmentDetales>) enums;

                try{

                    AppendActivity appendActivity=(AppendActivity) context;

                    appendActivity.refreshService(appoimentDetail,1);

                }catch (Exception e){
                    Log.e("importGetService","eRROR"+e.toString());
                    Log.e("GetAppotmentDetales3 = ", "eRROR"+e.toString() );

                }


//
//                controllClass.saveEmployTable(appoimentDetail);
                Toast.makeText(context, "save", Toast.LENGTH_LONG).show();

            } else if (JsonResponse != null && JsonResponse.contains("No Data Found.")) {
                // new SyncItemSwitch().execute();
                // Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();
                Log.e("GetAppotmentDetales3 = ", "nodata" );

            } else {
                Log.e("tag_itemCard", "****Failed to export data");
//                Log.e("tag_itemCard", "****"+JsonResponse.toString());
                //   Toast.makeText(context, "faild export"+ JsonResponse.toString(), Toast.LENGTH_LONG).show();
                Log.e("GetAppotmentDetales3 = ", "faild" );

//                Toast.makeText(context, "Failed to Get data", Toast.LENGTH_SHORT).show();
//                if(pd!=null) {
//                    pd.dismiss();
//                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText(context.getResources().getString(R.string.ops))
//                            .setContentText(context.getResources().getString(R.string.fildtoimportitemswitch))
//                            .show();
//                }


            }

        }
    }

    private class GetCustomer extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;

        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();
//            pd = ProgressDialog.show(context, "title", "loading", true);
//            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
//            pd.setTitleText(context.getResources().getString("Import Data"));


            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                String link = "http://"+ip+"/GetCustInfo".trim();

                String data = "CONO=" + URLEncoder.encode(CoNo, "UTF-8")+"&"+
                        "N1=" + URLEncoder.encode(custNo, "UTF-8");


                URL url = new URL(link);
                Log.e("urlStringCard = ", "" + url.toString() + "?" + data);
                Log.e("GetCustInfo1 = ", ""+ url.toString() + "?" + data );

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");


                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                while ((JsonResponse = bufferedReader.readLine()) != null) {
                    stringBuffer.append(JsonResponse + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                // Log.e("tag", "ItemOCode -->" + stringBuffer.toString());
                Log.e("GetCustInfo3 = ", ""+ stringBuffer.toString() );

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

//            JsonResponse = "[{\"CODE\":\"1\",\"BIRTH_DATE_AD\":\"\",\"AGE\":\"\",\"GENDER\":\"\",\"NATIONALITY\":\"\",\"PHONE\":\"\",\"ADDRESS\":\"\",\n" +
//                    " \"SOURCE\":\"\",\"INS_USER_NO\":\"\",\"INS_TIME\":\"\",\"INS_DATE\":\"\",\"UPD_USER_NO\":\"\",\"UPD_TIME\":\"\",\n" +
//                    " \"UPD_DATE\":\"\",\"NOTE_1\":\"\",\"NOTE_2\":\"\",\"ACCCODE\":\"\",\"POINTVIP\":\"\",\"ALL_CUST\":\"\",\"BALANCE\":\"16.6\",\n" +
//                    " \"CODE_1\":\"1\",\"FIRST_NAME\":\"Abeer\",\"SECOND_NAME\":\"Saloon\",\"THIERD_NAME\":\"\\/\",\"LAST_NAME\":\"Kuwait\",\"FULL_NAME\":\"Abeer Saloon \\/ Kuwait\",\"INS_USER_NO_1\":\"\"\n" +
//                    " ,\"INS_TIME_1\":\"\",\"INS_DATE_1\":\"\",\"UPD_USER_NO_1\":\"\",\"UPD_TIME_1\":\"\",\"UPD_DATE_1\":\"\"}]";
            if (JsonResponse != null && JsonResponse.contains("CODE")) {
                Log.e("tag_ItemOCode", "****Success");

                Log.e("GetCustInfo3 = ", ""+ JsonResponse.toString() );

                JsonResponseSave = JsonResponse;

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<CustomerInfoModel>>() {
                }.getType();
                Collection<CustomerInfoModel> enums = gson.fromJson(JsonResponse, collectionType);
                customerInfo.clear();
                customerInfo = (List<CustomerInfoModel>) enums;

                try{

                    AppendActivity appendActivity=(AppendActivity) context;
                    appendActivity.fillCustomerInfo(customerInfo);

                }catch (Exception e){
                    Log.e("importGetService","eRROR"+e.toString());
                    Log.e("GetCustInfo4 = ", ""+ e.toString() );

                }

//
//                controllClass.saveTransactionTable(transactionTableList);
                Toast.makeText(context, "save", Toast.LENGTH_LONG).show();

            } else if (JsonResponse != null && JsonResponse.contains("No Data Found.")) {
                // new SyncItemSwitch().execute();
                // Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();
                Log.e("GetCustInfo4 = ", "no daa" );

            } else {

                Log.e("GetCustInfo4 = ", "Failed" );
                Log.e("tag_itemCard", "****Failed to export data");
//                Log.e("tag_itemCard", "****"+JsonResponse.toString());
                //   Toast.makeText(context, "faild export"+ JsonResponse.toString(), Toast.LENGTH_LONG).show();

//                Toast.makeText(context, "Failed to Get data", Toast.LENGTH_SHORT).show();
//                if(pd!=null) {
//                    pd.dismiss();
//                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText(context.getResources().getString(R.string.ops))
//                            .setContentText(context.getResources().getString(R.string.fildtoimportitemswitch))
//                            .show();
//                }


            }

        }
    }
}








