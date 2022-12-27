package com.example.salonapp;

import static com.example.salonapp.ControllClass.dateTime;
import static com.example.salonapp.FirstLayoutApp.appoimentModel;
import static com.example.salonapp.FirstLayoutApp.dates;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonapp.DataServer.ExportJson;
import com.example.salonapp.DataServer.importJson;
import com.example.salonapp.Model.AppoimentModel;
import com.example.salonapp.Model.CategModel;
import com.example.salonapp.Model.CategoryItemModel;
import com.example.salonapp.Model.CustomerInfoModel;
import com.example.salonapp.Model.DataExported;
import com.example.salonapp.Model.GetAppotmentDetales;
import com.example.salonapp.Model.GetEmployModel;
import com.example.salonapp.Model.GetServiceModel;
import com.example.salonapp.Model.GroupsTable;
import com.example.salonapp.Model.ServiceModel;
import com.example.salonapp.Model.Type;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AppendActivity extends AppCompatActivity {
    CategoryItemListAdapter categoryItemListAdapter;
    List<GetServiceModel> itemList, serviceTempList;
    List<GroupsTable> categList, groupTempList;
    GridView listGrid;
    ListView catList;
    CategoryListAdapter categoryListAdapter;
    List<GetAppotmentDetales> listOfService;
    ListView list;
    ControllClass controllClass;
    List<String> servNo = new ArrayList<>();
    OrderedListAdapter orderedListAdapter;
    EditText searchService, searchGroup;
    TextView date, fromTime, toTime, period, employName, periodTime, customerName, customerPhone, note1;
    importJson importData;
    String durationTime = "";
    Spinner type;
    List<String> typeList;
    ArrayAdapter<String> arrayList;
    TextView close, save;
    List<GetAppotmentDetales>deleteList;

    public static boolean isClose=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appended_event);

        Initialization();


//        GridLayoutManager lLayout = new GridLayoutManager(MainActivity.this, NO_OF_COLUMNS);
//        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
//        rView.setHasFixedSize(true);
//        rView.setLayoutManager(lLayout);
//
//        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(MainActivity.this, rowListItem);
//        rView.setAdapter(rcAdapter);
    }

    private void Initialization() {
        itemList = new ArrayList<>();
        listGrid = findViewById(R.id.itemListView);
        categList = new ArrayList<>();
        catList = findViewById(R.id.catList);
        listOfService = new ArrayList<>();
        deleteList=new ArrayList<>();
        list = findViewById(R.id.list);
        searchService = findViewById(R.id.searchService);
        searchGroup = findViewById(R.id.searchGroup);
        controllClass = new ControllClass(AppendActivity.this);
        serviceTempList = new ArrayList<>();
        groupTempList = new ArrayList<>();
        type = findViewById(R.id.type);
        typeList = new ArrayList<>();
        importData = new importJson(AppendActivity.this);
//        itemList=controllClass.getserviceMo();
        close = findViewById(R.id.close);
        save = findViewById(R.id.save);


//        appoimentModel
        typeList = controllClass.getType();
        Log.e("typeList", "" + typeList.size());
        if (typeList.size() != 0) {
            arrayList = new ArrayAdapter<String>(AppendActivity.this, R.layout.spinner_style, typeList);
            type.setAdapter(arrayList);


        }
        List<GetEmployModel> employModels = controllClass.getEmployName(appoimentModel.getEMPLOYEE_NO());

        date = findViewById(R.id.date);
        fromTime = findViewById(R.id.fromTime);
        toTime = findViewById(R.id.toTime);
        period = findViewById(R.id.period);
        employName = findViewById(R.id.employName);
        periodTime = findViewById(R.id.periodTime);
        customerName = findViewById(R.id.customerName);
        customerPhone = findViewById(R.id.customerPhone);
        note1 = findViewById(R.id.note1);


        importData.getCustomer(appoimentModel.getCUSTOMER_NO());
        date.setText("" +controllClass.convertToEnglish(appoimentModel.getTODAY()));
        fromTime.setText("" + controllClass.convertToEnglish(appoimentModel.getFROM_TIME()));
        toTime.setText("" + controllClass.convertToEnglish(appoimentModel.getTO_TIME()));
        period.setText("" + controllClass.convertToEnglish(appoimentModel.getDURATION()));


        customerName.setText("" + controllClass.convertToEnglish(appoimentModel.getCUSTOMER_NO()));
        customerPhone.setText("" +controllClass.convertToEnglish( appoimentModel.getCUSTOMER_NO()));
        note1.setText("" + appoimentModel.getNOTE_1());

        categList = controllClass.getGroupsTable();
        importData.getAppDetailsByGroup(appoimentModel.getCODE());
        if (employModels.size() != 0) {
            employName.setText("" + employModels.get(0).getNAME());
        }

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controllClass.TimeDialog(date);

            }
        });

        categoryListAdapter = new CategoryListAdapter(AppendActivity.this, categList);
        catList.setAdapter(categoryListAdapter);

