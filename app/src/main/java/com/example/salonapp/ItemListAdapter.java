package com.example.salonapp;

import static com.example.salonapp.FirstLayoutApp.appoimentModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.salonapp.Model.AppoimentModel;
import com.example.salonapp.Model.GetCustomerNameTable;
import com.example.salonapp.Model.OrderListModel;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class ItemListAdapter extends BaseAdapter {

    private FirstLayoutApp context;
    private static List<AppoimentModel> itemsList;
    ControllClass controllClass;

    public ItemListAdapter(FirstLayoutApp context, List<AppoimentModel >itemsList) {
        this.context = context;
        this.itemsList = itemsList;
        controllClass=new ControllClass(context);
    }

    public ItemListAdapter() {

    }

    public void setItemsList(List<AppoimentModel>itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

//    private class ViewHolder {
//        TextView catName;
//        ImageView catPic;
//    }
//
//    @Override
//    public View getView(final int i, View view, ViewGroup viewGroup) {
//
//        final ViewHolder holder = new ViewHolder();
//        view = View.inflate(context, R.layout.cat_row, null);
//
//        holder.catName = (TextView) view.findViewById(R.id.catName);
//        holder.catPic = (ImageView) view.findViewById(R.id.image);
//
//
//        holder.catName.setText(itemsList.get(i).getCatName());
////        holder.catPic.setImageDrawable(context.getResources().getDrawable(itemsList.get(i).getPic()));
//
//        return view;
//    }




    private class ViewHolder {
        TextView customerName , phone,period,date,ph;
        LinearLayout rowId;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder = new ViewHolder();
        view = View.inflate(context, R.layout.item_row_, null);


//        if ( i%3 == 0) {
//            ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.INFINITE, .8f, ScaleAnimation.RELATIVE_TO_SELF, .8f);
////            scale.setStartOffset(900);
//            scale.setDuration(400);
//            scale.setInterpolator(new OvershootInterpolator());
//            view.startAnimation(scale);
//
//        } else if ( i%2 == 0) {
//            ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.INFINITE, .5f, ScaleAnimation.RELATIVE_TO_SELF, .8f);
////            scale.setStartOffset(900);
//            scale.setDuration(700);
//            scale.setInterpolator(new OvershootInterpolator());
//            view.startAnimation(scale);
//
//        } else {
//            ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.INFINITE, .2f, ScaleAnimation.RELATIVE_TO_SELF, .8f);
////            scale.setStartOffset(900);
//            scale.setDuration(200);
//            scale.setInterpolator(new OvershootInterpolator());
//            view.startAnimation(scale);
//        }






        holder.rowId=view.findViewById(R.id.rowId);
        holder.customerName = (TextView) view.findViewById(R.id.customerName);
        holder.phone = (TextView) view.findViewById(R.id.phoneNo);
        holder.period = (TextView) view.findViewById(R.id.period);
        holder.date=view.findViewById(R.id.date);
        holder.ph=view.findViewById(R.id.ph);

        List <GetCustomerNameTable>CustomerList=controllClass.getCustomerName(itemsList.get(i).getCUSTOMER_NO());
        if(CustomerList.size()!=0){
            holder.customerName.setText(""+CustomerList.get(0).getFULL_NAME());
            holder.ph.setText(""+CustomerList.get(0).getPHONE());
        }else {
            holder.customerName.setText(""+itemsList.get(i).getCUSTOMER_NO());
            holder.ph.setText("**********");

        }

        holder.period.setText(""+itemsList.get(i).getFROM_TIME()+" - "+itemsList.get(i).getTO_TIME());
        holder.date.setText(""+itemsList.get(i).getTODAY());

        if(itemsList.get(i).getDONE().equals("0")){
            holder.phone.setText("***This Appointment Can Update");
            holder.phone.setTextColor(context.getResources().getColor(R.color.greenS));

        }else if(itemsList.get(i).getDONE().equals("1")){
            holder.phone.setText("***This Appointment Can  not Update");
            holder.phone.setTextColor(context.getResources().getColor(R.color.red));


        }

//        for(int k=0;k<itemsList.get(i).size();k++){
//
//            holder.customerName.setText(itemsList.get(i).get(k).getCustomerName());
//        }

//        holder.itemName.setText(itemsList.get(i).get());
//        holder.itemPic.setImageDrawable(context.getResources().getDrawable(itemsList.get(i).getPic()));
//        holder.price.setText(""+itemsList.get(i).getPrice());

        holder.rowId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(itemsList.get(i).getDONE().equals("0")) {

                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Are You Sure You Want To Update This Appointment ? ")
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



                                    try{
                                     context.finishLayout();
                                    }catch (Exception e){
                                        Log.e("errorItemLiAd","error  "+e.toString());
                                    }

                                    Intent a = new Intent(context, AppendActivity.class);
                                    appoimentModel=itemsList.get(i);
                                    a.putExtra("FF","0");

                                    context.startActivity(a);

                                    sweetAlertDialog.dismissWithAnimation();


                                }
                            })
                            .show();

                }else   if(itemsList.get(i).getDONE().equals("1")) {

                    sweetMassageAlert("This Appointment is Done Can Not be Update");


                }




            }
        });

        return view;
    }
    public void sweetMassageAlert(String titleText){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(titleText)
                .setContentText("")
//                            .setCancelButton("cancel", new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                    sweetAlertDialog.dismissWithAnimation();
//                                }
//                            })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        sweetAlertDialog.dismissWithAnimation();


                    }
                })
                .show();
    }

}
