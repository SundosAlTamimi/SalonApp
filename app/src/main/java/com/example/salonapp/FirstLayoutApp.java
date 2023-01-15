package com.example.salonapp;

import static com.example.salonapp.ControllClass.dateTime;
import static com.example.salonapp.ControllClass.empName;
import static com.example.salonapp.ControllClass.empNo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.example.salonapp.DataServer.importJson;
import com.example.salonapp.Model.AppoimentModel;
import com.example.salonapp.Model.GetEmployModel;
import com.example.salonapp.Model.UserInfoModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FirstLayoutApp extends AppCompatActivity {
    GridView orderedList;
    ControllClass controllClass;
    private CarouselLayoutManager layoutManagerd;
    RecyclerView recyclerViews;
    EditText employSearch, appointmentSearch;
    List<GetEmployModel>data,tempData;
    List<AppoimentModel> appoimentTemp,listOfAppi;
    TextView employName,setting;
    public static AppoimentModel appoimentModel;
    TextView date,update;
    String employNo="0";
    importJson importData;
    String today="";
    static  String dates="";
    String getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_app_layout);
        orderedList=findViewById(R.id.itemListView);
        recyclerViews=findViewById(R.id.recyclerViews);
        controllClass=new ControllClass(FirstLayoutApp.this);
        employSearch =findViewById(R.id.itemCatSearch);
        appointmentSearch=findViewById(R.id.appointmentSearch);
        employName=findViewById(R.id.employName);
        showAllDataAccount();
        tempData=new ArrayList<>();
        appoimentTemp=new ArrayList<>();
        listOfAppi=new ArrayList<>();
        setting=findViewById(R.id.setting);
        date=findViewById(R.id.date);
        update=findViewById(R.id.update);
        importData=new importJson(FirstLayoutApp.this);

        Date currentTimeAndDate = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        today = df.format(currentTimeAndDate);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                importData=new importJson(FirstLayoutApp.this);
                importData.getAll(date.getText().toString());

            }
        });

        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!date.getText().toString().equals("")) {
                    if(!getData.equals("1")) {
                        importData.getAppointment(dates);
                        Log.e("date1", "" + dates);
                    }else{
                        importData.getAppointment(dateTime);
                        Log.e("date2", "" + dateTime);

                    }
                    Log.e("date", "" + dates);
                    dateTime=dates;

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateR=controllClass.TimeDialog(date);
               // date.setText(dateR);


            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingInt=new Intent(FirstLayoutApp.this,SettingActivity.class);
                startActivity(settingInt);


            }
        });

        employSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                tempData.clear();
                if(!employSearch.getText().toString().equals("")){
                    for(int q=0;q<data.size();q++) {
                        if (data.get(q).getNAME().toUpperCase().contains(employSearch.getText().toString().toUpperCase())) {
                            tempData.add(data.get(q));

                        }
                    }

                        recyclerViews.setAdapter(new TestAdapterForbar(FirstLayoutApp.this, tempData));

                }else {
                    recyclerViews.setAdapter(new TestAdapterForbar(FirstLayoutApp.this, data));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        appointmentSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                appoimentTemp.clear();
                if(!appointmentSearch.getText().toString().equals("")){
                    for(int q=0;q<listOfAppi.size();q++) {
                        try {
                            if (listOfAppi.get(q).getCustomerName().toUpperCase().contains(appointmentSearch.getText().toString().toUpperCase())) {
                                appoimentTemp.add(listOfAppi.get(q));

                            }
                        }catch (Exception e){

                        }
                    }

                        ItemListAdapter  orderedItemsAdapter = new ItemListAdapter(FirstLayoutApp.this, appoimentTemp);
                        orderedList.setAdapter(orderedItemsAdapter);

                }else {
                    ItemListAdapter  orderedItemsAdapter = new ItemListAdapter(FirstLayoutApp.this, listOfAppi);
                    orderedList.setAdapter(orderedItemsAdapter);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        try {
            getData = getIntent().getStringExtra("FF");

            if(getData.equals("1")){
                employNo=empNo;
                date.setText(controllClass.convertToEnglish(dateTime)+"");
                employName.setText(empName+"");

                refreshList(listOfAppi);
                getData="0";
            }else {
                date.setText(""+controllClass.convertToEnglish(today));
            }

        }catch (Exception e){
            Log.e("LogError","error_from "+e.toString());
            date.setText(""+controllClass.convertToEnglish(today));
        }


    }


    public void refresh(String message){
        new SweetAlertDialog(FirstLayoutApp.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(FirstLayoutApp.this.getResources().getString(R.string.ops))
                            .setContentText(message)
                            .show();
        refreshList(listOfAppi);

    }

    void refreshEmploy(){
        new SweetAlertDialog(FirstLayoutApp.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(FirstLayoutApp.this.getResources().getString(R.string.ops))
                .setContentText(FirstLayoutApp.this.getResources().getString(R.string.noDataE))
                .show();

    }


    public void showAllDataAccount() {
//        showHold.setText("" + holdVoucherList.size());
//        linerRec.setVisibility(View.VISIBLE);
        data=new ArrayList<>();

//        data=controllClass.getEmployModels();
        data=controllClass.getEmployList();
        layoutManagerd = new CarouselLayoutManager(CarouselLayoutManager.VERTICAL, true);

        recyclerViews.setLayoutManager(layoutManagerd);
        recyclerViews.setHasFixedSize(true);
        recyclerViews.addOnScrollListener(new CenterScrollListener());
        layoutManagerd.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        recyclerViews.setAdapter(new TestAdapterForbar(this,data ));
        recyclerViews.requestFocus();
        recyclerViews.scrollToPosition(6);
        recyclerViews.requestFocus();


    }

    public void refreshList(List<AppoimentModel> appoimentModelList) {
        showAllDataAccount();
        listOfAppi = controllClass.getAppointmentByUser(employNo);//employNo
        ItemListAdapter orderedItemsAdapter = new ItemListAdapter(FirstLayoutApp.this, listOfAppi);
        orderedList.setAdapter(orderedItemsAdapter);
    }

    public void finishLayout(){
        finish();
    }

    static class CViewHolderForbar extends RecyclerView.ViewHolder {

        ListView holdList;
        TextView customerName, ATax, tax;
        Button clear, doneButton;
        LinearLayout linearLayout;

        public CViewHolderForbar(View itemView) {
            super(itemView);
            customerName = itemView.findViewById(R.id.customerName);
            linearLayout=itemView.findViewById(R.id.dataLayout);
//            layBar = itemView.findViewById(R.id.layBar);
//            itemImage = itemView.findViewById(R.id.imgbar);

//        holdList = itemView.findViewById(R.id.listHold);
//        BTax = itemView.findViewById(R.id.sum_no_tax);
//        ATax = itemView.findViewById(R.id.sum_after_tax);
//        tax = itemView.findViewById(R.id.tax);
//        clear = itemView.findViewById(R.id.clear);
//        doneButton = itemView.findViewById(R.id.done_button);

        }
    }

    class TestAdapterForbar extends RecyclerView.Adapter<CViewHolderForbar> {
        Context context;
        List<GetEmployModel> list;
//DatabaseHandler db;

        public TestAdapterForbar(Context context, List<GetEmployModel> list) {
            this.context = context;
            this.list = list;
//        db=new DatabaseHandler(this.context);
        }


        @Override
        public CViewHolderForbar onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_row, viewGroup, false);
            return new CViewHolderForbar(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(final CViewHolderForbar cViewHolder, @SuppressLint("RecyclerView") final int i) {
            cViewHolder.customerName.setText(""+list.get(i).getNAME());
            cViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    employNo=list.get(i).getCODE();
                    empNo=list.get(i).getCODE();
                    empName=list.get(i).getNAME();
                    employName.setText(""+list.get(i).getNAME());
                    listOfAppi= controllClass.getAppointmentByUser(employNo);//employNo

                    if(listOfAppi.size()==0){
                        refreshEmploy();
                    }

                    ItemListAdapter  orderedItemsAdapter = new ItemListAdapter(FirstLayoutApp.this, listOfAppi);
                    orderedList.setAdapter(orderedItemsAdapter);
                }
            });
            final boolean[] longIsOpen = {false};

        }

        @Override
        public int getItemCount() {
            return list.size();
//            return Integer.MAX_VALUE;
        }
    }

}