//        listOfService.add(new ServiceModel("Cut Hair","00:54"));
//        listOfService.add(new ServiceModel(" Hair","02:54"));
//        listOfService.add(new ServiceModel("protain","01:4"));


//         orderedListAdapter=new OrderedListAdapter(AppendActivity.this,listOfService);
//         list.setAdapter(orderedListAdapter);

        listOfService.clear();

        searchService.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                serviceTempList.clear();
                if (!searchService.getText().toString().equals("")) {

                    for (int y = 0; y < itemList.size(); y++) {

                        if (itemList.get(y).getNAME().toUpperCase().equals(searchService.getText().toString().toUpperCase())) {

                            serviceTempList.add(itemList.get(y));
                        }

                    }

                    fillServiceList(serviceTempList);

                } else {
                    fillServiceList(itemList);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        searchGroup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                groupTempList.clear();
                if (!searchGroup.getText().toString().equals("")) {

                    for (int y = 0; y < categList.size(); y++) {

                        if (categList.get(y).getNAME().toUpperCase().equals(searchGroup.getText().toString().toUpperCase())) {

                            groupTempList.add(categList.get(y));
                        }

                    }
                    categoryListAdapter = new CategoryListAdapter(AppendActivity.this, groupTempList);
                    catList.setAdapter(categoryListAdapter);


                } else {
                    categoryListAdapter = new CategoryListAdapter(AppendActivity.this, categList);
                    catList.setAdapter(categoryListAdapter);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(AppendActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are You Sure You Want To Save  ??")
                        .setContentText("")
                        .setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        })
                        .setConfirmText("Yes")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {

                                //save
                                try {
                                    Log.e("ListOfSer", "" + listOfService.size());
                                    JSONArray obj2 = new JSONArray();
                                    for (int i = 0; i < listOfService.size(); i++) {

                                        try {
                                            if (listOfService.get(i).getIsNew().equals("1")) {
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
                                                DataExported dataExported = new DataExported();
                                                dataExported.setiCode(appoimentModel.getCODE());
                                                dataExported.setsFromTime(controllClass.convertToEnglish(fromTime.getText().toString()));
                                                dataExported.setsToTime(controllClass.convertToEnglish(toTime.getText().toString()));
                                                dataExported.setdUpdDate(controllClass.convertToEnglish(date.getText().toString()));
                                                dataExported.setsDuration(controllClass.convertToEnglish(period.getText().toString()));
                                                dataExported.setsNote1(note1.getText().toString());
                                                dataExported.setsNote2(note1.getText().toString());
                                                dataExported.setsUpdTime(controllClass.convertToEnglish(controllClass.getTime()));
                                                Log.e("timeeeee", "" + controllClass.getTime());
                                                dataExported.setsUpdUser("user");
                                                dataExported.setiRowNumber(controllClass.convertToEnglish("" + (i + 1)));
                                                dataExported.setiServiceNo(controllClass.convertToEnglish(listOfService.get(i).getSERVICE_NO()));
                                                dataExported.setsDurationService(controllClass.convertToEnglish(listOfService.get(i).getSERVICE_DURATION()));
                                                dataExported.setsInsUserNo("0");//listOfService.get(i).getINS_USER_NO()
                                                dataExported.setdInsDate(controllClass.convertToEnglish(controllClass.getDay()));//listOfService.get(i).getINS_DATE()
                                                dataExported.setsInsTime(controllClass.convertToEnglish(controllClass.getTime()));//listOfService.get(i).getINS_TIME()
                                                obj2.put(dataExported.getJSONObject2());
                                            }


                                        }catch (Exception e){
                                            Log.e("ErrorUpdates", "300  --> " + e.toString());

                                        }

                                    }



                                    try {

                                        JSONObject obj = new JSONObject();
                                        obj.put("JSN", obj2);
                                        ExportJson exportJson = new ExportJson(AppendActivity.this);
//                                        exportJson.UpdateDeleteAction();

//                                        for (int y=0;y<deleteList.size();y++){
//                                            try {
//                                                if (deleteList.get(y).getIsNew().equals("1")) {

//                                            Log.e("Updatedelete"," close"+deleteList.get(y).getSERVICE_NO()+"  delete size = "+deleteList.size());
                                                    exportJson.UpdateDeleteAction(deleteList,appoimentModel.getCODE()/*, deleteList.get(y).getSERVICE_NO()*/);

//                                                }
//                                            }catch (Exception e){
//                                                exportJson.UpdateDeleteAction(appoimentModel.getCODE(), deleteList.get(y).getSERVICE_NO());
//                                            }
//                                        }


                                        exportJson.UpdateAction(obj,controllClass.convertToEnglish(date.getText().toString()),appoimentModel.getCODE());

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Log.e("ErrorUpdate", "" + e.toString());
                                    }

//                                    finish();
                                    sweetAlertDialog.dismissWithAnimation();
                                } catch (Exception e) {
                                    Log.e("ErrorUpdates", "293  --> " + e.toString());

                                }

                            }
                        })
                        .show();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SweetAlertDialog(AppendActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are You Sure You Want To Close??")
                        .setContentText("")
                        .setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        })
                        .setConfirmText("Yes")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                isClose=false;
                                refreshFirstLayout();
                              //  finish();
                                sweetAlertDialog.dismissWithAnimation();


                            }
                        })
                        .show();

            }
        });

    }

    public void refreshCat() {
        categoryItemListAdapter = new CategoryItemListAdapter(AppendActivity.this, itemList);
        listGrid.setAdapter(categoryItemListAdapter);
    }

    public void refreshService(List<GetAppotmentDetales> getServiceModel,int refreshM) {


        for (int i = 0; i < getServiceModel.size(); i++) {

//            if(listOfService.size()!=0){
            int index = Arrays.asList(servNo.toArray()).indexOf(getServiceModel.get(i).getSERVICE_NO());

            Log.e("index", " --> " + getServiceModel.get(i).getSERVICE_NO());

            Log.e("index", " --> " + index);
            if (index == -1) {
                if(getServiceModel.get(i).getSERVICE_DURATION().equals("")){
                    getServiceModel.get(i).setSERVICE_DURATION("00:01");
                }
                listOfService.add(getServiceModel.get(i));
                servNo.add(listOfService.size() - 1, getServiceModel.get(i).getSERVICE_NO());
            } else {
                if(refreshM!=1) {
                    Toast.makeText(this, "This Service Is Found In List", Toast.LENGTH_SHORT).show();
                }
            }
//            }else {
//                servNo[listOfService.size()-1] = getServiceModel.get(i).getSERVICE_NO();
//                listOfService.add(getServiceModel.get(i));
//            }

        }

        for (int q = 0; q < listOfService.size(); q++) {
            if (q == 0) {
                durationTime = listOfService.get(q).getSERVICE_DURATION();
            } else {
                durationTime = addTime(durationTime, listOfService.get(q).getSERVICE_DURATION());
            }

        }

        toTime.setText("" + addTime(fromTime.getText().toString(), durationTime));
        period.setText("" + controllClass.convertToEnglish(durationTime));
        periodTime.setText("" +controllClass.convertToEnglish( durationTime));

        orderedListAdapter = new OrderedListAdapter(AppendActivity.this, listOfService);
        list.setAdapter(orderedListAdapter);

        //refresh for from Time to time and period

    }

    public void fillServiceList(List<GetServiceModel> servicesList) {
        itemList = servicesList;
        categoryItemListAdapter = new CategoryItemListAdapter(AppendActivity.this, itemList);
        listGrid.setAdapter(categoryItemListAdapter);
        refreshCat();
    }

    String addBetweenTime(String fromT, String toT) {

        SimpleDateFormat format = new SimpleDateFormat("hh:mm");// or you can add before dd/M/yyyy

        Date date1 = null;
        Date date2 = null;
        try {
            date1 = format.parse(fromT);
            date2 = format.parse(toT);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long mills = date1.getTime() + date2.getTime();

        int hours = (int) (mills / (1000 * 60 * 60));
        int mins = (int) (mills / (1000 * 60)) % 60;


        String diff = hours + ":" + mins;

        return diff;
    }

    String addTime(String t1, String t2) {
        String date3 = null;
        try {
            String time1 = t1 + ":00";
            String time2 = t2 + ":00";

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date date1 = null;
            Date date2 = null;

            try {

                date1 = timeFormat.parse(time1);
                Log.e("The d1 is", "" + date1);
                date2 = timeFormat.parse(time2);
                Log.e("The d2 is", "" + date2);
            } catch (Exception e) {

            }
            long sum = date1.getTime() + date2.getTime();

            date3 = timeFormat.format(new Date(sum));
            System.out.println("The sum is " + date3);
            Log.e("The sum is", "" + date3);
        } catch (Exception e) {

        }
        return controllClass.convertToEnglish(date3);
    }


    public void refreshDelete(int i) {
        GetAppotmentDetales d=listOfService.get(i);
        if(TextUtils.isEmpty(listOfService.get(i).getIsNew())) {
            deleteList.add(d);
        }
        listOfService.remove(i);
        servNo.remove(i);
        refreshService(listOfService,1);

    }

    public void fillCustomerInfo(List<CustomerInfoModel> customerInfo) {
        if (customerInfo.size() != 0) {

            customerName.setText("" + customerInfo.get(0).getFULL_NAME());
            customerPhone.setText("" + customerInfo.get(0).getPHONE());

        } else {

        }
    }

    public void refreshLayout() {
        Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
        Log.e("InREf11","up  "+dates);

        try{

            importData.getAppointment(dateTime);
            Log.e("InREf12","up  "+dateTime);

        }catch (Exception e){
Log.e("ErrorInREf","e "+e.toString()+dateTime);
        }


    }

   public void refreshFirstLayout(){
        Intent intentBarcode = new Intent(AppendActivity.this, FirstLayoutApp.class);
        intentBarcode.putExtra("FF", "1");
        startActivity(intentBarcode);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isClose) {
            refreshFirstLayout();
        }
    }

    @Override
    public void onBackPressed() {

        return;
    }
}
