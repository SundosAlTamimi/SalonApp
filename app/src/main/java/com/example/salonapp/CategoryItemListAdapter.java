package com.example.salonapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.salonapp.Model.CategoryItemModel;
import com.example.salonapp.Model.GetAppotmentDetales;
import com.example.salonapp.Model.GetServiceModel;
import com.example.salonapp.Model.ServiceModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryItemListAdapter extends BaseAdapter {

    private Context context;
    private static List<GetServiceModel> itemsList;
    ControllClass controllClass;

    public CategoryItemListAdapter(Context context, List<GetServiceModel> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
        controllClass=new ControllClass(context);
    }

    public CategoryItemListAdapter() {

    }

    public void setItemsList(List<GetServiceModel> itemsList) {
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
        TextView itemName , price;
        ImageView itemPic;
        LinearLayout itemLinear;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder = new ViewHolder();
        view = View.inflate(context, R.layout.category_item_row, null);


        if ( i%3 == 0) {
            ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.INFINITE, .8f, ScaleAnimation.RELATIVE_TO_SELF, .8f);
//            scale.setStartOffset(900);
            scale.setDuration(400);
            scale.setInterpolator(new OvershootInterpolator());
            view.startAnimation(scale);

        } else if ( i%2 == 0) {
            ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.INFINITE, .5f, ScaleAnimation.RELATIVE_TO_SELF, .8f);
//            scale.setStartOffset(900);
            scale.setDuration(700);
            scale.setInterpolator(new OvershootInterpolator());
            view.startAnimation(scale);

        } else {
            ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.INFINITE, .2f, ScaleAnimation.RELATIVE_TO_SELF, .8f);
//            scale.setStartOffset(900);
            scale.setDuration(200);
            scale.setInterpolator(new OvershootInterpolator());
            view.startAnimation(scale);
        }






        holder.itemName = (TextView) view.findViewById(R.id.itemName);
//        holder.itemPic = (ImageView) view.findViewById(R.id.item_pic);


        holder.itemName.setText(itemsList.get(i).getNAME());
//        holder.itemPic.setImageDrawable(context.getResources().getDrawable(itemsList.get(i).getPic()));


        holder.itemLinear=view.findViewById(R.id.itemLinear);
        holder.itemLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppendActivity appendActivity =(AppendActivity) context;

                GetAppotmentDetales getAppotmentDetales=new GetAppotmentDetales();
//                getAppotmentDetales.setCODE("");
                getAppotmentDetales.setSERVICE_NO(itemsList.get(i).getCODE());
                getAppotmentDetales.setServiceName(itemsList.get(i).getNAME());
                if(!itemsList.get(i).getDURATION_TIME().equals("")) {
                    getAppotmentDetales.setSERVICE_DURATION(itemsList.get(i).getDURATION_TIME());
                }else {
                    getAppotmentDetales.setSERVICE_DURATION("00:01");

                }
                getAppotmentDetales.setINS_TIME(controllClass.getTime());
                getAppotmentDetales.setINS_USER_NO("0");
                getAppotmentDetales.setINS_DATE(controllClass.getDay());
                getAppotmentDetales.setIsNew("1");

                List<GetAppotmentDetales> appList=new ArrayList<>();
                appList.add(getAppotmentDetales);
                appendActivity.refreshService(appList,0);

            }
        });

        return view;
    }


}
