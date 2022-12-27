package com.example.salonapp.DataServer;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import com.example.salonapp.AppendActivity;
import com.example.salonapp.ControllClass;
import com.example.salonapp.Model.GetAppotmentDetales;
import com.example.salonapp.Model.SettingModel;
import com.example.salonapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ExportJson {

    private Context context;
    private ProgressDialog progressDialog;
    private ProgressDialog progressDialogSave;
    private JSONObject obj;
    private JSONObject deleteObj;
    String itemCode;
    String JsonResponseSave;
    ControllClass controllClass;
    String codeNo;
    String serviceNo;

    String ip,CoNo,date,icode;
//    DataStatus dataStatus;
    SweetAlertDialog pd;

//    List<TransactionTable> transactionTableList;


    //http://10.0.0.121:8088/UpdateApp?CONO=FSS&JSONSTR={%22JSN%22:[{%22iCode%22:%2237121%22,%22sFromTimea%22:%2213:00%22,%22sToTime%22:%2215:15:00%22,%22sDuration%22:%2202:15:00%22,%22sNote1%22:%22%22,%22sNote2%22:%22%22,%22sUpdUser%22:%22user%22,%22sUpdTime%22:%2212:55:35%22,%22dUpdDate%22:%2201\/05\/2022%22,%22iServiceNo%22:%22470%22,%22sDurationService%22:%2201:00%22,%22iRowNumber%22:%221%22,%22sInsUserNo%22:%226%22,%22sInsTime%22:%2210:00%22,%22dInsDate%22:%2201\/05\/2022%22},{%22iCode%22:%2237121%22,%22sFromTime%22:%2213:00%22,%22sToTime%22:%2215:15:00%22,%22sDuration%22:%2202:15:00%22,%22sNote1%22:%22%22,%22sNote2%22:%22%22,%22sUpdUser%22:%22user%22,%22sUpdTime%22:%2212:55:35%22,%22dUpdDate%22:%2201\/05\/2022%22,%22iServiceNo%22:%22398%22,%22sDurationService%22:%2201:00%22,%22iRowNumber%22:%222%22,%22sInsUserNo%22:%226%22,%22sInsTime%22:%2210:00%22,%22dInsDate%22:%2201\/05\/2022%22},{%22iCode%22:%2237121%22,%22sFromTime%22:%2213:00%22,%22sToTime%22:%2215:15:00%22,%22sDuration%22:%2202:15:00%22,%22sNote1%22:%22%22,%22sNote2%22:%22%22,%22sUpdUser%22:%22user%22,%22sUpdTime%22:%2212:55:35%22,%22dUpdDate%22:%2201\/05\/2022%22,%22iServiceNo%22:%22399%22,%22sDurationService%22:%2200:15%22,%22iRowNumber%22:%223%22,%22sInsUserNo%22:%226%22,%22sInsTime%22:%2210:00%22,%22dInsDate%22:%2201\/05\/2022%22}]}


final SettingModel mainSettings;

    public ExportJson(Context context) {//, JSONObject obj
        this.context = context;
//        transactionTableList = new ArrayList<>();
        controllClass = new ControllClass(context);
         mainSettings = controllClass.getSettings();
        Log.e("settingip","48");
        try {
            pd = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        }catch (Exception e){

        }
        if ( mainSettings.getIp()!=null ) {
            Log.e("settingip"," 52 "+mainSettings.getIp());
            ip = mainSettings.getIp();
            CoNo=mainSettings.getCono();
        }
    }

    public void UpdateAction(JSONObject jsonObject,String dDate,String iCode){//,DataStatus dataStatus){
        if ( mainSettings!=null ) {

            this.obj = jsonObject;
            this.icode=iCode;
            this.date=dDate;
//        this.dataStatus=dataStatus;
            new SaveTransaction().execute();
        }else {
            Toast.makeText(context, "Please Add Setting", Toast.LENGTH_LONG).show();

        }
    }

    public void UpdateDeleteAction(List<GetAppotmentDetales> deleteList, String iCode/*, String iServiceNo*/){//,DataStatus dataStatus){
        if ( mainSettings!=null ) {

            this.codeNo = iCode;
//            this.serviceNo=iServiceNo;
////        this.dataStatus=dataStatus;
//            Log.e("ServiceDelete",""+iCode+"  "+iServiceNo);
            for (int y=0;y<deleteList.size();y++) {

                new SaveDeleteTransaction(deleteList.get(y).getSERVICE_NO()).execute();
            }
        }else {
            Toast.makeText(context, " Please Add Setting", Toast.LENGTH_LONG).show();

        }
    }


    private class SaveTransaction extends AsyncTask<String, String, String> {
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
            pd.getProgressHelper().setBarColor(Color.parseColor("#69095E"));
            pd.setTitleText(context.getResources().getString(R.string.update));
            pd.setCancelable(false);
            pd.show();

            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                String link = "http://" + ip + "/UpdateApp";

//                String data = "CONO=" + URLEncoder.encode("ATT", "UTF-8");

                String data = "JSONSTR=" + URLEncoder.encode(obj.toString(), "UTF-8")  + "&" +
                        "CONO="+URLEncoder.encode(CoNo, "UTF-8")+ "&" +
                        "iCode="+URLEncoder.encode(icode, "UTF-8")+ "&" +
                        "dDate="+URLEncoder.encode(date, "UTF-8");

                URL url = new URL(link);
                Log.e("urlStringCard = ", "" + url.toString() + "   " + data);
                Log.e("UpdateAppo1 = ", "" + url.toString() + "   " + data);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");
                Log.e("UpdateAppo1 = ", "114  "+obj.toString());

                Log.e("UpdateAppo1 = ", "105");

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
                Log.e("UpdateAppo1 = ", "126  "+stringBuffer.toString());

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("UpdateAppo1 = ", "132  "+e.toString());

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                    Log.e("UpdateAppo1 = ", "132  Error");

                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("exportUpdate", "Error closing stream", e);
                        Log.e("UpdateAppo1 = ", "145  "+e.toString());

                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

           // JsonResponse = "successful";
            if (JsonResponse != null && JsonResponse.contains("Update Successfully")) {
                Log.e("exportUpdate", "****Success");
                Log.e("UpdateAppo1 = ", "160  success");


                JsonResponseSave = JsonResponse;
//                controllClass.SaveStatusTable(dataStatus);
                // Toast.makeText(context, "Save Successful", Toast.LENGTH_SHORT).show();
//                controllClass.sweetMassageSuccess(context.getResources().getString(R.string.save));
                try {
                    AppendActivity coreActivity = (AppendActivity) context;
                    coreActivity.refreshLayout();
                } catch (Exception e) {

                }

                if(pd!=null) {
                    pd.dismiss();
                }

            } else if (JsonResponse != null && JsonResponse.contains("No Data Found.")) {
                // new SyncItemSwitch().execute();
                Log.e("exportUpdate", "****nodata");
                Log.e("UpdateAppo1 = ", "177  no data");

   //             Toast.makeText(context, "Can Not Update !!!", Toast.LENGTH_LONG).show();
                if(pd!=null) {
                    pd.dismiss();
//                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText(context.getResources().getString(R.string.ops))
//                            .setContentText(context.getResources().getString(R.string.fildtoimport))
//                            .show();
                }

            } else {
                Log.e("tag_itemCard", "****Failed to export data");
   //             Toast.makeText(context, "Can Not Update !!!", Toast.LENGTH_LONG).show();

//                Toast.makeText(context, "Failed to Get data", Toast.LENGTH_SHORT).show();
                if(pd!=null) {
                    pd.dismiss();
                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(context.getResources().getString(R.string.ops))
                            .setContentText(context.getResources().getString(R.string.fildtoimport))
                            .show();
                }
                Log.e("exportUpdate", "****faild");
                Log.e("UpdateAppo1 = ", "191  faild");


            }

        }
    }

    private class SaveDeleteTransaction extends AsyncTask<String, String, String> {
        private String JsonResponse = null;
        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;
String serviceNo;
        public SaveDeleteTransaction(String serviceNo) {
            this.serviceNo=serviceNo;

        }

        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(context,R.style.MyTheme);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setProgress(0);
//            progressDialog.show();
//            pd = ProgressDialog.show(context, "title", "loading", true);
//            pd=new SweetAlertDialog(context,SweetAlertDialog.PROGRESS_TYPE);
            pd.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
            pd.setTitleText(context.getResources().getString(R.string.delete));
            pd.setCancelable(false);
            pd.show();

            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                String link = "http://" + ip + "/DeleteServiceApp?";

//                String data = "CONO=" + URLEncoder.encode("ATT", "UTF-8");

                String data = "N1=" + URLEncoder.encode(codeNo, "UTF-8")  + "&" +
                        "N2="+URLEncoder.encode(serviceNo, "UTF-8")+ "&" +
                        "CONO="+URLEncoder.encode(CoNo, "UTF-8");

                URL url = new URL(link+data);
                Log.e("urlStringCard = ", "" + url.toString() + "   " + data);
                Log.e("Updatedelete1 = ", "" + url.toString() + "   " + data);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("GET");
//                Log.e("Updatedelete1 = ", "114  "+.toString());

                Log.e("Updatedelete1 = ", "105");

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
                Log.e("Updatedelete1 = ", "126  "+stringBuffer.toString());

                return stringBuffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Updatedelete1 = ", "132  "+e.toString());

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                    Log.e("Updatedelete1 = ", "132  Error");

                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("exportUpdate", "Error closing stream", e);
                        Log.e("Updatedelete1 = ", "145  "+e.toString());

                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String JsonResponse) {
            super.onPostExecute(JsonResponse);

            // JsonResponse = "successful";
            if (JsonResponse != null && JsonResponse.contains("Saved Successfully")) {
                Log.e("exportUpdate", "****Success");
                Log.e("Updatedelete1 = ", "160  success");


                JsonResponseSave = JsonResponse;
//                controllClass.SaveStatusTable(dataStatus);
                // Toast.makeText(context, "Save Successful", Toast.LENGTH_SHORT).show();
//                controllClass.sweetMassageSuccess(context.getResources().getString(R.string.save));
                try {
//                    AppendActivity coreActivity = (AppendActivity) context;
//                    coreActivity.refreshLayout();

    //                Toast.makeText(context, "delete Successful", Toast.LENGTH_SHORT).show();
                   // UpdateAction(obj);

                } catch (Exception e) {

                }

                if(pd!=null) {
                    pd.dismiss();
                }

            } else if (JsonResponse != null && JsonResponse.contains("No Data Found.")) {
                // new SyncItemSwitch().execute();
                Log.e("exportUpdate", "****nodata");
                Log.e("Updatedelete1 = ", "177  no data");

  //              Toast.makeText(context, "Can Not delete !!!", Toast.LENGTH_LONG).show();
                if(pd!=null) {
                    pd.dismiss();
//                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText(context.getResources().getString(R.string.ops))
//                            .setContentText(context.getResources().getString(R.string.fildtoimport))
//                            .show();
                }

            } else {
                Log.e("tag_itemCard", "****Failed to export data");
  //              Toast.makeText(context, "Can Not delete !!!", Toast.LENGTH_LONG).show();

//                Toast.makeText(context, "Failed to Get data", Toast.LENGTH_SHORT).show();
                if(pd!=null) {
                    pd.dismiss();
                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(context.getResources().getString(R.string.ops))
                            .setContentText(context.getResources().getString(R.string.fildtoimport)+("delete"))
                            .show();
                }
                Log.e("exportUpdate", "****faild");
                Log.e("Updatedelete1 = ", "191  faild");


            }

        }
    }

