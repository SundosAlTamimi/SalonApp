package com.example.salonapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.salonapp.Model.GetAppotmentDetales;
import com.example.salonapp.Model.GetServiceModel;
import com.example.salonapp.Model.ServiceModel;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class OrderedListAdapter extends BaseAdapter {

    private Context context;
    private static List<GetAppotmentDetales> itemsList;
    private MainActivity obj;

    public OrderedListAdapter(Context context, List<GetAppotmentDetales> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
        obj = new MainActivity();
    }

    public OrderedListAdapter() {

    }

    public void setItemsList(List<GetAppotmentDetales> itemsList) {
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

    private class ViewHolder {
        TextView serviceName, periodTime,delete;

    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder = new ViewHolder();
        view = View.inflate(context, R.layout.ordered_row, null);

        holder.serviceName = (TextView) view.findViewById(R.id.serviceName);
        holder.periodTime = (TextView) view.findViewById(R.id.periodTime);
        holder.delete=view.findViewById(R.id.delete);


        holder.serviceName.setText(itemsList.get(i).getServiceName());
        if(!itemsList.get(i).getSERVICE_DURATION().equals("")) {
            holder.periodTime.setText(itemsList.get(i).getSERVICE_DURATION());
        }else {
            itemsList.get(i).setSERVICE_DURATION("00:01");
            holder.periodTime.setText("00:01");
        }


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are You Sure You Want To Delete This Line  ??")
                        .setContentText("")
                        .setCancelButton("NO", new SweetAlertDialog.OnSweetClickListener() {
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

                                    AppendActivity appendActivity = (AppendActivity) context;
                                    appendActivity.refreshDelete (i);

                                    sweetAlertDialog.dismissWithAnimation();

                                }catch (Exception e){

                                }
                            }
                        })
                        .show();

            }
        });

        return view;
    }

}