//    private class SaveTransactionBy extends AsyncTask<String, String, String> {
//        private String JsonResponse = null;
//        private HttpURLConnection urlConnection = null;
//        private BufferedReader reader = null;
//        String vhfNo, POSNO, orderKind;
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
////            swASingUp = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
////            swASingUp.getProgressHelper().setBarColor(Color.parseColor("#FDD835"));
////            swASingUp.setTitleText("PleaseWait" );
////            swASingUp.setCancelable(false);
////            swASingUp.show();
////            isOk=false;
//
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            String URL_TO_HIT="";
//            try {
////                String ip=captainDatabase.getAllIPSetting();//192.168.1.101:81
//                URL_TO_HIT = "http://" + ip + "/UpdateApp";
//            } catch (Exception e) {
//
//            }
//
//            try {
//                JSONObject jo = obj.getJSONObject("InvoiceMaster");
////                vhfNo = jo.getString("InvNo");
////                POSNO = jo.getString("PosNo");
////                orderKind = jo.getString("InvKind");
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            Log.e("URL_TO_HIT",""+URL_TO_HIT+"   "+obj.toString());
//            try {
//
//
//                HttpClient client = new DefaultHttpClient();
//                HttpPost request = new HttpPost();
//                request.setURI(new URI(URL_TO_HIT));
//                request.setHeader("Content-Type","application/json");
//
//
//                List<BasicNameValuePair> nameValuePairs = new ArrayList<>(1);
//                // nameValuePairs.add(new BasicNameValuePair("_ID", "3"));
//
//                nameValuePairs.add(new BasicNameValuePair("InvoiceViewModel",obj.toString()));
//
//                // nameValuePairs.add(new BasicNameValuePair("SERIAL",convertToEnglish(jsonObject.getSERIAL())));
//
//                request.setEntity(new StringEntity(obj.toString()));
//
//
//                //Log.e("URL_TO_HIT",""+URL_TO_HIT+"  "+jsonObject.toString());
//
//                HttpResponse response = client.execute(request);
//
//
//                BufferedReader in = new BufferedReader(new
//                        InputStreamReader(response.getEntity().getContent()));
//
//                StringBuffer sb = new StringBuffer("");
//                String line = "";
//
//                while ((line = in.readLine()) != null) {
//                    sb.append(line);
//                }
//
//                in.close();
//
//
//                JsonResponse = sb.toString();
//                Log.e("exportCarPark", "JsonResponse\t" + JsonResponse);
//
//                return JsonResponse;
//
//
//            }//org.apache.http.conn.HttpHostConnectException: Connection to http://10.0.0.115 refused
//            catch (HttpHostConnectException ex) {
//                ex.printStackTrace();
////                progressDialog.dismiss();
//
//                Handler h = new Handler(Looper.getMainLooper());
//                h.post(new Runnable() {
//                    public void run() {
//
//                        Toast.makeText(context, "Ip Connection Failed ", Toast.LENGTH_LONG).show();
//                    }
//                });
//
//
//                return null;
//            } catch (Exception e) {
//                e.printStackTrace();
////                progressDialog.dismiss();
//                return null;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            //   swASingUp.dismissWithAnimation();
//            if (s != null && s.contains("DATA SAVED")) {
//////                Toast.makeText(ExportJason.this , "Success" , Toast.LENGTH_SHORT).show();
////                Log.e("tag", "****Success");
////                Log.e("vhf Success___", "= " + vhfNo);
////
//                dbHandler.updateOrderTablesIsPost(vhfNo, POSNO, orderKind);
//                dbHandler.updateOrderTablesIsPost2(vhfNo, POSNO, orderKind);
//                dbHandler.updateOrderTablesIsPost3(vhfNo, POSNO, orderKind);
//
////            } else if (s != null && s.contains("ErrorCode : 6")) {
////
////                dbHandler.updateOrderTablesIsPost(vhfNo,POSNO,orderKind);
////                dbHandler.updateOrderTablesIsPost2(vhfNo,POSNO,orderKind);
////                dbHandler.updateOrderTablesIsPost3(vhfNo,POSNO,orderKind);
//                Log.e("post_sen",""+s.toString());
//
//                Toast.makeText(context, "Save Success", Toast.LENGTH_SHORT).show();
////
//            }
//            else {
////                Toast.makeText(ExportJason.this, "Failed to export data", Toast.LENGTH_SHORT).show();
//                Log.e("tag ORDER", "****Failed to export data");
//                // Log.e("vhf failed ___2", "= " + vhfNo + "POSNO = " + POSNO);
//            }
//        }
//    }


}








